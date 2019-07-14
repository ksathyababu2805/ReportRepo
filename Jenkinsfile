pipeline {
	agent any
	stages {
		stage('Sanity Test Execution'){   
			steps {  
				sh 'mvn clean test -Dcucumber.options="--tags @createOrderSanityCheck" -DEnv=qa'       
			}		
	  	}
	  	stage('Creating Report'){
	  		steps {
	            sh 'mvn site'               
	  		}       
	  	}
	  	stage('Publish Report'){ 
	  		steps {
	    		publishHTML (target: [
		        reportDir: 'target/site/allure-maven-plugin',
		        reportFiles: 'index.html',
		        reportName: "Sanity Tests Report"]) 
	     	}
		}
	}
}
