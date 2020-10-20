package sparkx.ncms.service;

import sparkx.ncms.dao.Hospital;
import sparkx.ncms.repository.HospitalRepo;
import sparkx.ncms.repository.PatientQueueRepo;

import java.awt.*;
import java.util.List;

public class MoHService {
    public void addNewHospital(Hospital hospital){
        HospitalRepo hospitalRepo = new HospitalRepo();
        String hospitalID = hospital.getHospitalID();
        String name = hospital.getName();
        String district = hospital.getDistrict();
        int coordinateX = hospital.getCoordinateX();
        int coordinateY = hospital.getCoordinateY();

        hospitalRepo.addNewHospital(hospitalID, name, district, coordinateX, coordinateY);
    }

    public List<String> getQueuePatients(){
        PatientQueueRepo patientQueueRepo = new PatientQueueRepo();
        return patientQueueRepo.getQueuePatients();
    }
}
