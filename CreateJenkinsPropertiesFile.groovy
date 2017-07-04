job('Create-Jenkins-Properties-File') {
    parameters {
        stringParam('JENKINS_URL')
        stringParam('USERNAME')
	stringParam('PASSWORD')
	stringParam('CRED_ID')
    }
  
  steps {
        shell(

        '#!/bin/bash\n'  +
		'sed -i "s/{{JENKINS_URL}}/${JENKINS_URL}/g" /var/lib/jenkins/properties/jenkins.properties\n' +
		'sed -i "s/{{USERNAME}}/${USERNAME}/g" /var/lib/jenkins/properties/jenkins.properties\n' +
		'sed -i "s/{{PASSWORD}}/${PASSWORD}/g" /var/lib/jenkins/properties/jenkins.properties\n' +
		'sed -i "s/{{CRED_ID}}/${CRED_ID}/g" /var/lib/jenkins/properties/jenkins.properties\n'
        )
    }
  
     } 
