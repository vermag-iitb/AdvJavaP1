package com.AdvanceJava;
import java.sql.*;

public class cstmtDemos
{
    public static void main(String[] args)
    {
        // Note: Passing command line arguments (CLI) in IntelliJ => Run menu -> Edit Configurations ->
        // Select the application (.java project) from right hand menu -> enter arguments as space separated string.
        // If .java application is not present then we need to run the resp. .java by rt. clicking on it (Left side pane)
         try
        {   String url, name, pwd;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver loaded");
            url = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;";
            name = "sa";
            pwd = "reallyStrongPwd123";
            Connection con = DriverManager.getConnection(url,name,pwd);
            System.out.println("Connection Established. Hashcode => "+con);
            CallableStatement cstmt = con.prepareCall("EXECUTE [Demo2Conn].dbo.InsertIntoStudent ?,?,?;");
            cstmt.setInt(1,Integer.parseInt(args[0]));
            cstmt.setString(2,args[1]);
            cstmt.setInt(3,Integer.parseInt(args[2]));
            cstmt.executeUpdate();  // execute() or executeUpdate() both works
            System.out.println("One record updated");
            // Note:  [Programmability].[Stored Procedures].
            // 1. CallableStatement interface defined in java.sql package
            // 2. Abstract methods in Connection interface to create statement objects:
            // public abstract CallableStatement prepareCall() throws SQLException; (For 'CallableStatement' object, used for PL/SQL queries)

            // ============================================================================
            // ONE TIME STATEMENTS START HERE, COMMENT THEM FOR MULTIPLE RUNS (DDL AND DML)
            // ============================================================================
            // System.out.println("Command executed => "+
            //        cstmt.execute("Create table [Demo2Conn].ID_1.Student (std_id int Primary Key, " +
            //        "sname VARCHAR (255) not NULL, Maths int check(Maths >= 0 and Maths <= 100));"));
            // ===========================================================================
            // ***********************ONE TIME STATEMENTS END HERE************************
            // ===========================================================================

            CallableStatement cstmt2 = con.prepareCall("select * from [Demo2Conn].ID_1.Student where std_id=?;");
            cstmt2.setInt(1, Integer.parseInt(args[3]));
            ResultSet rs = cstmt2.executeQuery();
            ResultSetMetaData rm = rs.getMetaData();
            int n = rm.getColumnCount();
            System.out.println("\n--------------------");
            for (int i=1; i<=n; i++)
            {
                // To print 'rs' metadata (column names)
                System.out.print(rm.getColumnName(i)+"\t");
            }
            System.out.println("\n--------------------");
            while(rs.next())
            {
                // To print 'rs' records (actual content)
                for (int i=1; i<=n; i++)
                {
                    // getString() -> gets any datatype content, but in string format
                    System.out.print(rs.getString(i)+"\t\t");
                }
                System.out.println(); // to print next record in new line
            }
            System.out.println("--------------------");
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
}