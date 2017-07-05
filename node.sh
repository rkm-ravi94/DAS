#!/bin/bash

source /var/lib/jenkins/properties/jenkins.properties

JENKINS_URL=${JENKINS_URL}
NODE_NAME=$1
NODE_SLAVE_HOME='/home/jenkins'
EXECUTORS=1
SSH_PORT=22
CRED_ID=${CRED_ID}
USERID=${USER}
jenkins_username=${USERNAME}
jenkins_password=${PASSWORD}
remote_machine_username=root
LABELS=$2

ssh -o StrictHostKeyChecking=no  ${remote_machine_username}@${NODE_NAME} "mkdir -p ${NODE_SLAVE_HOME}"
echo "Copy java package"
scp -o StrictHostKeyChecking=no  /var/lib/jenkins/jdk-8u131-linux-x64.rpm ${remote_machine_username}@${NODE_NAME}:${NODE_SLAVE_HOME}/.
ssh -o StrictHostKeyChecking=no  ${remote_machine_username}@${NODE_NAME} "yum -y install epel-release"
echo "Install ansible package"
ssh -o StrictHostKeyChecking=no  ${remote_machine_username}@${NODE_NAME} "yum -y install ansible"
echo "Install git package"
ssh -o StrictHostKeyChecking=no  ${remote_machine_username}@${NODE_NAME} "yum -y install git"
scp -o StrictHostKeyChecking=no  /var/lib/jenkins/inventory/hosts ${remote_machine_username}@${NODE_NAME}:/etc/ansible/hosts
ssh -o StrictHostKeyChecking=no  ${remote_machine_username}@${NODE_NAME} "yum -y install ${NODE_SLAVE_HOME}/jdk-8u131-linux-x64.rpm"

echo "Crateing slave machine"

cat <<EOF | java -jar ~/jenkins-cli.jar -s ${JENKINS_URL} create-node ${NODE_NAME} --username "${jenkins_username}" --password "${jenkins_password}"
<slave>
  <name>${NODE_NAME}</name>
  <description></description>
  <remoteFS>${NODE_SLAVE_HOME}</remoteFS>
  <numExecutors>${EXECUTORS}</numExecutors>
  <mode>NORMAL</mode>
  <retentionStrategy class="hudson.slaves.RetentionStrategy$Always"/>
  <launcher class="hudson.plugins.sshslaves.SSHLauncher" plugin="ssh-slaves@1.5">
    <host>${NODE_NAME}</host>
    <port>${SSH_PORT}</port>
    <credentialsId>${CRED_ID}</credentialsId>
  </launcher>
  <label>${LABELS}</label>
  <nodeProperties/>
  <userId>${USERID}</userId>
</slave>
EOF
