package com.AdvanceJava;
import java.sql.*;

public class stmtDemos
{
    public static void main(String[] args)
    {
        try
        {   String url, name, pwd;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver loaded");
            url = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;";
            name = "sa";
            pwd = "reallyStrongPwd123";
            Connection con = DriverManager.getConnection(url,name,pwd);
            System.out.println("Connection Established. Hashcode => "+con);
            Statement stmt = con.createStatement();
            // Note:
            // 1. Statement interface defined in java.sql package
            // 2. Abstract methods in Connection interface to create statement objects:
            // public abstract Statement createStatement() throws SQLException; (For 'Statement' object, for static SQL queries)
            // public abstract PreparedStatement prepareStatement() throws SQLException; (For 'PreparedStatement' object, for dynamic SQL queries)
            // public abstract CallableStatement prepareCall() throws SQLException; (For 'CallableStatement' object, for PL/SQL queries)


            // ============================================================================
            // ONE TIME STATEMENTS START HERE, COMMENT THEM FOR MULTIPLE RUNS (DDL AND DML)
            // ============================================================================
            System.out.println("Command executed => "+
                    stmt.execute("Create table [Demo2Conn].ID_1.Student (std_id int Primary Key, " +
                    "sname VARCHAR (255) not NULL, Maths int check(Maths >= 0 and Maths <= 100));"));
            int x = stmt.executeUpdate("insert into [Demo2Conn].ID_1.Student values(101, 'aaa', 78);");
            System.out.println(x);
            if (x != -1)
                System.out.println("One record updated");
            else
                System.out.println("Record not updated");
            stmt.executeUpdate("insert into [Demo2Conn].ID_1.Student values(102, 'bbb', 85);");
            System.out.println("One record updated");
            stmt.executeUpdate("insert into [Demo2Conn].ID_1.Student values(103, 'ccc', 96);");
            System.out.println("One record updated");
            // ===========================================================================
            // ***********************ONE TIME STATEMENTS END HERE************************
            // ===========================================================================


            // Note:
            // 1. Statement, PreparedStatement, CallableStatement => has 3 common abstract methods for DDL, DML, DQL queries
            // 2. Methods are:
            //    public abstract boolean execute(String) throws SQLExecution;
            //    public abstract int executeUpdate(String) throws SQLExecution;
            //    public abstract ResultSet executeQuery(String) throws SQLExecution;
            ResultSet rs = stmt.executeQuery("select * from [Demo2Conn].ID_1.Student;");
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
