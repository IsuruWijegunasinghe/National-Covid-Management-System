package sparkx.ncms.controller;

import com.google.gson.JsonObject;
import sparkx.ncms.dao.Patient;
import sparkx.ncms.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PatientServlet")
public class PatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String district = req.getParameter("district");
        int coordinateX = Integer.parseInt(req.getParameter("coordinateX"));
        int coordinateY = Integer.parseInt(req.getParameter("coordinateY"));
        String gender = req.getParameter("gender");
        String contactNo = req.getParameter("contactNo");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));

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

    }

    private void sendResponse(String data, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("Response", data);
        writer.print(json.toString());
        writer.flush();
    }
}
