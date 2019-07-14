pipeline {

agent any

stages {

	stage('Sanity Test Execution'){                 
		withMaven (maven:'maven 3.5.0') {
        	sh 'mvn clean test -Dcucumber.options="--tags @createOrderSanityCheck" -DEnv=qa'
         } 
  	}
  	stage('Creating Report'){                 
		withMaven (maven:'maven 3.5.0') {
        	sh 'mvn site'
         } 
  	}
  	stage('Publish Report'){                 
		publishHTML (target: [
        reportDir: 'target/site/allure-maven-plugin',
        reportFiles: 'index.html',
        reportName: "Sanity Tests Report"]) 
  	}  
}
}
