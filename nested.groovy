nestedView('{{LABEL}}-jobs') {
    views {
      listView('{{LABEL}}') {
           jobs {
                names('Configure-{{LABEL}}-Tomcat-Job', 'Configure-{{LABEL}}-Nginx-Job', 'Configure-{{LABEL}}-Mysql-Job', 'Configure-{{LABEL}}-Zabbix-Job')
            }
            columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
            }
        }
    }
}
