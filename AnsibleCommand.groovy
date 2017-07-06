job('AnsibleCommand') {
        parameters {
                stringParam('command')

            }

            steps {
            shell(

            'name=$Command\n'  +
            'echo $name\n'

            )
            }
            }
