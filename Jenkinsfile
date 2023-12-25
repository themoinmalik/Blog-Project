pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean'
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }


    }
}
