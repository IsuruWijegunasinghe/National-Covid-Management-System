package sparkx.ncms.repository;

import sparkx.ncms.db.DBConnectionPool;

import java.sql.*;
import java.util.UUID;

public class PatientRepo {
    private void insertQuery()
    {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            UUID uuid = UUID.randomUUID();

            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("INSERT INTO patient (serialNo, firstName, lastName, district, coordinateX, coordinateY, severityLevel, gender, contactNo, email, age, admitDate, admittedBy, dischargeDate, dischargedBy) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, uuid.toString());
            stmt.setString(2, null);
            stmt.setString(3, null);
            stmt.setInt(4, 0);
            stmt.setInt(5, 0);
            stmt.setDate(6, new Date(new java.util.Date().getTime()));
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
