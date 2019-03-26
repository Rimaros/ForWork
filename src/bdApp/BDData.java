package bdApp;
/**
 * Класс данных коннекта к базе
 */
class BDData {

    private String password;
    private String username;
    private String dbURL;

    BDData(){
        password = "QWERTY_1234";
        username = "MAXIM";
        dbURL = "jdbc:oracle:thin:@host:port:SID";
    }

    String getDbURL() {
        return dbURL;
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }
}
