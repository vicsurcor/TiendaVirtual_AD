package es.masanz.tiendaVirtual.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class TransactionManager {

    private final String properties = "TiendaVirtual/resources/db/db.properties";

    public String name;
    public Connection connection;

    public TransactionManager(String name){

        this.name = name;
        connect();

    }

    public void connect() {

        try {

            Properties props = new Properties();
            FileInputStream in = new FileInputStream(properties);
            props.load(in);
            in.close();

            String url = props.getProperty("DB_URL");
            String user = props.getProperty("DB_USER");
            String password = props.getProperty("DB_PASSWORD");
            connection = DriverManager.getConnection(url + name, user, password);

        } catch (IOException | SQLException e) {
            System.out.println("The database doesnt exist");
        }

    }
    public boolean testOtherDatabase() {
        ResultSet rs = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void testTable(String tableName) {


        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT *\n" +
                    "FROM INFORMATION_SCHEMA.TABLES\n" +
                    "WHERE TABLE_SCHEMA = ?\n" +
                    "AND TABLE_NAME = ?\n");
            stmt.setString(1, name);
            stmt.setString(2, tableName);
            ResultSet tables = stmt.executeQuery();
            if (tables.next()) {
                System.out.println("The table exists.");
            } else {
                System.out.println("The table does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ResultSet executeSqlQuery(String query){
        ResultSet rs = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean executeSqlUpdate(String update) {
        try {

            PreparedStatement stmt = connection.prepareStatement(update);
            stmt.executeLargeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
