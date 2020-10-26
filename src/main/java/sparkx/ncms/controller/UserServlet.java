package sparkx.ncms.controller;

import sparkx.ncms.dao.User;
import sparkx.ncms.service.UserService;

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

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Util.getJsonObject(req);

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        UserService userService = new UserService();
        try{
            User user = userService.authenticateUser(username);

            JsonObjectBuilder response = Json.createObjectBuilder();
            if(password.equals(user.getPassword())){
                response.add("isAuthorized","true");
            }else {
                response.add("isAuthorized","false");
            }
            response.add("isMoH", user.getIsMoH());
            response.add("isHospital", user.getIsHospital());
            PrintWriter writer = resp.getWriter();
            writer.print(response.build());
            System.out.println(response.toString());

        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            return;
        }
    }
}
