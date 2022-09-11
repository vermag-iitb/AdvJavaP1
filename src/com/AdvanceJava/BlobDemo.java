// Program to store and retrieve following objects into/from DB:
// 1. BLOB, and
// 2. CLOB

package com.AdvanceJava;
import java.sql.*;
import java.io.FileInputStream;

public class BlobDemo
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

//            Statement stmt = con.createStatement();
//            System.out.println("@sql declared => "+stmt.execute("Declare @sql nvarchar(max)"));
//            Statement pstmt00 = con.prepareStatement("Declare @sql nvarchar(max)");
//            Statement pstmt0 = con.prepareStatement("SET @sql='UPDATE [Demo2Conn].ID_1.Student\n" +
//                    "set Photo =\n" +
//                    "(\n" +
//                    "    SELECT * FROM\n" +
//                    "    OPENROWSET(?,SINGLE_BLOB)\n" +
//                    "    as?\n" +
//                    ")\n" +
//                    "where std_id=?;'");
//            System.out.println("@sql defined");
//            PreparedStatement pstmt1 = con.prepareStatement("exec(@sql)");
            PreparedStatement pstmt = con.prepareStatement("UPDATE [Demo2Conn].ID_1.Student\n" +
                    "set Photo =\n" +
                    "(\n" +
                    "    SELECT * FROM\n" +
                    "    OPENROWSET(BULK ?,SINGLE_BLOB)\n" +
                    "    as?\n" +
                    ")\n" +
                    "where std_id=?;");
//            FileInputStream fis = new FileInputStream(args[0]);
//            pstmt.setBinaryStream(1,fis,fis.available());
//            String str = args[0] + ' ' + args[1];
//            System.out.println(str);
            pstmt.setString(1,args[0]);
            pstmt.setString(2,args[1]);
            pstmt.setInt(3,Integer.parseInt(args[2]));
            pstmt.executeUpdate();
            System.out.println("One record updated with image with Way 1: without 'FileInputStream'");
            PreparedStatement pstmt2 = con.prepareStatement("select * from [Demo2Conn].ID_1.Student where std_id=?;");
            pstmt2.setInt(1, Integer.parseInt(args[4]));
            ResultSet rs = pstmt2.executeQuery();
            printResultSet(rs);
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
}

//            Note:
//            1. ? mark 1: actual image (needs FileInputStream object, not String)
//            2. ? mark 2: alias name for the image object (String)
//            3. ? mark 3: std_id (int type)
//            /Users/vermag5/Sql_projects/sample_photos/doll.jpeg
//            /var/opt/mssql/data/images/doll.jpeg
//            img2
//            102
//            peach -> img3 ->103
//            pstmt.setInt(1,args[0]);
//            pstmt.setString(2,args[1]);

// Methods in 'PreparedStatment' interface for streams:
// 1. public abstract void setBinaryStream(int,InputStream,int) throws SQLException;        -> Used to set BLOB stream, int1 is '?' number, InputStream is Byte stream, int2 is length of the stream
// 2. public abstract void setCharacterStream(int,Reader/Writer,int) throws SQLException;   -> Used to set CLOB stream, int1 is '?' no., Reader/Writer is Character stream, int2 is length of the stream

// Note:
// 1. Classes with name having Stream in it work for Byte streams
// 2. Classes with name having Reader/Writer in it work for Character streams
// 3. FileInputStream object gets type casted to InputStream (bcz FileInputStream is child class of InputStream)
// 4. FileInputStream opens a file for reading present at provided location
// 5. available() method