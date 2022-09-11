package com.AdvanceJava;
import java.sql.*;

// Code to establish connection to SQLServer Database (running in Docker container, accessed via Azure Data Studio)
// Before running code, ensure following steps:
// 1. Docker is started and SQLServer is running as container in Docker
// 2. Add Docker plugin to IntelliJ

public class DBConn
{
    public static void main(String[] args)
    {
        try
        {   String url, name, pwd;
            // Note: Steps to develop a DB application:
            // 1. Loading a jdbc driver
            // 2. Establishing a connection
            // 3. Performing the task
            // 4. Closing the connection
            System.out.println("Hello");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Note:
            // 1. forName() can return a Class class's object or can return nothing
            // 2. Driver load statement points towards SQLServerDriver class present in the driver jar file, hence it needs to be correctly pointed
            // 3. Procedure to point driver class file in IntelliJ:
            //  - Click on File menu => Project Structure => Modules => Dependencies => Click + and add the jar
            //  - Jar name: mssql-jdbc-10.2.0.jre8.jar
            System.out.println("Driver loaded");
            // Note:
            // 1. To add SSL certificate to keystore (for PKIX error), use following commands:
            //     cd "/Library/Java/JavaVirtualMachines/jdk1.8.0_212.jdk/Contents/Home/jre/lib/security"
            //     keytool -import -alias example  -file cacert_advJava.der
            // 2. Naming convention for URL:
            //     jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
            //     url = "jdbc:sqlserver://" +serverName + ":1433;DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;"
            url = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;";
            name = "sa";
            pwd = "reallyStrongPwd123";
            Connection con = DriverManager.getConnection(url,name,pwd);
            if (con != null)
                System.out.println("Connection Established. \nHashcode => "+con);
            else
                System.out.println("Connection could not establish");
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
}
//    docker exec -t -i e34cdf8a96ef /bin/bash


//%JAVA_HOME%\bin\keytool -importcert -keystore D:\bamboo-agent-home\bambooKeyStore.jks -storepass changeit -file D:\DigiCertGlobalRootCA.crt -alias "DigiCertGlobalRootCA"
