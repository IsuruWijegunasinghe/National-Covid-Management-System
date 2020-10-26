package sparkx.ncms.repository;

import sparkx.ncms.dao.Hospital;
import sparkx.ncms.dao.User;
import sparkx.ncms.db.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {
    public User authenticateUser(String username)
    {
        User userObject = new User();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try
        {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT * FROM user WHERE username = ?");
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            while (rs.next()){
                userObject.setUsername(rs.getString(1));
                userObject.setPassword(rs.getString(2));
                userObject.setName(rs.getString(3));
                userObject.setIsMoH(rs.getInt(4));
                userObject.setIsHospital(rs.getInt(5));
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
        return userObject;
    }
}
