job('Create-inventory-Properties-File') {
  
  parameters {
    fileParam{
      name('Inventory_File')
      description ('select file')
    }
  }

steps {
shell(

'#!/bin/bash\n'  +
'cp Inventory_File ${JENKINS_HOME}/inventory/hosts\n'
)
}
}
