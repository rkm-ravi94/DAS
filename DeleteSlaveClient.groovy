job('Delete-Node') {
    parameters {
        stringParam('NODE_NAME', '192.168.33.19')
        stringParam('LABEL', '')
    }
  label('master')
  steps {
        shell(

        '#!/bin/bash\n'  +
		'source ${JENKINS_HOME}/properties/jenkins.properties\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-node ${NODE_NAME} --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-job Configure-${LABEL}-Nginx-Job --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-job Configure-${LABEL}-Mysql-Job --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-job Configure-${LABEL}-Tomcat-Job --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-job Configure-${LABEL}-Zabbix-Job --username "${USERNAME}" --password "${PASSWORD}"\n' +
		'java -jar ${JENKINS_CLI_JAR} -s ${JENKINS_URL} delete-view ${LABEL}-jobs --username "${USERNAME}" --password "${PASSWORD}"\n'

        )
    }
  
}
