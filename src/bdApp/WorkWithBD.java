package bdApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WorkWithBD {

    private String resultField; // самый первый селект в dual
    private String requestToBD; // текст запроса для поиска значения строки sql_tags
    private String driverName; // имя драйвера

    // основной класс обработки всех операций над данными
    WorkWithBD(String valueFromDual, String request, String driver) throws SQLException {
        this.resultField = valueFromDual;
        this.requestToBD = request;
        this.driverName = driver;
        String stringForParse;
        checkDriver(driverName); // проверка наличия драйвера
        updateBD(resultField, driverName); // апдейт базы инсертом
        stringForParse = selectBd(requestToBD, driverName); // вытягиваем из 2 задания значение поля sql_tags
        parseResult(stringForParse); // парсер поля
    }

    private void updateBD(String resultField, String driverName) throws SQLException {
        BdUpdate bdUpdate = new BdUpdate();

        String selectText;
        String requestToBDFinal;

        selectText = "DB=dev42 USER=MAXIM PASSWORD=”QWERTY_1234” SELCTTEXT=”" + resultField + "”";
        requestToBDFinal = "Insert into db.table(i1,i2,sql_tags) select 1,1,“" + selectText + "”";

        // обрабатываем апдейт с привязкой к базе
        switch (driverName){
            case "oracle":
                bdUpdate.oracleUpdate(requestToBDFinal);
                break;
            case "mySQL":
                bdUpdate.mySQLUpdate();
                break;
        }
    }

    private String selectBd (String requestToBD, String driverName) throws SQLException {
        BDSelect bdSelect = new BDSelect();
        String resultRequest = null;

        // обрабатываем селект с привязкой к базе
        switch (driverName){
            case "oracle":
                resultRequest = bdSelect.oracleSelect(requestToBD);
                break;
            case "mySQL":
                resultRequest = bdSelect.mySQLSelect(requestToBD);
                break;
        }
        return resultRequest;
    }

    // определяем необходимые параметры для парсера
    private void parseResult(String stringForParse){
        ArrayList<String> parameters = new ArrayList<>();

        String dbName = "DB";
        String user = "USER";
        String pass = "PASSWORD";
        String text = "SELCTTEXT";

       parameters.add(dbName);
       parameters.add(user);
       parameters.add(pass);
       parameters.add(text);

       returnParameters(parameters, stringForParse);
    }

    // сам парсер
    private void returnParameters(ArrayList<String> parametersList, String selectString){
        HashMap<String, String> resultMap = new HashMap<>();
        String START; // A literal character in regex
        String END; // A literal character in regex
        String result = null;

        // парсим строку
        for (String aParametersList : parametersList) {
            if (aParametersList.equalsIgnoreCase("DB") || aParametersList.equalsIgnoreCase("user")) {
                START = aParametersList + "=";
                END = " ";
            } else {
                START = aParametersList + "=”";
                END = "”";
            }
            String patterns = START + "\\w+" + END;

            Pattern pattern = Pattern.compile(patterns);
            Matcher matcher = pattern.matcher(selectString);
            while(matcher.find()) {
                result = matcher.group().replace(START, "").replace(END, "");
            }
            resultMap.put(aParametersList, result); // результаты парсера засовываем в мапу
        }
        // вывод содержимого мапы
        System.out.println("DB = " + resultMap.get("DB"));
        System.out.println("USER = " + resultMap.get("USER"));
        System.out.println("PASSWORD = " + resultMap.get("PASSWORD"));
        System.out.println("SELCTTEXT = " + resultMap.get("SELCTTEXT"));
    }

    private void checkDriver(String driverName){
        DbCheck dbCheck = new DbCheck();

        switch (driverName){
            case "oracle":
                dbCheck.oracleCheck();
                break;
            case "mySQL":
                dbCheck.mySQLCheck();
                break;
        }
    }
}
