package at.leonschloemmer.carwarehouse.databaseTest;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class DatabaseTest {

    public static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/db";
    static final String USER = "app";
    static final String PASSWORD = "app";
    private static Connection connection;

    @BeforeClass
    public static void initJdbc(){
        try {
            Class.forName(DRIVER_STRING);
            connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connecting to database impossible:\n" + e.getMessage() + "\n");
            System.exit(1);
        }
    }

    @Test
    public void testTablesContained() {
        DatabaseMetaData databaseMetaData;
        try {
            databaseMetaData = connection.getMetaData();

            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = null;
            String[] types = null;

            ResultSet tables = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);
            StringBuilder sb = new StringBuilder();
            while(tables.next()) {
                sb.append(tables.getString(3).toLowerCase());
                sb.append(";");
            }
            String allTables = sb.toString();
            assertThat(allTables, containsString("car"));
            assertThat(allTables, containsString("customer"));
            assertThat(allTables, containsString("supercar"));
            assertThat(allTables, containsString("warehousemanager_warehouse"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void teardownJdbc() {
        try {
            if (connection != null || !connection.isClosed()) {
                connection.close();
                System.out.println("Goodbye!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
