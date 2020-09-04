package sparkx.ncms.dao;

public class HospitalBed {
    private String bedID;
    private String hospitalID;
    private String patientID;

    public HospitalBed(String bedID, String hospitalID, String patientID) {
        this.bedID = bedID;
        this.hospitalID = hospitalID;
        this.patientID = patientID;
    }

    public String getBedID() {
        return bedID;
    }

    public void setBedID(String bedID) {
        this.bedID = bedID;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
}
