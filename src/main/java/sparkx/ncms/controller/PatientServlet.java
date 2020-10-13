package sparkx.ncms.controller;

import sparkx.ncms.dao.Hospital;
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

@WebServlet(name = "PatientServlet")
public class PatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String district = req.getParameter("district");
        int coordinateX = Integer.parseInt(req.getParameter("coordinateX"));
        int coordinateY = Integer.parseInt(req.getParameter("coordinateY"));
        String gender = req.getParameter("gender");
        String contactNo = req.getParameter("contactNo");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        */
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

        PatientService patientService = new PatientService();
        patientService.patient.setFirstName(firstName);
        patientService.patient.setLastName(lastName);
        patientService.patient.setDistrict(district);
        patientService.patient.setCoordinateX(coordinateX);
        patientService.patient.setCoordinateY(coordinateY);
        patientService.patient.setGender(gender);
        patientService.patient.setContactNo(contactNo);
        patientService.patient.setEmail(email);
        patientService.patient.setAge(age);

        patientService.savePatient();

        HospitalService hospitalService = new HospitalService();
        List<Hospital> availableHospitals = hospitalService.getAvailableHospitals();
        Hospital assignedHospital = patientService.assignHospital(availableHospitals);

        // Test
        PrintWriter out = resp.getWriter();
        out.println("Patient servlet");
    }
}
