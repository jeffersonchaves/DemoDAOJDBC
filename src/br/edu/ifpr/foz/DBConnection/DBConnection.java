package br.edu.ifpr.foz.DBConnection;

import br.edu.ifpr.foz.DBExceptions.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection connection = null;

    public static Connection getConnection(){

        try {
            Properties properties = loadProperties();
            String url = properties.getProperty("dburl");

            connection = DriverManager.getConnection(url, properties);

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

        return connection;
    }

    private static Properties loadProperties() {

        try (FileInputStream fileStream = new FileInputStream("db.properties")){

            Properties properties = new Properties();
            properties.load(fileStream);
            return properties;

        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }
}
