package sparkx.ncms.dao;

public class Doctor {
    private String doctorID;
    private String name;
    private String hospitalID;
    private boolean isDirector;

    public Doctor(String doctorID, String name, String hospitalID, boolean isDirector) {
        this.doctorID = doctorID;
        this.name = name;
        this.hospitalID = hospitalID;
        this.isDirector = isDirector;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public boolean isDirector() {
        return isDirector;
    }

    public void setDirector(boolean director) {
        isDirector = director;
    }
}
