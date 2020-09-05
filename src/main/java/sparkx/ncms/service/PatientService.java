package sparkx.ncms.service;

import sparkx.ncms.dao.Patient;
import sparkx.ncms.repository.PatientRepo;

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
}
