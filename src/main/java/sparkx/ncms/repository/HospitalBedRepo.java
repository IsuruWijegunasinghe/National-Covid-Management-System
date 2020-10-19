package sparkx.ncms.repository;

import sparkx.ncms.db.DBConnectionPool;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class HospitalBedRepo {
    public List<String> hospitalsWithVacantBeds()
    {
        List<String> vacantHospitals = new ArrayList<String>();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT DISTINCT hospitalID FROM hospital_bed WHERE patientID IS NULL");

            rs = stmt.executeQuery();
            while (rs.next()){
                vacantHospitals.add(rs.getString(1));
            }

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
        return vacantHospitals;
    }

    public void updateBed(String patientID, String bedID, String severityLevel) {
        /* Severity Level = -1 --> Discharging the patient */

        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("UPDATE hospital_bed SET patientID=? where bedID=?");
            if (severityLevel == "-1") {
                stmt.setObject(1,null);
                stmt.setString(2,bedID);
            }else {
                stmt.setString(1,patientID);
                stmt.setObject(2,bedID);
            }

            int changedRows = stmt.executeUpdate();
            System.out.println(changedRows == 1 ? "Successfully updated" : "Update failed");
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
