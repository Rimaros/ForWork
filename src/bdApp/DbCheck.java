package bdApp;

class DbCheck {

    void oracleCheck(){

        System.out.println("-------- Oracle JDBC Connection Testing ------");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver found");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
        }
    }

    void mySQLCheck(){

        System.out.println("-------- MySQL JDBC Connection Testing ------");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver found");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }
    }
}
