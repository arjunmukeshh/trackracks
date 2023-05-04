public enum LoginInfo {
    USER1("jdbc:mysql://localhost:3306/trackracks","root", "1234");

    private final String username;
    private final String password;
    private final String connectionIP;

    LoginInfo(String connectionIP, String username, String password) {
        this.connectionIP = connectionIP;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConnectionIP(){
        return connectionIP;
    }
}