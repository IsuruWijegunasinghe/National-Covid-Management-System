package sparkx.ncms.repository;

import sparkx.ncms.db.DBConnectionPool;

import java.sql.*;
import java.util.UUID;

public class PatientRepo {
    public void insertQuery(String firstName, String lastName, String district, int coordinateX, int coordinateY, String gender, String contactNo, String email, int age)
    {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            UUID uuid = UUID.randomUUID();

            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("INSERT INTO patient (serialNo, firstName, lastName, district, coordinateX, coordinateY, severityLevel, gender, contactNo, email, age, admitDate, admittedBy, dischargeDate, dischargedBy) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            stmt.setString(1, uuid.toString());
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, district);
            stmt.setInt(5, coordinateX);
            stmt.setInt(6, coordinateY);
            stmt.setString(7, null);
            stmt.setString(8, gender);
            stmt.setString(9, contactNo);
            stmt.setString(10, email);
            stmt.setInt(11, age);
            stmt.setDate(12,null);
            stmt.setString(13, null);
            stmt.setDate(14,null);
            stmt.setString(15, null);

            int changedRows = stmt.executeUpdate();
            System.out.println( changedRows == 1 ? "Successfully inserted" : "Insertion failed");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
    }
}
