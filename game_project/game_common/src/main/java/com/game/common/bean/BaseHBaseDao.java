package com.game.common.bean;

import com.game.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:BaseHBaseDao
 * @description：基础数据访问对象
 * @author:BochengHu
 * @date 2023-07-12  16:08
 */
public abstract class BaseHBaseDao {
    //创建线程访问对象
    private ThreadLocal<Connection> connHolder=new ThreadLocal<>();
    private ThreadLocal<Admin> adminHolder=new ThreadLocal<>();
    /**
     * 开始
     */
    protected  void start() throws IOException {
        getConnect();//获取连接
        getAdmin();//获取admin
    }

    /**
     * 结束
     * @throws IOException
     */
    protected void end() throws IOException {
        Admin admin = getAdmin();
        if(admin!=null){
            admin.close();
            adminHolder.remove();
        }
        Connection conn = getConnect();
        if(conn!=null){
            conn.close();
            connHolder.remove();
        }
    }

    /**
     * 创建表，没有预分区
     * @param name
     * @param families
     * @throws IOException
     */
    protected void createTableXX(String name,String...families) throws IOException {
        createTableXX(name,null,families);
    }
    /**
     * 创建表，有预分区
     * @param name  表名
     * @param regionCount 预分区
     * @param families  列族
     * @throws IOException
     */
    protected void createTableXX(String name,Integer regionCount,String...families) throws IOException {
        //获取admin对象
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);
        //判断表是否存在，存在就删除
        if(admin.tableExists(tableName)){
            deleteTable(name);
        }
        //创建表
        createTable(name,regionCount,families);
    }

    /**
     * 创建表
     * @param name 表名
     * @param families 列族
     * @throws IOException
     */
    private void createTable(String name,Integer regionCount,String...families) throws IOException {
        //列族
        //判断列族是否为空
        if(families.length==0||families==null){
            families=new String[1];//声明数据空间
            families[0]= Names.CF_INFO.getValue();//给列族赋值
        }
        //获取Admin对象
        Admin admin = getAdmin();
        //创建表的描述器
        TableName tableName=TableName.valueOf(name);
        TableDescriptorBuilder tableDescriptorBuilder=TableDescriptorBuilder.newBuilder(tableName);
        for (String family : families) {
            //创建列的描述器
            ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(family.getBytes()).build();
            //把列族的描述器放入表描述器中
            tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
        }

        //增加预分区
        //分区键
        if(regionCount==null || regionCount<=0){//没有分区键
            //创建表
            admin.createTable(tableDescriptorBuilder.build());
        }else{//有分区键
            byte[][] splitKeys=genSplitKeys(regionCount);
            admin.createTable(tableDescriptorBuilder.build(),splitKeys);
        }
    }
    /**
     * 删除表
     * @param name
     * @throws IOException
     */
    protected void deleteTable(String name) throws IOException {
        TableName tableName = TableName.valueOf(name);
        Admin admin=getAdmin();
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }
    /**
     * 创建命名空间，如果命名空间已经存在，不需要创建，否则，创建新的
     * @param namespace
     */
    protected void createNamespaceNX(String namespace) throws IOException {
        Admin admin = getAdmin();
        try {
            //获取命名空间描述器
            admin.getNamespaceDescriptor(namespace);
        }catch (NamespaceNotFoundException e){
            //e.printStackTrace();
            //创建命名空间描述器
            NamespaceDescriptor namespaceDescriptor =
                    NamespaceDescriptor.create(namespace).build();
            //创建命名空间
            admin.createNamespace(namespaceDescriptor);
        }

    }

    /**
     * 获取管理员admin
     * @return
     * @throws IOException
     */
    protected synchronized Admin getAdmin() throws IOException {
        Admin admin = adminHolder.get();
        if(admin==null){
            admin=getConnect().getAdmin();
            //管理员对象放入线程
            adminHolder.set(admin);
        }
        return admin;
    }
    /**
     * 创建连接对象
     * @return
     */
    //synchronized:表示同步
    protected synchronized Connection getConnect() throws IOException {
        //获取连接对象
        Connection conn = connHolder.get();
        if(conn==null){//判断连接对象是否存在,是空则创建
            Configuration conf = HBaseConfiguration.create();
            conn = ConnectionFactory.createConnection(conf);
            //连接对象放到进程中
            connHolder.set(conn);
        }
        return conn;
    }

    /**
     * 生成分区键
     * @param regionCount 分区号
     * @return
     */
    private byte[][] genSplitKeys(int regionCount){
        int splitKeyCount=regionCount-1;//如果有6个分区，只有5个分区键
        byte[][] bs=new byte[splitKeyCount][];//写一个二维数组，为分区键
        //分区键:0|,1|,2|,3|,4|
        //[负无穷,0|),[0|,1),[1|,2),[2|,3),[3|,4),[4|,正无穷)
        List<byte[]> bsList=new ArrayList<byte[]>();
        for (int i = 0; i < splitKeyCount; i++) {
            String splitKey=i+"|";
            System.out.println(splitKey);
            bsList.add(Bytes.toBytes(splitKey));
        }
        //把二维数组放入集合中
        bsList.toArray(bs);
        return bs;
    }


    /**
     * 增加多条数据
     * @param name 表名
     * @param puts 增加多条数据
     * @throws IOException
     */
    protected void putData(String name,List<Put> puts) throws IOException {
        //获取表对象
        Connection connect = getConnect();
        //获取表对象
        Table table = connect.getTable(TableName.valueOf(name));
        //增加数据
        table.put(puts);
        //释放资源
        table.close();
    }
    /**
     * 增加一条数据
     * @param name 表名
     * @param put  增加对象
     * @throws IOException
     */
    protected void putData(String name,Put put) throws IOException {
        //获取表对象
        Connection connect = getConnect();
        //获取表对象
        Table table = connect.getTable(TableName.valueOf(name));
        //增加数据
        table.put(put);
        //释放资源
        table.close();
    }
}
