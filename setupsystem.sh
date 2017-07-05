#!/bin/bash


function downloadjenkinsjar(){
	wget http://localhost:8080/jnlpJars/jenkins-cli.jar -O /var/lib/jenkins/
}

fuction downloadjavafile()
{
	wget http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.rpm?AuthParam=1499247857_224cf15566c1a3cc98aab6f61a6bdccc
	mv jdk-8u131-linux-x64.rpm?AuthParam=1499247857_224cf15566c1a3cc98aab6f61a6bdccc /var/lib/jenkins/jdk-8u131-linux-x64.rpm

}

downloadjenkinsjar
downloadjavafile
