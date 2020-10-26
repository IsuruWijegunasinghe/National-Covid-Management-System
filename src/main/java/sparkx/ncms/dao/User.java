package sparkx.ncms.dao;

public class User {
    private String username;
    private String password;
    private String name;
    private int isMoH;
    private int isHospital;

    public User(String username, String password, String name, int isMoH, int isHospital) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.isMoH = isMoH;
        this.isHospital = isHospital;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsMoH() {
        return isMoH;
    }

    public void setIsMoH(int isMoH) {
        this.isMoH = isMoH;
    }

    public int getIsHospital() {
        return isHospital;
    }

    public void setIsHospital(int isHospital) {
        this.isHospital = isHospital;
    }
}
