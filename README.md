# SWEN-440: Online Ordering System
Fall 2018

### Overview:
The system consists of three main components:  a command line order 
entry module, a controller module and a database. 
 
 - The order entry module handles all communication with the user.
 - The controller module handles all communication with the database.  
 - The database stores all product and transaction data.
   
## Development
The **Online Ordering System** was developed using the Java language. The
developers used the IntelliJ IDEA IDE, and DB Browser for SQLite for their principle
development tools.

The product.db file changes are not tracked in git because of:  
```git update-index --skip-worktree <file> ```  
Use this command to track again  
```git update-index --no-skip-worktree <file>```

### Dependencies
 - Java 8: Refactoring work done over the life of the ordering system has
 resulted in dependencies on newer versions of Java.
 - JDBC: Handles communication to SQLite database.
 - JUnit Library: Handles controller unit tests.

### Building
The development team choose Maven as their build tool.  Maven 3.1.0 and 3.3.9
have both been successfully used.  

With Maven installed, running **mvn clean install** from the root install directory should 
successfully build the program.  It is expected that every build will also execute the test
lifecycle ensuring that unit tests all still pass.

###### Build Instructions
1. Open the project in IntelliJ
2. Click “File”, then “Save All”
3. Run class with main method
4. Click “File”, then “Project Structure”
5. Select Tab "Artifacts"
6. Click plus button near top of window.
7. Select JAR from Add drop down menu
8. Select "From modules with dependencies"
9. Choose the main class:
    1. If you want the client’s ordering interface Select “MenuMain”
    2. If you want the admin audit interface Select “AuditMain”
10. The radio button should be selecting "extract to the target JAR" 
11. Click OK
12. Depending on your main class:
    1. If you chose “MenuMain” change Name to “orderSysClient”
    2. If you chose “AuditMain” change Name to “orderSysAdmin”
13. Change the output directory to the project root folder ex: “...\SWEN-440-Ordering-System\”
14. Press apply and OK
15. In the top menu click “Build”
16. Select the option “Build Artifacts”

### Testing
JUnit tests were made for public methods in the Controller class.
These are a basic coverage of the system's functionality.

## Running the Online Ordering System
1. To run the client’s ordering system click “run_client.bat”
2. To run the admin’s audit system click “run_admin.bat”

###### Test Users
```
Client:
 - Email: client@oos.com
 - Password: 123
 
Admin
 - Email: admin@oos.com
 - Password: 123```