package org.example;

import java.sql.*;

public class Bd {
    String jdbcURL = "yourUrl";
    String username = "postgres";
    String password = "yourpassword";
    Statement statement;
    Connection connection;

    public int openConnection() throws Exception {

        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");


            connection = DriverManager.getConnection(jdbcURL, username, password);
            statement = connection.createStatement();

            return 0;

        }
        catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public int makeTableOperations() {


        // Create a table if not exists
        String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(50) UNIQUE,countRUB FLOAT, countUSD FLOAT , countCNY FLOAT)";
        String createTableSQL = "CREATE TABLE IF NOT EXISTS operations (id SERIAL PRIMARY KEY, name VARCHAR(50), convertfrom VARCHAR(50),countfrom FLOAT, convertto VARCHAR(50), countto FLOAT)";
        try {
            statement.execute(createTableSQL);
            statement.execute(createUsersTableSQL);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }


    // Insert a row into the table
    public int insertNewOperation(String name, String convertfrom, String convertto, Float countfrom, Float countto) {
        String insertSQL =
                "INSERT INTO operations (name, convertfrom, countfrom, convertto, countto) VALUES ('"+name+"', '"+convertfrom+"', '"+countfrom+"', '"+convertto+"', '"+countto+"')";
        try {
            statement.executeUpdate(insertSQL);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    public void showData() {
        String selectSQL = "SELECT * FROM operations";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id")
                                + ", Name: "
                                + resultSet.getString("name")
                                + ", From: "
                                + resultSet.getFloat("countfrom")+" "
                                + resultSet.getString("convertfrom")
                                + " To: "
                                +resultSet.getFloat("countto")+" "
                                + resultSet.getString("convertto"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){

        // Close the connection
        try {
            connection.close();
            System.out.println("Connection closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public Float getValute(String personName,String valuteName) throws SQLException {
        String get = "SELECT * FROM users WHERE name = '"+personName+"'";
        ResultSet count = null;
        Float val = 100F;
        try {
            count = statement.executeQuery(get);
            while(count.next()){
                val = count.getFloat(valuteName);
            }
        }
        catch (Exception e){
            return 100F;
        }
        return val;
    }
    public void updateData(String personName, Float countRUB, Float countUSD, Float countCNY){
        String query = "INSERT INTO users (name,countRUB,countUSD,countCNY) VALUES ('"+personName+"','"+countRUB.toString()+"','"+countUSD.toString()+"','"+countCNY.toString()+"')ON CONFLICT" +
                "(name) DO UPDATE SET countRUB = '"+countRUB.toString()+"', countUSD = '"+countUSD.toString()+"', countCNY ='"+countCNY.toString()+"'";
        System.out.println(query);
        try {
            statement.executeQuery(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }




}
