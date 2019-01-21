package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class util {
    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    static {
        Properties prop = new Properties();
        InputStream inputStream = util.class.getResourceAsStream("jdbc.properties");
        try {
            prop.load(inputStream);
            DRIVER=prop.getProperty("jdbc.mysql.driver");
            URL=prop.getProperty("jdbc.mysql.url");
            USER
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
