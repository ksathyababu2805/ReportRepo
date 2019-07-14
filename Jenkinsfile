pipeline {
	agent any
	stages {
		stage('Sanity Test Execution'){   
			step {  
				sh 'mvn clean test -Dcucumber.options="--tags @createOrderSanityCheck" -DEnv=qa'       
			}		
	  	}
	  	stage('Creating Report'){
	  		step {
	            sh 'mvn site'               
	  		}       
	  	}
	  	stage('Publish Report'){ 
	  		step{
	    		publishHTML (target: [
		        reportDir: 'target/site/allure-maven-plugin',
		        reportFiles: 'index.html',
		        reportName: "Sanity Tests Report"]) 
	     	}
		}
	}
}
