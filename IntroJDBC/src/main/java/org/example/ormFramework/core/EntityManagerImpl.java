package org.example.ormFramework.core;

import org.example.ormFramework.annotation.Column;
import org.example.ormFramework.annotation.Entity;
import org.example.ormFramework.annotation.Id;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class EntityManagerImpl implements EntityManager {

    private final Connection connection;

    public EntityManagerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T> T findById(int id, Class<T> type) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        String tableName = type.getAnnotation(Entity.class).tableName();

        String idColumnName = Arrays.stream(type.getDeclaredFields()).filter(f -> f.isAnnotationPresent(Id.class)).findFirst().orElseThrow().getName();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE " + idColumnName + " = ?");

        preparedStatement.setInt(1, id);

        T entity = (T) type.getConstructors()[0].newInstance();


        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            return null;
        }

        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (field.getType().equals(String.class)) {
                    String setterName = "set" + ((field.getName().charAt(0) + "").toUpperCase()) + field.getName().substring(1);
                    String string = resultSet.getString(column.name());
                    type.getMethod(setterName, String.class).invoke(entity, string);
                } else {
                    String setterName = "set" + ((field.getName().charAt(0) + "").toUpperCase()) + field.getName().substring(1);
                    int string = resultSet.getInt(column.name());
                    type.getMethod(setterName, field.getType()).invoke(entity, string);
                }

            } else if (field.isAnnotationPresent(Id.class)) {
                String setterName = "set" + ((field.getName().charAt(0) + "").toUpperCase()) + field.getName().substring(1);
                type.getMethod(setterName, int.class).invoke(entity, id);

            }
        }
        return entity;
    }
}
