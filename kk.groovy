def bool(var) {
  var.equals("false") ? false : true
}

def buildNumber = env.BUILD_NUMBER
def buildUrl = env.BUILD_URL
def service = env.JOB_NAME
def BRANCH_NAME = env.BRANCH_NAME
def WORKSPACE = env.WORKSPACE

def build_artifact() {
  stage name: 'Build', concurrency: 5
  dir('repo') {
    git ([url: 'https://github.com/kamalkishorsingh/'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
  }
}

def compile() {
  stage name: 'Compile'
  if(language == "java" ) {
//   println "Hello World!"
   sh label: '', script: 'echo "it's java"'
    sh "language ${language}"
  }
  
  if(language == "python" ) {
//   println "Hello World!"
   sh label: '', script: 'echo "it's Python"'
    sh "language ${language}"
  }
  
   // sh "'${gradleHome}/bin/gradle' mvn clean install"
   else {
   sh label: '', script: 'echo "no-language choose"'
   }
  }


node("master") {
  build_artifact()
  compile()
}
