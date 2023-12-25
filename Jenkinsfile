pipeline {
    agent any
    tools {
    maven 'maven-4.0.0'
    }

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
