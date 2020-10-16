package sparkx.ncms.dto;

public class PatientDto {
    private String patientID;
    private String firstName;
    private String lastName;
    private String district;
    private int coordinateX;
    private int coordinateY;
    private String severityLevel;
    private String gender;
    private String contactNo;
    private String email;
    private int age;
    private String admitDate;
    private String admittedBy;
    private String dischargeDate;
    private String dischargedBy;

    public PatientDto() {

    }

    public PatientDto(String patientID, String firstName, String lastName, String district, int coordinateX, int coordinateY, String severityLevel, String gender, String contactNo, String email, int age, String admitDate, String admittedBy, String dischargeDate, String dischargedBy) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.district = district;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.severityLevel = severityLevel;
        this.gender = gender;
        this.contactNo = contactNo;
        this.email = email;
        this.age = age;
        this.admitDate = admitDate;
        this.admittedBy = admittedBy;
        this.dischargeDate = dischargeDate;
        this.dischargedBy = dischargedBy;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(String admitDate) {
        this.admitDate = admitDate;
    }

    public String getAdmittedBy() {
        return admittedBy;
    }

    public void setAdmittedBy(String admittedBy) {
        this.admittedBy = admittedBy;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getDischargedBy() {
        return dischargedBy;
    }

    public void setDischargedBy(String dischargedBy) {
        this.dischargedBy = dischargedBy;
    }
}
