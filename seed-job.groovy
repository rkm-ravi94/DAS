job('seed-job') {

scm {
   git {
      remote {
          
          url('https://github.com/alok-opstree/DAS.git')
      }
      branch('*/master')
      
  }
}
steps {
        dsl {
            external('*.groovy')
            
        }
}

steps {
shell(

'#!/bin/bash\n'  +

'mkdir -p ${JENKINS_HOME}/template/\n'  +

'mkdir -p ${JENKINS_HOME}/scripts/\n' +
'mkdir -p ${JENKINS_HOME}/properties/\n' +
'mkdir -p ${JENKINS_HOME}/inventory/\n' +
'cp *.template ${JENKINS_HOME}/template/.\n' +
'cp *.sh ${JENKINS_HOME}/scripts/.\n' +
'cp inventory.Properties  ${JENKINS_HOME}/.\n' +
'cp jenkins.properties ${JENKINS_HOME}/properties/.\n' +
'cp hosts ${JENKINS_HOME}/inventory/.\n'

)
}
}
