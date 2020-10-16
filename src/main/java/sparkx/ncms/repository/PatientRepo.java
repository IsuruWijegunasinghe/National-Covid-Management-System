package sparkx.ncms.repository;

import sparkx.ncms.dao.Patient;
import sparkx.ncms.db.DBConnectionPool;
import sparkx.ncms.dto.PatientCount;
import sparkx.ncms.dto.PatientDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
        /* Severity Level = -1 --> Discharging the patient */

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

    public List<PatientDto> selectAllPatient()
    {
        List<PatientDto> patientList = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT * FROM patient");

            rs = stmt.executeQuery();
            while (rs.next()){
                patientList.add(new PatientDto(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
                        rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15)));

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
        return patientList;
    }

    public PatientDto selectPatient(String patientID)
    {
        PatientDto patientDto = new PatientDto();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT * FROM patient WHERE patientID = ?");
            stmt.setString(1, patientID);

            rs = stmt.executeQuery();
            while (rs.next()) {
                patientDto.setPatientID(rs.getString(1));
                patientDto.setFirstName(rs.getString(2));
                patientDto.setLastName(rs.getString(3));
                patientDto.setDistrict(rs.getString(4));
                patientDto.setCoordinateX(rs.getInt(5));
                patientDto.setCoordinateY(rs.getInt(6));
                patientDto.setSeverityLevel(rs.getString(7));
                patientDto.setGender(rs.getString(8));
                patientDto.setContactNo(rs.getString(9));
                patientDto.setEmail(rs.getString(10));
                patientDto.setAge(rs.getInt(11));
                patientDto.setAdmitDate(rs.getString(12));
                patientDto.setAdmittedBy(rs.getString(13));
                patientDto.setDischargeDate(rs.getString(14));
                patientDto.setDischargedBy(rs.getString(15));
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
        return patientDto;
    }

    public PatientCount getPatientCount()
    {
        PatientCount patientCount = new PatientCount();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT COUNT(patientID) FROM patient WHERE dischargeDate IS NULL");

            rs = stmt.executeQuery();
            patientCount.setPatientCount(rs.getInt(1));
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
        return patientCount;
    }
}
