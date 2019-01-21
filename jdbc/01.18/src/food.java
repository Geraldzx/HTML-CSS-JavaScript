import java.sql.*;
import java.util.Scanner;

import static sun.misc.Version.print;

public class food {
    //私有化driver
    private  static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //私有化连接
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/food?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC";
    //私有化用户名
    private static final String USER = "root";
    //私有化密码
    private static final String PASSWODE = "root";

    static Scanner input = new Scanner(System.in);
    static  {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("1.添加新食品");
        System.out.println("2.根据食品名称，查询食品信息");
        System.out.println("3.根据食品名称，修改食品价格");
        System.out.println("4.计算所有食品总价");
        System.out.println("5.统计所有食品中最贵的商品信息");
        System.out.println("6.按照食品的价格升序排序输出这些食品的信息");
        System.out.println("7.退出");
    }
    public static void main(String[] args) {

        while(true){
            print();
            System.out.println("请选择：");
            int choose = input.nextInt();
            switch (choose){
                case 1:
                    add();
                    break;
                case 2:
                    findfood();
                    break;
                case 3:
                    revise();
                    break;
                case 4:
                    money();
                    break;
                case 5:
                    moneyTo();
                    break;
                case 6:
                    sort();
                    break;
                case 7:
                    System.out.println("感谢使用,再见！");
                    return;
                default:
                    System.out.println("输入有误，请重新输入！");
            }
        }
    }
    private static void add(){
        System.out.println("请输入食物名称");
        String name = input.next();
        System.out.println("请输入食物单价");
        double price = input.nextDouble();
        System.out.println("请输入库存数量");
        int chose = input.nextInt();

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWODE);
            String sql = "INSERT INTO FOOD (NAME,PRICE,CHOSE) VALUSE (?,?,?)";
            PreparedStatement pp = connection.prepareStatement(sql);
            pp.setString(1,name);
            pp.setDouble(2,price);
            pp.setInt(3,chose);

            int i = pp.executeUpdate();
            if(i>0)
                System.out.println("添加成功");
            else
                System.out.println("添加失败");
            if (pp!=null)
                pp.close();
            if (connection!=null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void findfood(){
        System.out.println("请输入要查询的食物名称");
        String name = input.next();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWODE);
            String sql = "SELECT * FROM FOOD WHERE NAME = ?";
            PreparedStatement pp = connection.prepareStatement(sql);
            pp.setString(1,name);
            ResultSet rs = pp.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String Fname = rs.getString(2);
                double price = rs.getDouble(3);
                int choose = rs.getInt(4);
                System.out.println("食品编号：" + id + "名称：" + name + "价格：" + price + "数量：" + choose);
            }
            if (rs!=null)
                rs.close();
            if (pp !=null)
                pp.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private  static void revise(){
        System.out.println("请输入需要修改的食品");
        String name = input.next();
        System.out.println("请输入修改后的单价");
        double price = input.nextDouble();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWODE);
            String sql = "UPDATE  FOOD F PRICE=? WHERE F.NAME='?'";
            PreparedStatement pp = connection.prepareStatement(sql);
            int i = pp.executeUpdate();
            if (i>0)
                System.out.println("修改成功");
            else
                System.out.println("修改失败");

            if (pp!=null)
                pp.close();
            if (connection!=null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private  static void money(){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWODE);
            String sql = "SELECT SUM(F.PRICE*F.CHOSE) FROM FOOD F";
            PreparedStatement pp = connection.prepareStatement(sql);
            ResultSet rs = pp.executeQuery();
            while (rs.next()){
                double money = rs.getDouble(1);
                System.out.println("商品的总价：" + money + "元。");
            }

            if (rs!=null)
                rs.close();
            if (pp!=null)
                pp.close();
            if (connection!=null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void moneyTo(){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWODE);
            String sql = "SELECT * FROM FOOD F ORDER BY F.Price DESC LIMIT 1";
            PreparedStatement pp = connection.prepareStatement(sql);
            ResultSet rs = pp.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String Fname = rs.getString(2);
                double price = rs.getDouble(3);
                int choose = rs.getInt(4);
                System.out.println("食品编号：" + id + "名称：" + Fname + "价格：" + price + "数量：" + choose);
            }

            if (rs!=null)
                rs.close();
            if (pp!=null)
                pp.close();
            if (connection!=null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void sort(){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWODE);
            String sql = "SELECT * FROM FOOD F ORDER BY F.PRICE";
            PreparedStatement pp = connection.prepareStatement(sql);
            ResultSet rs = pp.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String Fname = rs.getString(2);
                double price = rs.getDouble(3);
                int choose = rs.getInt(4);
                System.out.println("食品编号：" + id + "名称：" + Fname + "价格：" + price + "数量：" + choose);
            }

            if (rs!=null)
                rs.close();
            if (pp!=null)
                pp.close();
            if (connection!=null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
