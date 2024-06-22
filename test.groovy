pipeline {
    agent any

    stages {
        stage('Get Source Code') {
            steps {
                git branch: 'main', credentialsId: 'a21b8eb9-5a40-488b-b497-1685eace2871', url: 'https://github.com/Zuneirah/TutorialNinja.git'
                echo 'Hello World'
            }
        }
        
        stage('Build Code') {
            steps {
              bat script : 'mvn compile'
               
            }
        }
        
        stage('Run Test') {
            steps {
               bat script: 'mvn test -Dbrowser=localchrome'
               
            }
            
        }
        
        stage('Publish Report') {
            steps {
              publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: '', reportFiles: 'target/surefire-reports/Extent*.html', reportName: 'My Report', reportTitles: '', useWrapperFileDirectly: true])
               
            }
            
        }
    }
}
