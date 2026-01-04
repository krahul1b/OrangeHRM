pipeline {
    agent any

    parameters {
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'Browser to execute tests'
        )
        choice(
            name: 'ENVIRONMENT',
            choices: ['qa', 'uat'],
            description: 'Target environment'
        )
        string(
            name: 'TAGS',
            defaultValue: '@smoke',
            description: 'Cucumber tags to execute'
        )
    }

    environment {
        MAVEN_OPTS = "-Xmx1024m"
        GIT_REPO = "https://github.com/krahul1b/OrangeHRM.git"
    }

    stages {

        stage('Checkout Source Code') {
            steps {
                git branch: 'master',
                    url: "${GIT_REPO}"
            }
        }

        stage('Build & Execute Tests') {
            steps {
                bat """
                mvn clean test ^
                -Dbrowser=${params.BROWSER} ^
                -Denvironment=${params.ENVIRONMENT} ^
                -Dcucumber.filter.tags=${params.TAGS}
                """
            }
        }

        stage('Publish Cucumber Report') {
            steps {
                cucumber(
                    buildStatus: 'UNSTABLE',
                    reportTitle: 'OrangeHRM Test Automation Report',
                    fileIncludePattern: '**/cucumber.json'
                )
            }
        }
    }

    post {
        failure {
            echo '❌ Test execution failed. Check logs and reports.'
        }
        success {
            echo '✅ Test execution completed successfully.'
        }
    }
}