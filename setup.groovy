listView("SysTem-Job") {
    jobs {
        names('Add-Node', 'Delete-Node', 'Create-Jenkins-Properties-File')
    }
  	 columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
            }
}
