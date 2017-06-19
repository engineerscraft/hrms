# Software Requirements
1. MySQL
2. HeidiSQL (MySQL Client)
3. Apache DS
4. JXplorer (LDAP Client)
5. Apache Maven
6. NPM
7. Angular CLI (Refer to https://cli.angular.io for installation steps and other commands)
8. Eclipse (For Java development)
9. Visual Studio Code (For Angular development)
10. Tortoise SVN (include command line utility as well)
11. Java 1.8 (JAVA_HOME environment variable to be set correctly and JDK bin folder path to be added to PATH environment variable)

# Code Checkout
1. Create a folder in your local machine where you want to checkout the source code
2. Go inside the folder and run the command `svn co --depth empty https://github.com/saptarshibasu/huac`
3. Go inside the newly created folder huac and run the command `svn up trunk`

# Code Build
1. Go inside the trunk folder and run the command `mvn clean install`. Note: For the first time, it will download a lot of stuff and hence it may take quite some time

# Setting Up LDAP & DB
1. Import the LDIF file huac\trunk\hua_setup\Hamdard.ldif into the LDAP server using the JXplorer 
   Note: Login details of LDAP
   a. Host: localhost
   b. Port: 10389
   c. User DN: uid=admin,ou=system 
   d. Password: secret
   e. Protocol: LDAPv3
   f. Level: User + Password
2. Execute the SQL files: all-ddl.sql & all-permissions.sql at the location huac\trunk\hua_setup\ in MySQL
3. If after hua DB import-setup, not able to login with HUAPPUSER, then execute below SQL using root login in MySQL.
    `GRANT ALL PRIVILEGES ON HUA.* TO HUAAPPUSER@'localhost';`
4. Create an environment variable HRMS_ROOT. Create a folder called log in the folder pointed to by HRMS_ROOT. All logs will be generated here.

# Running the servers
1. Open the command terminal, go to the folder huac\trunk\hua-gui\src\main\gui and run the command `npm start`
2. Open another terminal, go to the folder huac\trunk\hua-server and run the command `mvn spring-boot:run`
3. Open a web browser and go to the URL http://localhost:4200 and login using the credentials TEST03/TEST03

# Generate REST API documentation
1. Go to location hua-server
2. Run the command `mvn -P doc install`
3. The document will ge generated at docs\api
4. The document will be available at http://techsnippet.online/hrms/api/

# Generate DB structure documentation
1. Go to location hua-server
2. Run the command `mvn -P doc site`
3. The document will ge generated at docs\database\schemaspy
4. The document will be available at http://techsnippet.online/hrms/database/schemaspy/
