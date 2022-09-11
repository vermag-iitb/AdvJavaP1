// Program to process multiple SQL queries as a Batch (called Batch Processing)
// NOTE: Generally updating and inserting operations are not performed using Java code,
//       as SQL/PLSQL provides wide features for that purpose.

package com.AdvanceJava;
import java.sql.*;

public class stmtDemo3_batchprocessing
{
    public static void printResultSet(ResultSet rs)
    {
        try
        {
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
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            // NOTE: Methods from ResultSet interface for scrolling and updating
            // *****************************************************************
            // 1. public abstract void addBatch() throws SQLException;              -> adds query to the running batch
            // 2. public abstract int[] executeBatch() throws SQLException;         -> executes the batch to modify ResultSet and update in the DB


            // TO PRINT RESULTSET BEFORE ANY OPERATION
            // ---------------------------------------
            ResultSet rsObject = stmt.executeQuery("select * from [Demo2Conn].ID_1.Student;");
            System.out.println("Result before updating the Database");
            printResultSet(rsObject);

            // TO MAKE FOLLOWING MODIFICATIONS
            // a. Insert one record
            // b. Update one record
            // c. Delete two records
            // ----------------------------------------------
            stmt.addBatch("delete from [Demo2Conn].ID_1.Student where std_id=114");
            stmt.addBatch("delete from [Demo2Conn].ID_1.Student where std_id=109");
            stmt.addBatch("insert into [Demo2Conn].ID_1.Student values (108, 'lll', 56)");
            stmt.addBatch("update [Demo2Conn].ID_1.Student set std_id=115 where std_id=107");
            stmt.executeBatch();

            // TO PRINT RESULTSET
            // ------------------
            ResultSet rsObject2 = stmt.executeQuery("select * from [Demo2Conn].ID_1.Student;");
            System.out.println("Result after updating the Database");
            printResultSet(rsObject2);
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
}

//TODO:
// Ques1. Does Batch-Processing closes the opened ResultSet object as it gives the following error when
// I used a ResultSet object to print DB values before and after the Batch-processing:
// "com.microsoft.sqlserver.jdbc.SQLServerException: The result set is closed."