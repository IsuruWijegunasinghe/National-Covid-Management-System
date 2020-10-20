package sparkx.ncms.dao;

public class Hospital {
    private String hospitalID;
    private String name;
    private String district;
    private int coordinateX;
    private int coordinateY;
    private String buildDate;

    public Hospital() {
    }

    public Hospital(String hospitalID, String name, String district, int coordinateX, int coordinateY, String buildDate) {
        this.hospitalID = hospitalID;
        this.name = name;
        this.district = district;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.buildDate = buildDate;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }
}
