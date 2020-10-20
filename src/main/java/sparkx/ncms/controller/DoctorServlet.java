package sparkx.ncms.controller;

import sparkx.ncms.repository.PatientRepo;
import sparkx.ncms.service.PatientService;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;

@WebServlet(name = "DoctorServlet")
public class DoctorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Util.getJsonObject(req);
        String patientID = jsonObject.getString("patientID");
        String doctorID = jsonObject.getString("doctorID");
        String severityLevel = jsonObject.getString("severityLevel");

        PatientService patientService = new PatientService();
        patientService.updatePatient(patientID, doctorID,severityLevel);
    }
}
