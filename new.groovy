#!/usr/bin/env groovy

env.BRANCH_NAME = BRANCH_NAME
//env.BUILD_ENV = BUILD_ENV

def build_artifact() {
    git ([url: 'git@github.com:elarahq/'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
  }
  
