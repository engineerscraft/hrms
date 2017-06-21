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
10. Git (https://git-scm.com/download/)
11. Java 1.8 (JAVA_HOME environment variable to be set correctly and JDK bin folder path to be added to PATH environment variable)

# Code Checkout
1. Go to a folder where you want to checkout the entire hrms folder
2. Run the command `git clone https://github.com/saptarshibasu/hrms.git`

# Code Build
1. Go inside the trunk folder and run the command `mvn clean install`. Note: For the first time, it will download a lot of stuff and hence it may take quite some time

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

# Setting Up LDAP & DB
1. Import the LDIF file huac\trunk\hua_setup\Hamdard.ldif into the LDAP server using the JXplorer 
   Note: Login details of LDAP
   * Host: localhost
   * Port: 10389
   * User DN: uid=admin,ou=system 
   * Password: secret
   * Protocol: LDAPv3
   * Level: User + Password
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
