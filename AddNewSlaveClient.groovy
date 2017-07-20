job('Add-Node') {
    parameters {
        stringParam('REMOTE_MACHINE_IP', '', ' ')
        stringParam('LABEL', '' ,'Add client name')
	fileParam{
     		 name('Inventory_File')
      		description ('Inventory Host File')
    }
    }
  
  steps {
        shell(

        '#!/bin/bash\n'  +

        'rm -rf ${WORKSPACE}/*.groovy\n' +
		'cp ${JENKINS_HOME}/template/DeployNginxdsl.template ${WORKSPACE}/DeployNginxdsl.groovy\n' +
		'cp ${JENKINS_HOME}/template/DeployTomcatdsl.template ${WORKSPACE}/DeployTomcatdsl.groovy\n' +
		'cp ${JENKINS_HOME}/template/DeployMysqldsl.template ${WORKSPACE}/DeployMysqldsl.groovy\n' +
		'cp ${JENKINS_HOME}/template/nested.template ${WORKSPACE}/nested.groovy\n' +
		'cp ${JENKINS_HOME}/template/Deployzabbixdsl.template ${WORKSPACE}/Deployzabbixdsl.groovy\n' +
		'cp ${JENKINS_HOME}/template/TomcatDeployment.template ${WORKSPACE}/TomcatDeployment.groovy\n' +
		'cp ${JENKINS_HOME}/template/MysqlReplication.template ${WORKSPACE}/MysqlReplication.groovy\n' +
		'cp ${JENKINS_HOME}/template/AnsibleCommand.template ${WORKSPACE}/AnsibleCommand.groovy\n' +
		'cp ${JENKINS_HOME}/template/Deployzabbixagentdsl.template ${WORKSPACE}/Deployzabbixagentdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployNginxdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployTomcatdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployMysqldsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" Deployzabbixdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" nested.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" TomcatDeployment.groovy\n' + 
		'sed -i "s/{{LABEL}}/${LABEL}/g" Deployzabbixagentdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" MysqlReplication.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" AnsibleCommand.groovy\n' +		
		'cp Inventory_File ${JENKINS_HOME}/inventory/hosts\n' + 
		'keyValue=`cat ${JENKINS_HOME}/inventory/hosts  | grep "\\[" | sed "s/.//;s/.$//"| cut -d "]" -f1 | paste -s -d ","`\n' +
		'echo "key=${keyValue}" > ${JENKINS_HOME}/inventory.Properties' 
		
		
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
