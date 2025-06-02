pipeline {
    agent { label 'java-agent' } // Agent có Docker

    environment {
        IMAGE_NAME = 'spring_t2208e:latest'
        REGISTRY_URL = 'http://localhost:8001'
        DOCKER_CREDENTIALS_ID = 'habour-credential' // ID credentials trong Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Login to Harbor') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh 'echo $PASSWORD | docker login $REGISTRY_URL -u $USERNAME --password-stdin'
                }
            }
        }

        stage('Push Image') {
            steps {
                sh '''
                docker tag $IMAGE_NAME $REGISTRY_URL/project/$IMAGE_NAME
                docker push $REGISTRY_URL/project/$IMAGE_NAME
                '''
            }
        }
    }
}
