package sparkx.ncms.repository;

import sparkx.ncms.db.DBConnectionPool;

import java.sql.*;
import java.util.Calendar;
import java.util.UUID;

public class PatientRepo {
    public void insertPatient(String firstName, String lastName, String district, int coordinateX, int coordinateY, String gender, String contactNo, String email, int age)
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

    public void updatePatient(String patientID, String doctorID, String severityLevel) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            if (severityLevel == "-1") {
                stmt = con.prepareStatement("UPDATE patient SET dischargeDate = ?, dischargedBy = ? WHERE patientID = ?");

                stmt.setDate(1, (Date) Calendar.getInstance().getTime());
                stmt.setString(2, doctorID);
                stmt.setString(3, patientID);
            } else {
                stmt = con.prepareStatement("UPDATE patient SET severityLevel = ?, admitDate = ?, admittedBy = ? WHERE patientID = ?");

                stmt.setString(1, severityLevel);
                stmt.setDate(2, (Date) Calendar.getInstance().getTime());
                stmt.setString(3, doctorID);
                stmt.setString(4, patientID);
            }

            int changedRows = stmt.executeUpdate();
            System.out.println(changedRows == 1 ? "Successfully updated" : "Update failed");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
    }

    public ResultSet selectAllPatient()
    {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT * FROM patient");

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

    public ResultSet selectPatient(String patientID)
    {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT * FROM patient WHERE patientID = ?");
            stmt.setString(1, patientID);

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

    public ResultSet getPatientCount()
    {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT COUNT(patientID) FROM patient WHERE dischargeDate IS NULL");

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
}
