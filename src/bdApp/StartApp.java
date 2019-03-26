package bdApp;

import java.sql.SQLException;

public class StartApp {

    public static void main(String[] args) throws SQLException {
        String valueFromDual; // строка запроса для первой таблицы
        String selectFromBD; // селект для поиска значения строки sql_tags
        String driverName; // имя драйвера, который будет использоваться для работы с базой

        valueFromDual = "select “maxI1”, max(i1) from dual";
        selectFromBD = "Select sql_tags from db.table where i1=1 and i2=1";
        driverName = "oracle";
        new WorkWithBD(valueFromDual, selectFromBD, driverName);
    }
}