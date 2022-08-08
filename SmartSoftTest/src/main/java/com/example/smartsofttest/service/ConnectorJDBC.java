package com.example.smartsofttest.service;

import java.sql.*;

public class ConnectorJDBC {
    private final String url = "jdbc:postgresql://localhost:5432/Numbers";
    private final String user = "root";
    private final String password = "root";


    private Connection connection = null;
    Statement statement = null;

    public ConnectorJDBC ()
    {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error/ PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        //System.out.println("PostgreSQL JDBC Driver successfully connected");
    }


    public String readName() throws SQLException {
        statement = connection.createStatement();
        String output = "";

        ResultSet resultSet = statement.executeQuery(
                " SELECT * " +
                        " FROM  num_data" +
                        " WHERE name like 'zero' ");
        //System.out.println("select send");

        while (resultSet.next())
        {
            String name = resultSet.getString("name");
            String number = resultSet.getString("number");
            output+=name+" "+number;
            // System.out.println("Name: "+name+" Value: "+number);
        }

        return output;
    }

    public String readName(int i) throws SQLException {
        String num = Integer.toString(i);
        String output = "";

        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                " SELECT name " +
                        " FROM  num_data" +
                        " WHERE number = "+num);
        while (resultSet.next())
        {
            String name = resultSet.getString("name");
            output+= name;
        }

        return output;
    }

    public String readNumber(String name) throws SQLException {
        String output = "";
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(
                " SELECT number " +
                        " FROM  num_data" +
                        " WHERE name like '" + name.toLowerCase()+"'");
        while (resultSet.next())
        {
            output+= resultSet.getString("number");
        }

        return output;
    }

    public boolean writeLog(int type, String input, String output) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO logs (TYPE, INNER_VALUE, OUTER_VALUE, CREATED) VALUES (?, ?, ?, ?)");
            String stype = (type == 1) ? "StringToNumber" : "NumberToString";

            statement.setString(1, stype);
            statement.setString(2, input);
            statement.setString(3, output);
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            statement.close();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

    }
}
