job('StaticGitUrl') {
parameters {
        stringParam('git_url')
        
    }

    scm {
   git {
      remote {

          url('$git_url')
      }
      branch('*/master')

  }
}
}
