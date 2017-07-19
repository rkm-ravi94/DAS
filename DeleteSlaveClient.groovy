job('Delete-Node') {
    parameters {
        stringParam('NODE_NAME', '')
    }
  label('master')
  steps {
        shell(

        '#!/bin/bash\n'  +
		'source ${JENKINS_HOME}/properties/jenkins.properties\n' +
		'LABEL=`java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} get-node ${NODE_NAME} --username "${USERNAME}" --password "${PASSWORD}" | grep "label"  | cut -d ">" -f2 | cut -d "<" -f1`\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-node ${NODE_NAME} --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-job ${LABEL}-Setup-Nginx-Job --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-job ${LABEL}-Setup-Mysql-Job --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-job ${LABEL}-Setup-Tomcat-Job --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-job ${LABEL}-Setup-Zabbix-Job --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-job ${LABEL}-Command-Executor --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-job ${LABEL}-Mysql-Replication --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-job ${LABEL}-Tomcat-Deployment --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-view ${LABEL}-jobs --username "${USERNAME}" --password "${PASSWORD}"\n'

        )
    }
  
}
