def bool(var) {
  var.equals("false") ? false : true
}

def buildNumber = env.BUILD_NUMBER
def buildUrl = env.BUILD_URL
def service = env.JOB_NAME
def BRANCH_NAME = env.BRANCH_NAME
def WORKSPACE = env.WORKSPACE

def git_clone() {
  stage name: 'Build', concurrency: 5
  dir('repo') {
    git ([url: 'https://github.com/kamalkishorsingh/'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
  }
}

def compile_build() {
  stage name: 'Compile'
  if(language == "java" ) {
//   println "Hello World!"
   sh label: '', script: 'echo "it is java"'
      println "language ${language}"
  }
  
  if(language == "python" ) {
//   println "Hello World!"
   sh label: '', script: 'echo "it is Python"'
   sh "rm -rf ${WORKSPACE}/${service}.tar"
   // println "${WORKSPACE}"
   sh "cd ${WORKSPACE}/repo/ ; tar -czf ../${service}.tar ."
    println "language ${language}"
  }
}
 

def deploy() {
    stage name: 'Deploy'
  if(language == "java" ) {
  println "language ${language}"
  sh "cp -r ${WORKSPACE}/repo/${service}.md /tmp/"
  sh label: '', script: 'echo "file copy Java"'
  }
  if(language == "python" ) {
  println "language ${language}"
 // sh "cp -r ${WORKSPACE}/repo/${service}.tar /tmp/"
  sh "cp -r ${WORKSPACE}/${service}.tar /tmp/"
  sh label: '', script: 'echo "file copy Python"'
  }
}

   // sh "'${gradleHome}/bin/gradle' mvn clean install"
//   else {
//   sh label: '', script: 'echo "no-language choose"'
//   }
//  }


node("master") {
  git_clone()
  compile_build()
  deploy()
}
