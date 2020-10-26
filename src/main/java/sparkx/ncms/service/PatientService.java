package sparkx.ncms.service;

import sparkx.ncms.dao.Hospital;
import sparkx.ncms.dao.HospitalBed;
import sparkx.ncms.dao.Patient;
import sparkx.ncms.dto.PatientCount;
import sparkx.ncms.dto.PatientDto;
import sparkx.ncms.repository.PatientRepo;

import java.util.List;

public class PatientService {
    private PatientRepo patientRepo;

    public PatientService() {
        patientRepo = new PatientRepo();
    }

    public String savePatient(Patient patient){
        String patientID = null;

        String firstName = patient.getFirstName();
        String lastName = patient.getLastName();
        String district = patient.getDistrict();
        int coordinateX = patient.getCoordinateX();
        int coordinateY = patient.getCoordinateY();
        String gender = patient.getGender();
        String contactNo = patient.getContactNo();
        String email = patient.getEmail();
        int age = patient.getAge();

        patientID = patientRepo.insertPatient(firstName, lastName, district, coordinateX, coordinateY, gender, contactNo, email, age);
        return patientID;
    }

    public Boolean updatePatient(String patientID, String doctorID, String severityLevel){
        return patientRepo.updatePatient(patientID, doctorID, severityLevel);
    }

    public Hospital assignHospital(Patient patient, List<Hospital> availableHospitals){
        int patientCoordinateX = patient.getCoordinateX();
        int patientCoordinateY = patient.getCoordinateY();

        double shortestDistance = Double.POSITIVE_INFINITY;
        Hospital nearestHospital = null;

        for (Hospital hospital : availableHospitals){
            int hospitalCoordinateX = hospital.getCoordinateX();
            int hospitalCoordinateY = hospital.getCoordinateY();

            double distanceX = patientCoordinateX - hospitalCoordinateX;
            double distanceY = patientCoordinateY - hospitalCoordinateY;
            double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

            if (distance < shortestDistance){
                shortestDistance = distance;
                nearestHospital = hospital;
            }
        }
        return nearestHospital;
    }

    public List<PatientCount> getPatientCount(){
        List<PatientCount> patientCount = patientRepo.getPatientCount();
        return patientCount;
    }

    public PatientDto getPatientInfo(String patientID){
        PatientDto patientDto = patientRepo.selectPatient(patientID);
        return patientDto;
    }

    public List<PatientDto> getAllPatient(){
        List<PatientDto> patientList = patientRepo.selectAllPatient();
        return patientList;
    }
}
