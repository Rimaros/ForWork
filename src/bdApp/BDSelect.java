package bdApp;

import java.sql.*;

class BDSelect {
    String oracleSelect(String request) throws SQLException {
        System.out.println("-------- Oracle DB Select ------");
        BDData bdData = new BDData();
        String dbURL = bdData.getDbURL();
        String username = bdData.getUsername();
        String password = bdData.getPassword();
        ResultSet resultSet = null;
        String result = null;
        Connection connection;
        Statement statement = null;

        connection = DriverManager.getConnection(dbURL, username, password);

        if (connection != null) {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(request);
            while (resultSet.next()){
                result = resultSet.getString("sql_tags");
            }
        } else System.out.println("Connection is NULL");
        close(statement, connection, resultSet);
        return result;
    }

    String mySQLSelect(String request){
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        close(statement, connection, resultSet);
        return null;
    }

    private void close(Statement statement, Connection connection, ResultSet resultSet){
        try {
            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
