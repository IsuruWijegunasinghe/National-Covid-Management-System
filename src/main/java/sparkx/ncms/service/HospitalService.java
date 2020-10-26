package sparkx.ncms.service;

import sparkx.ncms.dao.Hospital;
import sparkx.ncms.dao.HospitalBed;
import sparkx.ncms.dto.PatientDto;
import sparkx.ncms.repository.HospitalBedRepo;
import sparkx.ncms.repository.HospitalRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HospitalService {
    public List<Hospital> getAvailableHospitals(){  //Return available Hospital details
        HospitalBedRepo hospitalBedRepo = new HospitalBedRepo();
        List<String> availableHospitals = hospitalBedRepo.hospitalsWithVacantBeds();

        HospitalRepo hospitalRepo = new HospitalRepo();
        List<Hospital> availableHospitalList = new ArrayList<>();
        for (String hospital : availableHospitals){
            Hospital hospitalObject = hospitalRepo.selectHospital(hospital);
            availableHospitalList.add(hospitalObject);
        }

        return availableHospitalList;
    }

    public List<String> getAvailableBeds(String hospitalID){  //Return available Bed details
        HospitalRepo hospitalRepo = new HospitalRepo();
        List<String> availableBeds = hospitalRepo.getAvailableBed(hospitalID);

        return availableBeds;
    }

    public void buildHospital(){  //Build a new Hospital

    }

    public void updateBed(String patientID, String bedID, String severityLevel){
        HospitalBedRepo hospitalBedRepo = new HospitalBedRepo();
        hospitalBedRepo.updateBed(patientID,bedID,severityLevel);
    }
}
