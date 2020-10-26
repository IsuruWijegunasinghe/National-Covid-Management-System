package sparkx.ncms.dto;

public class PatientCount {
    private String hospitalID;
    private String district;
    private int patientCount;

    public PatientCount(String hospitalID, String district, int patientCount) {
        this.hospitalID = hospitalID;
        this.district = district;
        this.patientCount = patientCount;
    }

    public String getDitrict() {
        return district;
    }

    public void setDitrict(String ditrict) {
        this.district = ditrict;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public int getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }
}
