pipeline {
    agent any
    tools {
        //maven 'maven_3_9_6'
        maven "MAVEN_H"
    }
    stages {
        stage('MavenBuild') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'Ascend_Team3_PDP', url: 'https://github.com/walmart-2023-sep-ascend/pdp_team03_backend.git']])
                // Run Maven build commands
                bat 'mvn clean install'
            }
        }
        stage('dockerBuild') {
            steps {
                script {
                    bat 'docker build -f Dockerfile -t harshinisuresh/pdpteam03backenddocker .'
                }

            }
		}
        stage('DockerPush') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'Mydockerc', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {

                    script {
                        bat 'docker login -u %DOCKER_USERNAME% -p %DOCKER_PASSWORD%'
                        bat 'docker push harshinisuresh/pdpteam03backenddocker:latest'
                }
                    }
            }
        }
    }
}
