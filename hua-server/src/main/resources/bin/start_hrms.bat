echo off
set HRMS_HOME=%cd%
set ENV=%1
IF [%1]==[] (
set ENV=prod
)
java -Dname=hrmsApp -Xms256m -Xmx512m -server -Xloggc:log/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=log -Dlogging.config=config/log4j2.xml -Dspring.config.location=config/application-prod.properties -jar lib/hrms.jar