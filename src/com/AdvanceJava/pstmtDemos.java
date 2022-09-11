package com.AdvanceJava;
import java.sql.*;

public class pstmtDemos
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
            PreparedStatement pstmt1 = con.prepareStatement("insert into [Demo2Conn].ID_1.Student values(?,?,?);");
            pstmt1.setInt(1,Integer.parseInt(args[0]));
            pstmt1.setString(2,args[1]);
            pstmt1.setInt(3,Integer.parseInt(args[2]));
            pstmt1.executeUpdate();
            System.out.println("One record updated");
            // Note:
            // 1. PreparedStatement interface defined in java.sql package
            // 2. Abstract methods in Connection interface to create statement objects:
            // public abstract PreparedStatement prepareStatement() throws SQLException; (For 'PreparedStatement' object, for dynamic SQL queries)


            // ============================================================================
            // ONE TIME STATEMENTS START HERE, COMMENT THEM FOR MULTIPLE RUNS (DDL AND DML)
            // ============================================================================
            // System.out.println("Command executed => "+
            //        pstmt.execute("Create table [Demo2Conn].ID_1.Student (std_id int Primary Key, " +
            //        "sname VARCHAR (255) not NULL, Maths int check(Maths >= 0 and Maths <= 100));"));
            // ===========================================================================
            // ***********************ONE TIME STATEMENTS END HERE************************
            // ===========================================================================

            PreparedStatement pstmt2 = con.prepareStatement("select * from [Demo2Conn].ID_1.Student where std_id=?;");
            pstmt2.setInt(1, Integer.parseInt(args[3]));
            ResultSet rs = pstmt2.executeQuery();
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
