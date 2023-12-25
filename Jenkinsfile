pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'javac BlogApplication.java'
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
