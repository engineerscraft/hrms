export HRMS_HOME=.
ENV=$1
if [ "$ENV" = "" ]
then 
 ENV='prod';
fi
java -Xms256m -Xmx512m -server -Xloggc:log/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=log -Dlogging.config=config/log4j2.xml -Dspring.config.location=config/application-$ENV.properties -jar lib/hrms.jar