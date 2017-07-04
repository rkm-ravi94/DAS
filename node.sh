#!/bin/bash

JENKINS_URL=$1
NODE_NAME=$2
NODE_SLAVE_HOME='/home/jenkins'
EXECUTORS=1
SSH_PORT=22
CRED_ID=$3
RANDOMENO=`shuf -i 1-99 -n 1`
#LABELS=slave-${RANDOMENO}
USERID=${USER}
jenkins_username=$4
jenkins_password=$5
remote_machine_username=$6
LABELS=$7

ssh -o StrictHostKeyChecking=no ${remote_machine_username}@${NODE_NAME} "mkdir /home/jenkins"
scp -o StrictHostKeyChecking=no /var/lib/jenkins/jdk-8u131-linux-x64.rpm ${remote_machine_username}@${NODE_NAME}:${NODE_SLAVE_HOME}/.
ssh -o StrictHostKeyChecking=no ${remote_machine_username}@${NODE_NAME} "sudo yum install epel-release"
ssh -o StrictHostKeyChecking=no ${remote_machine_username}@${NODE_NAME} "sudo yum install ansible"

ssh -o StrictHostKeyChecking=no ${remote_machine_username}@${NODE_NAME} "sudo yum -y install jdk-8u131-linux-x64.rpm"

cat <<EOF | java -jar ~/jenkins-cli.jar -s $1 create-node $2 --username "$4" --password "$5"
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
