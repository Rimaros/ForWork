package bdApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WorkWithBD {

    private String resultField;
    private String requestToBD;
    private String driverName;

    WorkWithBD(String valueFromDual, String request, String driver) throws SQLException {
        this.resultField = valueFromDual;
        this.requestToBD = request;
        this.driverName = driver;
        String stringForParse;
        checkDriver(driverName);
        updateBD(resultField, driverName);
        stringForParse = selectBd(requestToBD, driverName);
        parseResult(stringForParse);
    }

    private void updateBD(String resultField, String driverName) throws SQLException {
        BdUpdate bdUpdate = new BdUpdate();

        String selectText;
        String requestToBDFinal;

        selectText = "DB=dev42 USER=MAXIM PASSWORD=”QWERTY_1234” SELCTTEXT=”" + resultField + "”";
        requestToBDFinal = "Insert into db.table(i1,i2,sql_tags) select 1,1,“" + selectText + "”";

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

    private void returnParameters(ArrayList<String> parametersList, String selectString){
        HashMap<String, String> resultMap = new HashMap<>();
        String START; // A literal character in regex
        String END; // A literal character in regex
        String result = null;

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
            resultMap.put(aParametersList, result);
        }
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
