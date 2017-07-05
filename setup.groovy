listView("SysTem-Job") {
    jobs {
        name('Add-Node','Delete-Node','Create-Jenkins-Properties-File')
    }
  	 columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
            }
}
