pipeline {
    agent none
    stages {
        stage("test") {
            agent {
                label "gjy_onestack03"
            }
            steps {
                script {
                    def ret = sh(script: "kubectl get nodes|awk '{print \$2}'|grep -o ^Ready|wc -l", returnStdout: true).trim()
                    if ( ret == '2' ) {
                        input {
                            message "是否重置k8s集群？"
                            ok "Yes"
                            }
                        steps {
                            _1_dns()
                        }
                    } else {
                        println("2122")
                    }
                }

            }
        }
    }
}
