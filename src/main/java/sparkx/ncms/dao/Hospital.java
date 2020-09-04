package sparkx.ncms.dao;

public class Hospital {
    private String hospitalID;
    private String name;
    private String district;
    private String coordinateX;
    private String coordinateY;
    private String buildDate;

    public Hospital(String hospitalID, String name, String district, String coordinateX, String coordinateY, String buildDate) {
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

    public String getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
    }

    public String getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }
}
