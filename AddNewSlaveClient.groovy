job('Add-Node') {
    parameters {
        stringParam('REMOTE_MACHINE_IP', '192.168.33.99')
        stringParam('LABEL', '')
    }
  
  steps {
        shell(

        '#!/bin/bash\n'  +
        'rm -rf ${WORKSPACE}/*.groovy\n' +
		'cp ${JENKINS_HOME}/scripts/DeployNginxdsl.template ${WORKSPACE}/DeployNginxdsl.groovy\n' +
		'cp ${JENKINS_HOME}/scripts/DeployTomcatdsl.template ${WORKSPACE}/DeployTomcatdsl.groovy\n' +
		'cp ${JENKINS_HOME}/scripts/DeployMysqldsl.template ${WORKSPACE}/DeployMysqldsl.groovy\n' +
		'cp ${JENKINS_HOME}/scripts/nested.template ${WORKSPACE}/nested.groovy\n' +
		'cp ${JENKINS_HOME}/scripts/Deployzabbixdsl.template ${WORKSPACE}/Deployzabbixdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployNginxdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployTomcatdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployMysqldsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" Deployzabbixdsl.groovy\n' +
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
          '/var/lib/jenkins/scripts/node.sh ${REMOTE_MACHINE_IP} ${LABEL}\n'

        )
    }
}
