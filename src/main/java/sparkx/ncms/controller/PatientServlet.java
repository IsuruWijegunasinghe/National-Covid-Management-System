package sparkx.ncms.controller;

import sparkx.ncms.dao.Hospital;
import sparkx.ncms.dao.HospitalBed;
import sparkx.ncms.dao.Patient;
import sparkx.ncms.service.HospitalService;
import sparkx.ncms.service.PatientService;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

@WebServlet(name = "PatientServlet")
public class PatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Test
        PrintWriter out = resp.getWriter();
        out.println("Patient servlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Util.getJsonObject(req);
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        String district = jsonObject.getString("district");
        int coordinateX = Integer.parseInt(jsonObject.getString("coordinateX"));
        int coordinateY = Integer.parseInt(jsonObject.getString("coordinateY"));
        String gender = jsonObject.getString("gender");
        String contactNo = jsonObject.getString("contactNo");
        String email = jsonObject.getString("email");
        int age = Integer.parseInt(jsonObject.getString("age"));

        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setDistrict(district);
        patient.setCoordinateX(coordinateX);
        patient.setCoordinateY(coordinateY);
        patient.setGender(gender);
        patient.setContactNo(contactNo);
        patient.setEmail(email);
        patient.setAge(age);

        PatientService patientService = new PatientService();
        patientService.savePatient(patient);

        HospitalService hospitalService = new HospitalService();
        List<Hospital> availableHospitals = hospitalService.getAvailableHospitals();

        if (availableHospitals.isEmpty()){

        }

        Hospital assignedHospital = patientService.assignHospital(patient, availableHospitals);
        List<HospitalBed> availableHospitalBeds = hospitalService.getAvailableBeds(assignedHospital);

        Random rand = new Random();
        HospitalBed assignedHospitalBed = availableHospitalBeds.get(rand.nextInt(availableHospitalBeds.size()));

        // Test
        PrintWriter out = resp.getWriter();
        out.println("Patient servlet");
    }
}
