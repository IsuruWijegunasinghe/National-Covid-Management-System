package sparkx.ncms.dao;

public class PatientQueue {
    private String queueID;
    private String patientID;

    public PatientQueue(String queueID, String patientID) {
        this.queueID = queueID;
        this.patientID = patientID;
    }

    public String getQueueID() {
        return queueID;
    }

    public void setQueueID(String queueID) {
        this.queueID = queueID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
}
