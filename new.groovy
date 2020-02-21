#!/usr/bin/env groovy

env.BRANCH_NAME = BRANCH_NAME
//env.BUILD_ENV = BUILD_ENV

def clone() {
  // git branch: 'kk', credentialsId: 'kamalkishorsingh', url: 'https://github.com/kamalkishorsingh/test-nginx.git'
  //git branch: 'master', credentialsId: 'kamalkishorsingh', url: 'https://github.com/kamalkishorsingh/test-nginx.git'
	git url: 'https://github.com/kamalkishorsingh/test-nginx.git'
  }
  
node("master") {
	stage('clone'){
		clone()
	}
}
