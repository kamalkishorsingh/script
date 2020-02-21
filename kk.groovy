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
    git ([url: 'https://github.com/kamalkishorsingh'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
  }
  wrap([$class: 'AnsiColorBuildWrapper']) {
     env.BRANCH_NAME = BRANCH_NAME
     echo '############ BUILD DONE ###############'+ '\n'
  }
}


node("master") {
  build_artifact()
}