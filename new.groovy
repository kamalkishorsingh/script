#!/usr/bin/env groovy

env.BRANCH_NAME = BRANCH_NAME
//env.BUILD_ENV = BUILD_ENV

def clone() {
   // git ([url: 'git@github.com:'+GIT_ORG+'/'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
  //  git ([url: 'https://git@github.com:'+GIT_ORG+'/'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
  // git branch: 'kk', credentialsId: 'kamalkishorsingh', url: 'https://github.com/kamalkishorsingh/test-nginx.git'
  //git branch: 'master', credentialsId: 'kamalkishorsingh', url: 'https://github.com/kamalkishorsingh/test-nginx.git'
	git url: 'https://github.com/kamalkishorsingh/test-nginx.git'
  }
  
node("master") {
	stage('clone'){
		clone()
	}
}
