package es.masanz.tiendaVirtual.db;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;

import net.sf.jsqlparser.statement.update.Update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class TransactionManager {

    private final String properties = "resources/db.properties";

    public String name;
    public static Connection connection;

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
            Statement statement = (Statement) CCJSqlParserUtil.parse(update);
            if (statement instanceof Update) {
                Update updateStatement = (Update) statement;
                List<Expression> expressions = updateStatement.getExpressions();
                PreparedStatement stmt = connection.prepareStatement(update);
                for (int i = 0; i < expressions.size(); i++) {
                    stmt.setString(i + 1, expressions.get(i).toString());
                }
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            } else if (statement instanceof Insert) {
                Insert insertStatement = (Insert) statement;
                String values = insertStatement.toString().split("VALUES")[1];
                if (!values.contains("),")) {
                    String[] expressions = values.replace("(", "").replace(")", "").split(",");;
                    PreparedStatement stmt = connection.prepareStatement(update);
                    for (int i = 0; i < expressions.length; i++) {
                        // Handle expressions
                        stmt.setString(i + 1, expressions[i].trim());
                    }
                } else if (values.contains("),")) {
                    // Handle multi-expressions
                    String[] expressions = values.split("),");

                }
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
            else if (statement instanceof Delete) {
                Delete deleteStatement = (Delete) statement;
                Expression expressions = deleteStatement.getWhere();
                PreparedStatement stmt = connection.prepareStatement(update);
                stmt.setString( 1, expressions.toString());

                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (JSQLParserException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public  void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
