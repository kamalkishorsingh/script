def bool(var) {
  var.equals("false") ? false : true
}

def buildNumber = env.BUILD_NUMBER
def buildUrl = env.BUILD_URL
def service = env.JOB_NAME
def BRANCH_NAME = env.BRANCH_NAME

def build_artifact() {
  stage name: 'Build', concurrency: 5
  dir('repo') {
    git ([url: 'https://github.com/kamalkishorsingh/'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
  }
}

def deploy() {
  try {
    echo service+' deploying on' + ' ' + environment
      sh '/var/lib/jenkins/flow-groovy/deploy_beta_footer.sh ' + service + ' ' + environment + " " + env.BUILD_NUMBER + ' ' + env.BRANCH_NAME
    return "deployed"
  } catch (all) {
    return "FAILURE"
  }
}


node("master") {
  build_artifact()
}
