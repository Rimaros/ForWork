package bdApp;

import java.sql.SQLException;

public class StartApp {

    public static void main(String[] args) throws SQLException {
        String valueFromDual;
        String selectFromBD;
        String driverName;

        valueFromDual = "select “maxI1”, max(i1) from dual";
        selectFromBD = "Select sql_tags from db.table where i1=1 and i2=1";
        driverName = "oracle";
        new WorkWithBD(valueFromDual, selectFromBD, driverName);
    }
}