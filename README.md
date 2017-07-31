# Software Requirements
1. PostgreSQL (9.6.3) - https://www.enterprisedb.com/postgresql-963-binaries-win64?ls=Crossover&type=Crossover
2. DBeaver - http://dbeaver.jkiss.org/)
3. Apache DS
4. JXplorer (LDAP Client)
5. Apache Maven
6. NPM
7. Angular CLI (Refer to https://cli.angular.io for installation steps and other commands) - Install the version 1.0.0 only using the command `npm install -g @angular/cli@1.0.0`
8. Eclipse (For Java development)
9. Visual Studio Code (For Angular development)
10. Git (https://git-scm.com/download/)
11. Java 1.8 (JAVA_HOME environment variable to be set correctly and JDK bin folder path to be added to PATH environment variable)

# Code Checkout
1. Go to a folder where you want to checkout the entire hrms folder
2. Run the command `https://github.com/engineerscraft/hrms.git`

# Database Setup
1. Add the bin/ location of the extracted PostgreSQL folder in the environment PATH variable
2. Open a command prompt and run the following command to initialize a new database: `initdb.exe <filesystem location of the database files>`
3. Start the database by running the command: `pg_ctl -D <location given in step 2> -l <log file name along with path> start`
4. At any point of time, the database can be stopped by using the command: `pg_ctl -D <location given in step 2> stop`
5. Create a new database with the command: `createdb hrmsdb`
6. login to the database with the command: `psql -d hrmsdb`. 
7. Run the following set of SQL to setup the schema and user:
```
CREATE SCHEMA HRMS;
CREATE USER "hrmsapp" WITH CREATEROLE PASSWORD 'hrmsapp';
CREATE USER "sysadm" WITH CREATEROLE SUPERUSER PASSWORD 'sysadm';
```
8. Use your favourite client to execute the scripts: hua_setup/all_ddl.sql, hua_setup/all_baseline.sql and hua_setup/all_test_data.sql

# Environment Setup
1. Create an environment variable HRMS_HOME and point a directory location where you want to store the log.
2. Create a folder called log under the location.

# Code Build
1. Go inside the `hrms` folder and run the command `mvn clean install`. Note: For the first time, it will download a lot of stuff and hence it may take quite some time

# Code Commit
1. After making the changes, go to the root hrms folder
2. Run the command `git add *` to add any new files you might have created
3. Run the command `git commit -m <your comment withing double quotes>` to commit the code
4. Run the command `git push` to push the committed code to the git repository
5. Run the command `git status` to check the status of the files
6. Run the command `git branch` to list all the branches in the git repository. The branch you are currently on will be marked with "*"
7. Run the command `git checkout -b <branch name>` to create a new branch
8. Run the command `git push origin <branch name>` to push the new branch
9. If you are on the master branch and you want to move to the dev branch, run the command `git checkout dev`. Double check by running the command `git branch`
10. Run the command `git pull origin <branch name>` to pull from a particular branch
11. Before the first phase goes live we will push our code directly to the "master" branch and hence the above commands will be sufficient for you to get started
12. After go-live of 1st phase we will keep the production code in the "master" branch and create a "developer" branch to do the development for future phases. At any point of time "master" branch will have the production code
13. We will create one new branch for every release. So even after multiple releases we will have all the codes of all individual releases
14. For any production hot-fixes, we will create a separate hot-fix branch, fix the bug, test it and merge it wil the master branch and the appropriate release branch

# Git Best Practices
1. Make a point to commit all your changes before you plan to pull.
2. Commit often, and regularly do a pull / push! This will save us from complicated merge operations. 
3. And like any version control system, please write clear commit messages. 
4. There is a tool called 'gitk' (should be available by default), that presents a nice view of the changes done by various commits. 

# Setting Up LDAP
1. Import the LDIF file hrms\hua_setup\hrms.ldif into the LDAP server using the JXplorer 
   Note: Login details of LDAP
   * Host: localhost
   * Port: 10389
   * User DN: uid=admin,ou=system 
   * Password: secret
   * Protocol: LDAPv3
   * Level: User + Password
2. Users TEST/TEST, HUB1/HUB1, HUB2/HUB2, HUB10/HUB10, HUB42/HUB42 will be created. Here TEST is an ADMIN user and not an employee. HUB1 and HUB42 are SUPERVISOR, HUB10 is an HR, HUB2 is a normal EMPLOYEE.
   
# Running the Servers
1. Open the command terminal, go to the folder hrms\hua-gui\src\main\gui and run the command `npm start`
2. Open another terminal, go to the folder hrms\hua-server and run the command `mvn spring-boot:run`
3. Open a web browser and go to the URL http://localhost:4200 and login using the credentials TEST/TEST

# Generate REST API Documentation
1. Go to location hua-server
2. Run the command `mvn -P doc install`
3. The document will ge generated at docs\api
4. The document will be available at https://engineerscraft.github.io/hrms/api/

# Generate DB Structure Documentation
1. Go to location hua-server
2. Run the command `mvn -P doc site`
3. The document will ge generated at docs\database\schemaspy
4. The document will be available at https://engineerscraft.github.io/hrms/database/schemaspy/

# Running Integration Tests for APIs
1. Run the command `mvn -P test integration-test`

# Running with SSL (HTTPS with self-signed certificate)
1. Run the command `java -jar -Dspring.profiles.active=prod hrms.jar`
2. Open https://localhost:8080 in Chrome and accept the security exception to continue (since self-signed certificate authenticity could not be verified).
3. To use the self-signed certificate with other applications, such as, Postman, import the certificate (from hua-server/src/main/resources folder) as Trusted Root in OS Credential Store.

# Running the server using script: start_hrms.bat
1. Build the distribution packge using: 'mvn clean install'
2. Inside hrms\hua-server\target directory hrms-distribution.zip and hrms-distribution.tar will be created.
3. Ship hrms-distribution.zip to desired location.
4. Extract hrms-distribution.zip which will give you directory structure as tabulated below:

 	hrms --> config --> Application-prod.properties
 
 	hrms --> config --> keystore.p12
 
 	hrms --> config --> log4j2.xml		
 
 	hrms --> lib --> hrms.jar		
 
 	hrms --> log  
 
 	hrms --> start_hrms.bat
 
 	hrms --> start_hrms.sh  
   
5. Execute start_hrms.bat to start the application by passing argument (e.g prod). If no argument is passed, the default will be taken as prod.
   './start_hrms.bat prod'
