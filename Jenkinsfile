pipeline {
  agent any
  stages {
    stage('Test') {
      agent {
        docker {
          image 'mysql/mysql-server'
          args '-e MYSQL_ROOT_PASSWORD=1234 -d'}
        }
        steps {
          sh '''
          docker exec 'CREATE DATABASE springwater CHARACTER SET UTF8mb4 collate utf8mb4_bin -e MYSQL_ROOT_PASSWORD=1234' sh -c 'exec mysql < ./db/dump.sql
          '''
        }
        post {
          always {
            junit 'target/surefire-reports/*.xml'
          }
        }
      }
      stage('Build') {
        agent {
          docker {
            image 'maven:3-alpine'
          }
        }
        steps {
          sh '(mvn clean package)'
          stash name: "app", includes: "**"
        }
      }
      stage('QualityTest') {
        agent {
          docker {
            image 'maven:3-alpine'
          }
        }
        steps {
          unstash "app"
          sh '(mvn clean test)'
          sh '(mvn sonar:sonar -Dsonar.projectKey=bull0n_springwater -Dsonar.organization=bull0n-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=0916c4e7bc3cf93e96f97c679a7cde097309b43d)'
        }
      }
      // stage('IntegrationTest'){
      //   agent{
      //     docker{
      //       image 'lucienmoor/katalon-for-jenkins:latest'
      //       args '-p 8888:8080'
      //     }
      //   }
      //   steps {
      //     unstash "app"
      //     sh 'java -jar ./target/SpringTestDemo-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &'
      //     sh 'sleep 30'
      //     sh 'chmod +x ./runTest.sh'
      //     sh './runTest.sh'
      //
      //     cleanWs()
      //   }
      //
      // }
    }
    post {
      always {
        echo 'always clean up'
        deleteDir()
      }
    }
  }