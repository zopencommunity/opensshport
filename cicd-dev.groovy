
node('linux')
{
  stage ('Poll') {
               // Poll from upstream:
               checkout([
                                               $class: 'GitSCM',: 'GitSCM',
                       branches: [[name: '*/...']],
                       doGenerateSubmoduleConfigurations: false,
                       extensions: [],
                       userRemoteConfigs: [[url: 'https://...']]])

                // Poll for local changes
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        userRemoteConfigs: [[url: "https://github.com/zopencommunity/opensshport.git"]]])
  }

  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/opensshport.git'), string(name: 'PORT_DESCRIPTION', value: 'OpenSSH' ), string(name: 'BUILD_LINE', value: 'DEV')]
  }
}

