package sparkx.ncms.repository;

import sparkx.ncms.db.DBConnectionPool;

import java.sql.*;
import java.util.Calendar;
import java.util.UUID;

public class HospitalRepo {
    public ResultSet selectHospital(String hospitalID)
    {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT * FROM hospital WHERE hospitalID = ?");
            stmt.setString(1, hospitalID);

            rs = stmt.executeQuery();

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
        return rs;
    }

    public ResultSet selectAvailableHospitals()
    {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT hospitalID FROM hospital WHERE condition "); //Need to add condition

            rs = stmt.executeQuery();

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
        return rs;
    }

    public ResultSet addNewHospital(String hospitalID, String name, String district, int coordinateX, int coordinateY)
    {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("INSERT INTO hospital (hospitalID, name, district, coordinateX, coordinateY, buildDate) VALUES (?,?,?,?,?,?)");

            stmt.setString(1, hospitalID);
            stmt.setString(2, name);
            stmt.setString(3, district);
            stmt.setInt(4, coordinateX);
            stmt.setInt(5, coordinateY);
            stmt.setDate(6, (Date) Calendar.getInstance().getTime());

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
        return rs;
    }
}
