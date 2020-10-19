package sparkx.ncms.controller;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

@WebServlet(name = "MoHServlet")
public class MoHServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Util.getJsonObject(req);
        String hospitalId = jsonObject.getString("hospitalId");
        String name = jsonObject.getString("name");
        String district = jsonObject.getString("district");
        int coordinateX = Integer.parseInt(jsonObject.getString("coordinateX"));
        int coordinateY = Integer.parseInt(jsonObject.getString("coordinateY"));

    }
}
