package sparkx.ncms.repository;

import sparkx.ncms.dao.Hospital;
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
    public String insertPatient(String firstName, String lastName, String district, int coordinateX, int coordinateY, String gender, String contactNo, String email, int age)
    {
        String patientID = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            UUID uuid = UUID.randomUUID();

            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("INSERT INTO patient (id, first_name, last_name, district, location_x, location_y, severity_level, gender, contact, email, age, admit_date, admitted_by, discharge_date, discharged_by) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

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
            if(changedRows == 1){
                patientID = uuid.toString();
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
        return patientID;
    }

    public Boolean updatePatient(String patientID, String doctorID, String severityLevel) {
        /* Severity Level = -1 --> Discharging the patient */

        Boolean success = false;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            if (severityLevel == "-1") {
                stmt = con.prepareStatement("UPDATE patient SET discharge_date = ?, discharged_by = ? WHERE id = ?");

                stmt.setDate(1, (Date) Calendar.getInstance().getTime());
                stmt.setString(2, doctorID);
                stmt.setString(3, patientID);
            } else {
                stmt = con.prepareStatement("UPDATE patient SET severity_level = ?, admit_date = ?, admitted_by = ? WHERE id = ?");

                stmt.setString(1, severityLevel);
                stmt.setDate(2, new Date(Calendar.getInstance().getTime().getTime()));
                stmt.setString(3, doctorID);
                stmt.setString(4, patientID);
            }

            int changedRows = stmt.executeUpdate();
            if(changedRows == 1){
                success = true;
            }
            System.out.println(changedRows == 1 ? "Successfully updated" : "Update failed");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return success;
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
            stmt = con.prepareStatement("SELECT * FROM patient WHERE id = ?");
            stmt.setString(1, patientID);

            rs = stmt.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                patientDto.setPatientID(rs.getString(1));
                System.out.println(rs.getString(1));
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

    public List<PatientCount> getPatientCount()
    {
        List<PatientCount> patientCount = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT * FROM hospital");
            rs = stmt.executeQuery();
            List<Hospital> allHospitals = new ArrayList<>();
            while (rs.next()){
                allHospitals.add(new Hospital(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5), rs.getString(6)));
            }

            for (Hospital hospital : allHospitals){
                stmt = con.prepareStatement("SELECT COUNT(patient_id) FROM hospital_bed WHERE hospital_id = ?");
                stmt.setString(1, hospital.getHospitalID());

                rs = stmt.executeQuery();
                while (rs.next()){
                    patientCount.add(new PatientCount(hospital.getHospitalID(), hospital.getDistrict(), rs.getInt(1)));
                    System.out.println(rs.getInt(1));
                }
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
        return patientCount;
    }
}
