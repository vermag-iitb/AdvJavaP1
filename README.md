# AdvJavaP1
Java DB connection scripts

General Terminology:
--------------------
1. DB - a software that stores organised collection of data in the form of tables
   - Types of DB applications -  Oracle, MySQL, Ms-SQL Server, DB2, Derby, FoxPro, Visual FoxPro, 
                           Sybase, DBase, MongoDB, Ms-Access, etc
2. Client - a software that sends request to server
3. Server - a software that receives clients request, processes it, constructs response and sends it
4. JDBC:
 - stands for Java DB Connectivity, 
 - it is a specification defining rules for DB application, 
 - helps to communicate with DB using SQL,
 - it is used by vendors to develop drivers and DB app.s
Java App <--> Driver <--> Oracle DB
5. Driver:
 - a software that connects DB application and DB
 - Types (2): ODBC (Open DB Connectivity) and JDBC (Java DB Connectivity)
 - ODBC:
   - were used before Java (by C/C++)
     - Structure of use: 
       - Non-Java apps <--> ODBC Tool <--> ODBC Driver for Oracle <--> Oracle DB
 - JDBC

JDBC types (4 - Java classes):
------------------------------
   - Type 1 (ODBC bridge driver) 
     - Architecture: Java App <--> Type 1 <--> ODBC Tool <--> ODBC Driver for Oracle <--> Oracle DB
     - Driver Class name = sun.jdbc.odbc.JdbcOdbcDriver
     - Doesn't support Jdk 1.8 and onwards
     - Developed in C language, but declared in Java
     - One driver supports all ODBC enabled DBs
     - Problems: Performance overhead, DB client software needs to be installed in local system, 
     it is platform dependent (won't work for different OS)
   - Type 2 (Partly Java Native (i.e., C based) API driver)
     - Java App <--> Type 2 driver for oracle <--> Oracle DB
     - This is a Java class which loads Java, but interacts with C libraries
     - it is unique for all DB client and server
     - faster than Type 1
     - DB needs to be installed on client
     - it is platform dependent
   - Type 3 (Net Pure Java driver or Middleware driver)
     - Java App <--> Type 3 driver (in local system) <--> Type 1/2/4 driver (in Middleware) <-->  Oracle DB
     - It is Java based and hence DB/platform independent
     - it doesn't work independently, and depends on web-based servers like Tomcat, etc...
     - Due to middleware system, DB doesn't need to be installed in local system
     - Middleware needs to be installed which is extra layer
     - Still in use (Type 1 and 2 are not in use now)
   - Type 4 (100% Pure Java driver or Thin Driver)
     - Java App <--> Type 4 <--> Oracle DB
     - It directly communicates with DB, by passing Java instructions directly to DB
     - Highest performance driver
     - Platform independent and DB is not required to be installed in local

Steps to develop a DB application:
----------------------------------
1. Loading a JDBC driver
2. Establishing a connection
3. Performing the task
4. Closing the connection

Setting up SQL-Server in MACOS:
-------------------------------
1. Install Azure Data Studio
 - https://docs.microsoft.com/en-us/sql/azure-data-studio/download-azure-data-studio?view=sql-server-ver15
2. Command to install command line SQLserver (this helps to write command in terminal in absence of GUI software):
   - npm install -g sql-cli

   If gives error then:
   - sudo npm install -g sql-cli
3. Start server using this command:
   - mssql -u sa -p reallyStrongPwd123
4. Checking sql working:
   - Select @@version
5. Install Docker (community version)
6. Start docker image: mcr.microsoft.com/mssql/server 
   - Command: sudo docker pull mcr.microsoft.com/mssql/server:2019-latest
7. Command to run the container:
   docker run -d --name sql_server_demo -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=reallyStrongPwd123' -p 1433:1433 mcr.microsoft.com/mssql/server:2019-latest
   (Check whether container started or not in Docker Dashboard (or UI), SA – System Admin)
8. Add a localhost connection:
 - Server: localhost
 - Authentication: SQL Login
 - User Name: sa (or sql_server_demo)
 - Password: reallyStrongPwd123
 - Database: master
 - Advanced Settings -> Trust server certificate: True
9. Run commands in Azure to create database, schema, tables, etc as stored in sql scripts under "AdvJavaP1/SQLFiles/SQL_Adv_Java/.."
10. Now run java scripts under src directory

