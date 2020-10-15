package sparkx.ncms.service;

import sparkx.ncms.dao.Hospital;
import sparkx.ncms.dao.HospitalBed;

import java.util.Collections;
import java.util.List;

public class HospitalService {
    public List<Hospital> getAvailableHospitals(){  //Return available Hospital details
        List<Hospital> availableHospitals = Collections.emptyList();


        return availableHospitals;
    }

    public List<HospitalBed> getAvailableBeds(Hospital hospital){  //Return available Bed details
        List<HospitalBed> availableBeds = Collections.emptyList();


        return availableBeds;
    }

    public void buildHospital(){  //Build a new Hospital

    }
}
