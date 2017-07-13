#!/bin/bash

cp /etc/ansible/roles/mysql-slave/tasks/main.yml  /etc/ansible/roles/mysql-slave/tasks/main.yml.bck


 #########print the value of log file ###############

var=(`grep -i -r 'mysql*' /etc/ansible/roles/output.txt | awk {'print $1'}`) && sed -i 's/master_log_file/'"${var}"'/g' /etc/ansible/roles/mysql-slave/tasks/main.yml

####################print the value of log pos########################
var=(`grep -i -r 'mysql*' /etc/ansible/roles/output.txt | awk {'print $2'}`) && sed -i 's/master_log_pos/'"${var}"'/g' /etc/ansible/roles/mysql-slave/tasks/main.yml
