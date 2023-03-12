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
   - Type 4 (100% Pure Java driver)
     - Java App <--> Type 2 <--> Oracle DB
     - 

Steps to develop a DB application:
----------------------------------
1. Loading a JDBC driver
2. Establishing a connection
3. Performing the task
4. Closing the connection

