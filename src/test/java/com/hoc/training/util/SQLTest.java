package com.hoc.training.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class SQLTest {

    @Test
    void testInsert() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = """
            insert into authors(id, name, email) 
            values ('id_1', 'Budi', 'budi@mail.com')
        """;

        int update = statement.executeUpdate(sql);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testDelete() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "delete from authors";

        int update = statement.executeUpdate(sql);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testSelect() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "select * from authors";

        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    void testResultSet() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "select * from authors";

        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");

            System.out.println(
                String.join(",", id, name, email)
            );
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    void testPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        
        String sql = "select * from authors where id = ? and email = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "id_1");
        ps.setString(2, "budi@mail.com");
        
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            System.out.println("Data " + rs.getString("name"));
        } else {
            System.out.println("Data not found");
        }

        ps.close();
        connection.close();
    }
}
