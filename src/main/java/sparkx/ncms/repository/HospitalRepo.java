package sparkx.ncms.repository;

import sparkx.ncms.dao.Hospital;
import sparkx.ncms.db.DBConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class HospitalRepo {
    public Hospital selectHospital(String hospitalID)
    {
        Hospital hospital = new Hospital();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT * FROM hospital WHERE id = ?");
            stmt.setString(1, hospitalID);

            rs = stmt.executeQuery();

            while (rs.next()){
                hospital.setHospitalID(rs.getString(1));
                hospital.setName(rs.getString(2));
                hospital.setDistrict(rs.getString(3));
                hospital.setCoordinateX(rs.getInt(4));
                hospital.setCoordinateY(rs.getInt(5));
                hospital.setBuildDate(rs.getString(6));
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
        return hospital;
    }

    public List<String> getAvailableBed(String hospitalID)
    {
        List<String> availableBeds = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT id FROM hospital_bed WHERE hospital_id = ? AND patient_id IS NULL");
            stmt.setString(1, hospitalID);

            rs = stmt.executeQuery();

            while (rs.next()){
                availableBeds.add(rs.getString(1));
                System.out.println(rs.getString(1));
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
        return availableBeds;
    }

    public Boolean addNewHospital(String hospitalID, String name, String district, int coordinateX, int coordinateY)
    {
        Boolean success = false;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("INSERT INTO hospital (id, name, district, location_x, location_y, build_date) VALUES (?,?,?,?,?,?)");

            stmt.setString(1, hospitalID);
            stmt.setString(2, name);
            stmt.setString(3, district);
            stmt.setInt(4, coordinateX);
            stmt.setInt(5, coordinateY);
            stmt.setDate(6, (Date) Calendar.getInstance().getTime());

            int changedRows = stmt.executeUpdate();
            if(changedRows == 1){
                success = true;
            }
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
        return success;
    }
}
