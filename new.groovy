#!/usr/bin/env groovy

env.BRANCH_NAME = BRANCH_NAME
//env.BUILD_ENV = BUILD_ENV

def build_artifact() {
   // git ([url: 'git@github.com:'+GIT_ORG+'/'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
    git ([url: 'https://git@github.com:'+GIT_ORG+'/'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
  }
  
