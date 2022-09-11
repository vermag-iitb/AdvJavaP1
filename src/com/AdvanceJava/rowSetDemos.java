package com.AdvanceJava;

import com.sun.rowset.JdbcRowSetImpl;
import javax.sql.rowset.JdbcRowSet;
import java.sql.*;

// Note:
// 1. ROWSET types: JdbcRowSet, CachedRowSet, WebRowSet, FilteredRowSet, JoinRowSet
// (all are sub-interfaces to ResultSet Interface)
// 2. ROWSET properties: Java bean, disconnected (except JdbcRowSet), Serializable
// 3. Java bean properties: class is public, all instance variables are private (accessed
// using setters and getters), implements java.io.Serializable, only default constructor,
// defined inside a package
// 4. Serializable object: it means converting object to bits/bytes, hence being able to
// read/write to file and network, because streams need this format to work
// 5. Every database vendor has their own rowset classes implementing above rowset interfaces.
// 6. Oracle DB has: OracleJDBCRowset, OracleCachedRowset, OracleWebRowset, OracleFilteredRowset, OracleJoinRowset
// 7. SQLServer DB has: JDBCRowsetImpl, CachedRowsetImpl, WebRowsetImpl, FilteredRowsetImpl, JoinRowsetImpl

public class rowSetDemos
{
    public static void main(String[] args)
    {
        try
        {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            System.out.println("Driver loaded");
            String url, name, pwd;
            url = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;";
            name = "sa";
            pwd = "reallyStrongPwd123";
            JdbcRowSet jrs = new JdbcRowSetImpl();
            jrs.setUrl(url);
            jrs.setUsername(name);
            jrs.setPassword(pwd);
//            System.out.println(((Object) jrs).getClass().getName());
            jrs.setCommand("select * from [Demo2Conn].ID_1.Student");
            jrs.execute();
            ResultSetMetaData rm = jrs.getMetaData();
            int n = rm.getColumnCount();
            System.out.println("\n--------------------");
            for (int i=1; i<=n; i++)
            {
                // To print 'rs' metadata (column names)
                System.out.print(rm.getColumnName(i)+"\t");
            }
            System.out.println("\n--------------------");
            while(jrs.next())
            {
                // To print 'rs' records (actual content)
                for (int i=1; i<=n; i++)
                {
                    // getString() -> gets any datatype content, but in string format
                    System.out.print(jrs.getString(i)+"\t\t");
                }
                System.out.println(); // to print next record in new line
            }
            System.out.println("--------------------");

//
//            Connection con = DriverManager.getConnection(url,name,pwd);
//            System.out.println("Connection Established. Hashcode => "+con);
//            Statement stmt = con.createStatement();
        }
        catch(Exception e)
        {
//            System.err.println(e);  or
            e.printStackTrace();
        }
    }
}
