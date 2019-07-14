pipeline {
	agent any
	stages {
		stage('Sanity Test Execution'){   
			step {  
				withMaven(maven:'maven 3.5.0')          
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
