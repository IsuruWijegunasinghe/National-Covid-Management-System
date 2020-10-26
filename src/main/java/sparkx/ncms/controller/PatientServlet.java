package sparkx.ncms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import sparkx.ncms.dao.Hospital;
import sparkx.ncms.dao.HospitalBed;
import sparkx.ncms.dao.Patient;
import sparkx.ncms.dto.PatientCount;
import sparkx.ncms.dto.PatientDto;
import sparkx.ncms.service.HospitalService;
import sparkx.ncms.service.PatientService;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
    private PatientService patientService = new PatientService();
    private HospitalService hospitalService = new HospitalService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        switch (req.getPathInfo()){
            case "/PatientCount":
                try {
                    List<PatientCount> patientCount = patientService.getPatientCount();
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(patientCount);
                    //out.println(patientCount);
                    Util.sendResponse(resp,jsonString);
                }catch (Exception e){
                    e.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
                break;

            case "/PatientInfo":
                try {
                    System.out.println(req.getParameter("patientID"));
                    PatientDto patientDto = patientService.getPatientInfo(req.getParameter("patientID"));
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(patientDto);
                    out.println(patientDto.toString());
                    Util.sendResponse(resp,jsonString);
                }catch (Exception e){
                    e.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }

                break;

            case "/AllPatientInfo":
                try{
                    List<PatientDto> patientList = patientService.getAllPatient();
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(patientList);
                    //out.println(patientCount);
                    Util.sendResponse(resp,jsonString);
                    //out.println(patientList);

                    //out.println("Patient servlet GET AllPatientInfo");
                }catch (Exception e){
                    e.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }

                break;

        }

        // Test
        //out.println("Patient servlet GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Util.getJsonObject(req);
        //System.out.println(jsonObject);

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
        //System.out.println(patient);

        try {
            String patientID = patientService.savePatient(patient);
            Hospital assignedHospital;
            String assignedBed;
            if(patientID.equals(null)){
                assignedHospital = null;
                assignedBed = null;
            }else{
                List<Hospital> availableHospitals = hospitalService.getAvailableHospitals();
                assignedHospital = patientService.assignHospital(patient, availableHospitals);
                assignedBed = hospitalService.getAvailableBeds(assignedHospital.getHospitalID()).get(0);
                hospitalService.updateBed(patientID,assignedBed,"0");
            }

            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("patientID",patientID);
            response.add("assignedHospital", assignedHospital.getName());
            response.add("assignedBed", assignedBed);
            PrintWriter writer = resp.getWriter();
            writer.print(response.build());
            System.out.println(response.toString());

        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }

        /*
        List<Hospital> availableHospitals = hospitalService.getAvailableHospitals();

        if (availableHospitals.isEmpty()){

        }

        Hospital assignedHospital = patientService.assignHospital(patient, availableHospitals);
        List<HospitalBed> availableHospitalBeds = hospitalService.getAvailableBeds(assignedHospital);

        Random rand = new Random();
        HospitalBed assignedHospitalBed = availableHospitalBeds.get(rand.nextInt(availableHospitalBeds.size()));

         */

        // Test
        /*
        System.out.println("Patient Registered");
        PrintWriter out = resp.getWriter();
        out.println("Patient servlet POST");
        Util.sendResponse(resp,"POST");
        */
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        String patientId = req.getParameter("patientId");
        String doctorId = req.getParameter("doctorId");
        String severityLevel = req.getParameter("severityLevel");

        try {
            patientService.updatePatient(patientId, doctorId, severityLevel);
            // Need to add response
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }
         */
    }
}
