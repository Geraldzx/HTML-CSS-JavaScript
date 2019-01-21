package com.impl;

import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUitls {
    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    static{
        Properties prop = new Properties();
        InputStream resourceAsStream = DBUitls.class.getResourceAsStream("/jdbc.properties");
        try {
            prop.load(resourceAsStream);
            DRIVER=prop.getProperty("com.mysql.cj.jdbc.Driver");
            URL=prop.getProperty("jdbc:mysql://127.0.0.1:3306/examstudent?useUnicode=true&characterEncoding=UTF8");
            USER=prop.getProperty("root");
            PASSWORD=prop.getProperty("root");

            Class.forName(DRIVER);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //打开数据库
    public Connection  getconnection(){
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
/*
关闭数据库
 */
    public void close(Connection conn, PreparedStatement prtmt, ResultSet rs){
            try {
                if (rs!=null)
                    rs.close();
                if (prtmt!=null)
                    prtmt.close();
                if (conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * 通用的增，删，改
     */
    protected int executeUpdate(String sql,Object...params){
        Connection conn=getconnection();
        PreparedStatement prtmt=null;
        try {
            prtmt=conn.prepareStatement(sql);
            if (params!=null){
                for (int i = 0; i < params.length; i++) {
                    prtmt.setObject(i+1,params[i]);
                }
            }
            int row=prtmt.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(conn,prtmt,null);
        }
        return -1;
    }

    /**
     * 通用查询
     */
    protected <T> List <T> querylist(Class<T> Tclass,String sql,Object...params){
        Connection conn=getconnection();
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        List<T> list = new ArrayList<>();
        try {
            pstmt=conn.prepareStatement(sql);
            if (params!=null){
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i+1,params[i]);
                }
            }

            rs=pstmt.executeQuery();
            while (rs.next()){
                T t = Tclass.newInstance();
                //为对象T的属性复制
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();//获取列的个数
                for (int i = 0; i < columnCount; i++) {
                    String colum = metaData.getColumnLabel(i + 1);//获取列名
                    BeanUtils.setProperty(t,colum,rs.getObject(colum));
                }
                list.add(t);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            close(conn,pstmt,rs);
        }
        return null;
    }
}
