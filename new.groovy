#!/usr/bin/env groovy

//env.BRANCH_NAME = BRANCH_NAME
//env.BUILD_ENV = BUILD_ENV

def build_artifact() {
   // git ([url: 'git@github.com:'+GIT_ORG+'/'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
    //git ([url: 'https://git@github.com:'+GIT_ORG+'/'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
  // git branch: 'kk', credentialsId: 'kamalkishorsingh', url: 'https://github.com/kamalkishorsingh/test-nginx.git'
   git branch: [[name: BRANCH_NAME]], credentialsId: 'kamalkishorsingh', url: 'https://github.com/'+GIT_ORG+'/'+GIT_REPO+''
  }
  
