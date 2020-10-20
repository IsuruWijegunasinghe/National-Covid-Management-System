package sparkx.ncms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import sparkx.ncms.dao.Hospital;
import sparkx.ncms.dao.PatientQueue;
import sparkx.ncms.dto.PatientCount;
import sparkx.ncms.service.MoHService;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "MoHServlet")
public class MoHServlet extends HttpServlet {
    private MoHService mohService = new MoHService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        switch (req.getPathInfo()){
            case "/QueuePatient":
                try {
                    List<String> queuePatients = new ArrayList<>();
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(queuePatients);
                    out.println(queuePatients);
                    Util.sendResponse(resp,jsonString);
                }catch (Exception e){
                    e.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Util.getJsonObject(req);
        String hospitalId = jsonObject.getString("hospitalId");
        String name = jsonObject.getString("name");
        String district = jsonObject.getString("district");
        int coordinateX = Integer.parseInt(jsonObject.getString("coordinateX"));
        int coordinateY = Integer.parseInt(jsonObject.getString("coordinateY"));

        Hospital hospital = new Hospital();
        hospital.setHospitalID(hospitalId);
        hospital.setName(name);
        hospital.setDistrict(district);
        hospital.setCoordinateX(coordinateX);
        hospital.setCoordinateY(coordinateY);

        try {
            mohService.addNewHospital(hospital);
            // Need to add response
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }
    }
}
