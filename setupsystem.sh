#!/bin/bash


function downloadjenkinsjar(){
	wget http://localhost:8080/jnlpJars/jenkins-cli.jar -O /var/lib/jenkins/
}

function downloadjavafile()
{
		wget --no-check-certificate -c --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.rpm -O /var/lib/jenkins/


}

downloadjenkinsjar
downloadjavafile
