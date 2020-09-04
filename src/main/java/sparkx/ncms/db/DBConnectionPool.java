package sparkx.ncms.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionPool {
    private static DBConnectionPool instance;
    private BasicDataSource basicDataSource;

    private DBConnectionPool()
    {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/ncms");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("Ipdw@970133534sql");
        basicDataSource.setMinIdle(2);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMaxTotal(10);
    }

    public static DBConnectionPool getInstance()
    {
        if(instance == null)
        {
            instance = new DBConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException
    {
        return this.basicDataSource.getConnection();
    }

    public void close(AutoCloseable closeable)
    {
        try
        {
            if(closeable != null)
            {
                closeable.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
