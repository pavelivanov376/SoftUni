package org.example;

import jdk.jshell.spi.SPIResolutionException;
import org.example.com.example.entity.Employee;
import org.example.ormFramework.core.EntityManager;
import org.example.ormFramework.core.EntityManagerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Properties;

public class Main {
    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost:3306/";
    private static final String TABLE_NAME = "minions_db";

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, URISyntaxException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class.forName(Driver.class.getName());
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123");

        Connection connection = DriverManager.getConnection(CONNECTION_STRING + TABLE_NAME, "root", "123");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM minions WHERE id < ?");
        int maxId = 10;

        preparedStatement.setInt(1, maxId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d %n", resultSet.getString("name"), resultSet.getInt("age"));
        }

        EntityManager entityManager = EntityManagerFactory.create("mariadb", "localhost", 3306, "root", "123", "minions_db", Main.class);
        Employee byId = entityManager.findById(2, Employee.class);
    }

}