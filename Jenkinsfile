node {
    
    stage('Prepare') {
        sh "rm -Rf *"
        def mvnHome = tool 'maven'
        env.PATH = "${mvnHome}/bin:${env.PATH}"
        registry_url = "192.168.0.152:8082"
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
        userRemoteConfigs: [[credentialsId: '44ae1c08-e857-4dc6-9ae5-5b65fc12209f', url: "http://192.168.0.150:8000/waka2020/provider.git"]]])
        pom = readMavenPom file: "pom.xml"
        img_name = "${pom.groupId}-${pom.artifactId}"
        img_tag = "${pom.version}"
        img = "${img_name}:${img_tag}"
        registry_img = "${registry_url}/${img}"
    }
    
    stage('Build Code') {
        sh "mvn clean install"
    }

    stage('Build Image And Push') {
        docker.withRegistry("http://${registry_url}",'01385c55-7871-4868-a03a-889c4dc403ec') {
        def customimage = docker.build("${img}")
        customimage.push()
        }
    }
    
    stage('Delete Image') {
        sh "docker rmi ${registry_img}"
        sh "docker rmi ${img}"
    }

}
