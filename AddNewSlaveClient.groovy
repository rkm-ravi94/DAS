job('Add-Node') {
    parameters {
        stringParam('REMOTE_MACHINE_IP', '192.168.33.99')
        stringParam('LABEL', '')
	fileParam{
     		 name('Inventory_File')
      		description ('select file')
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
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployNginxdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployTomcatdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" DeployMysqldsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" Deployzabbixdsl.groovy\n' +
		'sed -i "s/{{LABEL}}/${LABEL}/g" nested.groovy\n' + 
		'cp Inventory_File ${JENKINS_HOME}/inventory/hosts\n' + 
		'keyValue=`cat ${JENKINS_HOME}/inventory/hosts  | grep "\\[" | sed "s/.//;s/.$//" | paste -s -d ","`\n' +
		'echo "key=${keyValue}" > ${JENKINS_HOME}/inventory.Properties\n' +
		'scp -o StrictHostKeyChecking=no ~/.ssh/id_rsa root@${REMOTE_MACHINE_IP}:~/.ssh/id_rsa'
		
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
