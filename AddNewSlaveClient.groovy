job('ADD-new-slave-dsl') {
    parameters {
        stringParam('JENKINS_URL', 'http://192.168.33.99:8080')
        stringParam('REMOTE_MACHINE_IP')
        stringParam('CRED_ID')
        stringParam('jenkins_username')
        password('jenkins_password', 'jenkins_password')
        stringParam('remote_machine_username')
        stringParam('LABEL')
    }
  
  steps {
        shell(

        '#!/bin/bash\n'  +
        'rm -rf ${WORKSPACE}/*.groovy\n' +
		'cp ${JENKINS_HOME}/scripts/DeployNginxdsl.template ${WORKSPACE}/DeployNginxdsl.groovy\n' +
		'cp ${JENKINS_HOME}/scripts/DeployTomcatdsl.template ${WORKSPACE}/DeployTomcatdsl.groovy\n' +
		'cp ${JENKINS_HOME}/scripts/DeployMysqldsl.template ${WORKSPACE}/DeployMysqldsl.groovy\n' +
		'cp ${JENKINS_HOME}/scripts/nested.template ${WORKSPACE}/nested.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployNginxdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployTomcatdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployMysqldsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" nested.groovy\n' 
        )
    }
  
  
       steps {
        dsl {
            external('*.groovy')
            
        }
    }
      
  
    steps {
        shell(

        '#!/bin/bash\n'  +
          '/var/lib/jenkins/scripts/node.sh ${JENKINS_URL} ${REMOTE_MACHINE_IP} ${CRED_ID} ${jenkins_username} ${jenkins_password} ${remote_machine_username} &{LABEL}\n'

        )
    }
}
