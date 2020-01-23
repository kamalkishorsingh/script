#!/usr/bin/env groovy
import hudson.model.*
import hudson.EnvVars
import groovy.json.JsonSlurperClassic
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import java.net.URL
import java.text.SimpleDateFormat


def setVar() {
  workSpace = pwd()
  WORKING_DIR="${workSpace}"
  def buildURL = env.BUILD_URL
}

def setSha() {
  dir(WORKING_DIR) {
    GIT_SHA = sh(returnStdout: true, script: 'git rev-parse HEAD')
  }
}

def clone() {
	stage name: 'clone'
    // github plugin, pipeline: github groovy libraries need to installed for this
    dir(WORKING_DIR) {
      checkout poll: false, scm: [$class: 'GitSCM', branches: [[name: GITHUB_PR_SOURCE_BRANCH]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'ChangelogToBranch', options: [compareRemote: "origin", compareTarget: 'master']], [$class: 'CleanBeforeCheckout']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: GIT_CREDS, url: "https://github.com/"+GIT_ORG+"/"+GIT_REPO+".git"]]]
    }
}


node("master") {
	clone()
}
