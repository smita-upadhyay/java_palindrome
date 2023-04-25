pipeline {
    
    agent any
    
    tools {
        maven 'maven-3.6.3'
        }
	
	stages {
	      
	  stage('Clone Default Test Repo'){
          steps {
	      withCredentials([usernamePassword(credentialsId: 'GitHub-poc', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
	      sh '''
	        
	         rm -fr ut-auto-validation-tests
	         git clone https://$GIT_USERNAME:$GIT_PASSWORD@github.com/$GIT_USERNAME/ut-auto-validation-tests.git
                 cp -R ut-auto-validation-tests/src/*  src/
		 
	      ''' 
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
      
      stage('Code Quality'){
            steps{
                withSonarQubeEnv('sonar'){
                    sh 'mvn -f pom.xml sonar:sonar'
                }
            }
        }
        
       stage('Junit Test') {
            steps {
                junit keepLongStdio: true, testResults: 'target/surefire-reports/*.xml'
              
            }
        }
        
        stage('Jacoco Report') {
            steps {
                jacoco()
            }

        }
     }
      post('test result report pass') {
             always{  
		    script{
                  if (env.CHANGE_ID) {
                    def date = sh(returnStdout: true, script: "date -u").trim() 
		    def data = readFile(file: 'target/surefire-reports/com.javatechie.StringPalindrome.App_Test.txt') + " " + readFile(file: 'target/surefire-reports/com.javatechie.StringPalindrome.AppTest.txt')
		    def coverageData2 = readFile(file: 'target/surefire-reports/TEST-com.javatechie.StringPalindrome.AppTest.xml')
		     pullRequest.comment(" Build ${env.BUILD_ID}  Test Results  :  <br> <p> ${data}  <br> <p> Coverage xml Details :<br> <p> ${coverageData2}" )}
		  
		  if(env.CHANGE_ID){
		      def date = sh(returnStdout: true, script: "date -u").trim()
		      def datas = readFile(file: 'target/surefire-reports/com.javatechie.StringPalindrome.App_Test.txt') + " " + readFile(file: 'target/surefire-reports/com.javatechie.StringPalindrome.AppTest.txt')
		      def coverageData1 = readFile(file: 'target/my-reports/index.html')
		      pullRequest.comment(" Build ${env.BUILD_ID}  Test Results  :  <br> <p> ${datas}  <br> <p> Coverage html Details :<br> <p> ${coverageData1}" )	  
			  }
		  
			  
		   	  
	            
	          }
	   
             
	     }
	   }
    
	

}


