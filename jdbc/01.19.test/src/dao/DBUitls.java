package dao;

import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
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
    static {
        Properties prop = new Properties();
        InputStream resourceAsStream = DBUitls.class.getResourceAsStream("/jdbc.properties");
        try {
            prop.load(resourceAsStream);
            DRIVER=prop.getProperty("jdbc.mysql.drover");
            URL=prop.getProperty("jdbc.mysql.url");
            USER=prop.getProperty("jdbc.mysql.user");
            PASSWORD=prop.getProperty("jdbc.mysql.password");

            Class.forName(DRIVER);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 打开数据库
     */
    public Connection getconnection(){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库
     */
    public void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try {
            if (rs!=null)
                rs.close();
            if (pstmt!=null)
                pstmt.close();
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
        PreparedStatement pstmt=null;
        int row= -1;
        try {
            pstmt = conn.prepareStatement(sql);

            if (params!=null)
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i+1,params[i]);
                }
             row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(conn,pstmt,null);
        }
        return row;
    }

    /**
     * 通用的查询
     */
     protected <T> List<T> queryList_relect(Class<T> tclass, String sql, Object...params){
         Connection conn=getconnection();
         PreparedStatement pstmt = null;
         ResultSet rs=null;
         List<T> list = new ArrayList<>();
         try {
             pstmt = conn.prepareStatement(sql);
             if (params!=null)
                 for (int i = 0; i < params.length; i++) {
                     pstmt.setObject(i+1,params[i]);
                 }
             rs=pstmt.executeQuery();

                 while (rs.next()){
                     T t = tclass.newInstance();
                     ResultSetMetaData metaData = rs.getMetaData();
                     int columnCount = metaData.getColumnCount();
                     for (int i = 0; i < columnCount; i++) {
                         String columnClassName = metaData.getColumnClassName(i + 1);
                         BeanUtils.setProperty(t,columnClassName,rs.getObject(columnClassName));
                     }
                     list.add(t);
                 }
         } catch (SQLException e) {
             e.printStackTrace();
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (InstantiationException e) {
             e.printStackTrace();
         } catch (InvocationTargetException e) {
             e.printStackTrace();
         } finally {
             close(conn,pstmt,rs);
         }
         return list;
     }
}
