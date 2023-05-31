pipeline {
    
    agent any
    
    tools {
        maven 'maven-3.6.3'
        }
	
	stages {
	      
	  stage('Clone Default Test Repo'){
          steps {
	      sh 'git clone https://github.com/smita-upadhyay/java_palindrome.git'
	  }
	         }
           }
            
        stage('Build') {
            steps{
		    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn clean install -f pom.xml'
		    }
               }
             }
}
     
