package sparkx.ncms.controller;

import sparkx.ncms.repository.PatientRepo;
import sparkx.ncms.service.PatientService;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        boolean success = false;
        success = patientService.updatePatient(patientID, doctorID,severityLevel);
        System.out.println(success);

        JsonObjectBuilder response = Json.createObjectBuilder();
        if(success){
            response.add("result","1");
        }else {
            response.add("result","0");
        }

        PrintWriter writer = resp.getWriter();
        writer.print(response.build());
        System.out.println(response.toString());

    }
}
