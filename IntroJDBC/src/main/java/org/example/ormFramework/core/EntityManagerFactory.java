package org.example.ormFramework.core;

import org.example.ormFramework.annotation.Column;
import org.example.ormFramework.annotation.Entity;
import org.example.ormFramework.annotation.Id;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.FileNameMap;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntityManagerFactory {
    public static EntityManager create(String dbType, String host, int port, String user, String pass, String dbName, Class<?> mainCLass) throws SQLException, URISyntaxException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection("jdbc:" + dbType + "://" + host + ":" + port + "/" + dbName, user, pass);
        String path = mainCLass.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        String packageName = mainCLass.getPackageName();
        System.out.println(path);
        File rootDir = new File(path + packageName.replace(".", "/"));

        List<Class<?>> classes = new ArrayList<>();
        scan(rootDir, packageName, classes);

        for (Class classInfo : classes) {
            Entity entityInfo = (Entity) classInfo.getAnnotation(Entity.class);
            String tableName = entityInfo.tableName();

            String sql = "CREATE TABLE ";
            sql += tableName + " (\n";
            String primaryKeyDefinition = "";
            for (Field field : classInfo.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    sql += "  " + field.getName() + " int auto_increment,\n";
                    primaryKeyDefinition = "constraint " + tableName + "_pk primary key (" + field.getName() + ")";
                } else if (field.isAnnotationPresent(Column.class)) {
                    Column columnInfo = field.getAnnotation(Column.class);
                    sql += "  " + columnInfo.name() + " " + columnInfo.columnDefinition() + ",\n";
                }
            }
            sql += "  " + primaryKeyDefinition + "\n);";
            System.out.println(sql);
            connection.createStatement().execute(sql);
        }

        return new EntityManagerImpl(connection);
    }

    public static void scan(File dir, String packageName, List<Class<?>> classes) throws ClassNotFoundException {
        for (var file : dir.listFiles()) {
            if (file.isDirectory()) {
                scan(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                Class<?> classInfo = Class.forName(packageName + "." + file.getName().replace(".class", ""));
                if (classInfo.isAnnotationPresent(Entity.class)) {
                    classes.add(classInfo);
                }
            }
        }
    }
}


