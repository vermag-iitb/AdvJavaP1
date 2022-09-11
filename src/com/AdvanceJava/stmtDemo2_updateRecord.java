// Program to
// 1. update already present records, and
// 2. insert new ones
// 3. delete any record
// NOTE: Generally updating and inserting operations are not performed using Java code,
//       as SQL/PLSQL provides wide features for that purpose.

package com.AdvanceJava;
import java.sql.*;

public class stmtDemo2_updateRecord
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
            // NOTE: ResultSet Enhancement
            // ***************************
            // 1. ResultSet types (3):
            //  - public static final int TYPE_FORWARD_ONLY; // Default, only forward direction scroll allowed
            //  - public static final int TYPE_SCROLL_INSENSITIVE; // Scroll both directions allowed, but doesn't update DDBMS
            //  - public static final int TYPE_SCROLL_SENSITIVE; // Scroll both directions allowed, also update DDBMS instantly
            // 2. ResultSet Concurrency types (2):
            //  - public static final int CONCUR_READ_ONLY; // Doesn't allow updating records in DB
            //  - public static final int CONCUR_UPDATABLE; // Allow updating records in DB
            // NOTE: Methods from Connection interface
            // ***************************************
            // 1. public abstract Statement createStatement(int, int) throw SQLException;
            // 2. public abstract PreparedStatement prepareStatement(String, int, int) throw SQLException;
            // 3. public abstract CallableStatement prepareCall(String, int, int) throw SQLException;
            // int_1 -> ResultSet type
            // int_2 -> Concurrency type
            // NOTE: Methods from ResultSet interface for scrolling and updating
            // *****************************************************************
            // 1. public abstract boolean next() throws SQLException;           -> to shift to next
            // 2. public abstract boolean previous() throws SQLException;       -> to shift to previous
            // 3. public abstract boolean isBeforeFirst() throws SQLException;  -> to check position, i.e., row number = 0
            // 4. public abstract boolean isAfterLast() throws SQLException;    -> to check position
            // 5. public abstract boolean isFirst() throws SQLException;        -> to check position, i.e., row number = 1
            // 6. public abstract boolean isLast() throws SQLException;         -> to check position
            // 7. public abstract void beforeFirst() throws SQLException;       -> to shift to this position
            // 8. public abstract void afterLast() throws SQLException;         -> to shift to this position
            // 9. public abstract boolean first() throws SQLException;          -> to shift to this position
            // 10. public abstract boolean last() throws SQLException;          -> to shift to this position
            // 11. public abstract int getrow() throws SQLException;            -> to return the current row number
            // 12. public abstract boolean absolute(int) throws SQLException;   -> to shift to 'int(+/-)' location from before first/after last
            // 13. public abstract boolean relative(int) throws SQLException;   -> to shift to 'int(+/-)' location from current position
            // 14. updateInt(int, int)          -> to update the column of current row of ResultSet object, where int1 => column number (1 onwards), int2 => value
            // 15. updateString(int, String)    -> to update the column of current row of ResultSet object, where int1 => column number (1 onwards), String2 => value of String
            // 16. updateRow()                  -> to update the updated values of record into Database (mandatory after update only, not delete or insert)
            // 17. insertRow()                  -> to insert new record (not to use updateRow() here)
            // 18. moveToInsertRow()            -> to make space for new row after the current position
            // 19. deleteRow()                  -> to delete the row at current position

            // TO PRINT RESULTSET BEFORE ANY OPERATION
            // ---------------------------------------
            ResultSet rsObject = stmt.executeQuery("select std_id,sname,Maths from [Demo2Conn].ID_1.Student;");
            System.out.println("Result before updating the Database");
            printResultSet(rsObject);

            // TO UPDATE 1st RECORD'S STD_ID AND UPDATE IN DB
            // ----------------------------------------------
            rsObject.absolute(1);
            rsObject.updateInt(1,105);
            rsObject.updateRow();

            // TO PRINT RESULTSET
            // ------------------
            System.out.println("Result after updating the Database");
            printResultSet(rsObject);

            // TO INSERT RECORD IN RESULTSET AND UPDATE IN DB
            // ----------------------------------------------
            rsObject.absolute(2);
            rsObject.moveToInsertRow();
            rsObject.updateInt(1,100);
            rsObject.updateString(2, "lll");
            rsObject.updateInt(3, 86);
            rsObject.insertRow();

            // TO PRINT RESULTSET
            // ------------------
            System.out.println("Result after inserting row in the Database");
            printResultSet(rsObject);

            // TO DELETE RECORD FROM RESULTSET AND UPDATE IN DB
            // ------------------------------------------------
            rsObject.absolute(1);
            rsObject.deleteRow();

            // TO PRINT RESULTSET
            // ------------------
            System.out.println("Result after inserting row in the Database");
            printResultSet(rsObject);
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
}

//TODO:
// Ques1: After updating the ResultSet object using updateInt() and updateRow(), the change appears after refreshing the connection. Why???
// Ques2: Why DB sends the ResultSet in sorted manner or is it that DB stores data in ascending manner always?