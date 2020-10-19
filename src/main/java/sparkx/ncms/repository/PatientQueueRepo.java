package sparkx.ncms.repository;

import sparkx.ncms.db.DBConnectionPool;
import sparkx.ncms.dto.PatientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientQueueRepo {
    public void addToQueue(String patientID)
    {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("INSERT INTO patient_queue(patientID) VALUES (?)");
            stmt.setString(1,patientID);

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

    public List<String> getQueuePatients()
    {
        List<String> queuePatients = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT patientID FROM patient_queue");

            rs = stmt.executeQuery();
            while (rs.next()){
                while (rs.next()){
                    queuePatients.add(rs.getString(1));
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
        return queuePatients;
    }
}
