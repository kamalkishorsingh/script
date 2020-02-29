def bool(var) {
  var.equals("false") ? false : true
}

def buildNumber = env.BUILD_NUMBER
def buildUrl = env.BUILD_URL
def service = env.JOB_NAME
def BRANCH_NAME = env.BRANCH_NAME
def WORKSPACE = env.WORKSPACE

def git_clone() {
  stage name: 'clone', concurrency: 5
  dir('repo') {
    git ([url: 'https://github.com/kamalkishorsingh/'+GIT_REPO+'.git', branch: BRANCH_NAME, changelog: true, poll: true])
  }
}

def compile() {
  stage name: 'compile_build'
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
  
if(language == "php" ) {
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
  sh "rm -rf ${WORKSPACE}/${service}.tar"
  sh label: '', script: 'echo "file copy Python"'
  sh "rm -rf /tmp/${service}.tar"
  }
   if(language == "php" ) {
  println "language ${language}"
 // sh "cp -r ${WORKSPACE}/repo/${service}.tar /tmp/"
  sh "cp -r ${WORKSPACE}/${service}.tar /tmp/"
  sh "rm -rf ${WORKSPACE}/${service}.tar"
  sh label: '', script: 'echo "file copy PHP"'
  sh "rm -rf /tmp/${service}.tar"
  }
}

def estatus(){
	      stage name: 'Comfirmation'
              script {
              mail (to: 'kamal271992@gmail.com',
                subject: "Job '${env.JOB_NAME}' (${env.BUILD_NUMBER}) is waiting for input",
                body: "Please go to ${env.BUILD_URL}. This Approval for Deployment");
              env.RequestedAction = input message: 'Do you want to confirm deployment??', ok: 'Proceed for Deployment',
             // parameters: [choice(choices: "Approve\nReject", description: 'You want to Approve/Reject validation of Deployment.', name: 'Requested_Action')]
              parameters: [choice(choices: "Approve", description: 'You want to Approve/Reject validation of Deployment.', name: 'Requested_Action')]
	    //  }
	     if ( env.RequestedAction == "Approve" ){
             print "Deployment in-Progress"
                   deploy()
                   print "Deployment in-progress"
              }
		      else{
            stage("Aborted"){
              print "Deployment not progress"
	      echo "Aborted by: [${user}]"
            }
         }
     }
 }

node("master") {
  git_clone()
  compile()
  timeout(time: 300, unit: 'SECONDS') {
       estatus()
   }
}
