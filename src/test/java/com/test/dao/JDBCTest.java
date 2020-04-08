package com.test.dao;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rsl = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://39.100.248.8:3306/ssmdemo";
            String user = "liab";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from tb_user where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, 1L);
            rsl = preparedStatement.executeQuery();
            while (rsl.next()) {
                System.out.println(rsl.getString("user_name"));
                System.out.println(rsl.getString("password"));
                System.out.println(rsl.getInt("age"));
                System.out.println(rsl.getDate("birthday"));
                System.out.println(rsl.getTimestamp("created"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (rsl != null) {
                rsl.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

