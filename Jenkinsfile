pipeline {
    agent {
        label 'java-agent'
    }
    stages {
        stage ('Checkout Code') {
            steps {
                echo 'Checking out source-code ...'
                checkout scm
            }
        }
        stage ('Build') {
            steps {
                echo 'Building ...'
                sh  'mvn clean complie'
            }
        }
        stage ('Test') {
            steps {
                sh 'mvn test'
           }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
           }
        }
        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
     post {
            success {
                echo 'Build successful!'
            }
            failure {
                echo ' Build failed!'
            }
        }
}