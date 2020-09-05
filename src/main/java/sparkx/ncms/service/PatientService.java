package sparkx.ncms.service;

import sparkx.ncms.dao.Hospital;
import sparkx.ncms.dao.Patient;
import sparkx.ncms.repository.PatientRepo;

import java.util.List;

public class PatientService {
    public Patient patient = new Patient();

    public void savePatient(){
        PatientRepo patientRepo = new PatientRepo();

        String firstName = patient.getFirstName();
        String lastName = patient.getLastName();
        String district = patient.getDistrict();
        int coordinateX = patient.getCoordinateX();
        int coordinateY = patient.getCoordinateY();
        String gender = patient.getGender();
        String contactNo = patient.getContactNo();
        String email = patient.getEmail();
        int age = patient.getAge();

        patientRepo.insertQuery(firstName, lastName, district, coordinateX, coordinateY, gender, contactNo, email, age);
    }

    public Hospital assignHospital(List<Hospital> availableHospitals){
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
}
