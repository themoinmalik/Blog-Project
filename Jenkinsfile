pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/themoinmalik/Blog-Project.git'
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
