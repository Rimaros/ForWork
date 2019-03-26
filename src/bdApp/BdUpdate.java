package bdApp;

import java.sql.*;

class BdUpdate {

    void oracleUpdate(String request) throws SQLException {

        System.out.println("-------- Oracle DB Update ------");
        BDData bdData = new BDData();
        String dbURL = bdData.getDbURL();
        String username = bdData.getUsername();
        String password = bdData.getPassword();
        Connection connection;
        Statement statement = null;
        connection = DriverManager.getConnection(dbURL, username, password);

        if (connection != null) {
            statement = connection.createStatement();
            statement.executeUpdate(request);
        } else System.out.println("Connection is NULL");
        close(statement, connection);
    }

    void mySQLUpdate(){
        System.out.println("-------- Realisation is not work ------");
        Connection connection = null;
        Statement statement = null;
        close(statement, connection);
    }

    private void close(Statement statement, Connection connection){
        try {
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
