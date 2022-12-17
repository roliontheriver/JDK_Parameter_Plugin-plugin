#!/usr/bin/env groovy

/* `buildPlugin` step provided by: https://github.com/jenkins-infra/pipeline-library */
buildPlugin(
  // Container agents start faster and are easier to administer
  useContainerAgent: true,
  // Test Java 11 with minimum Jenkins version, Java 17 with a more recent version
  // Test Java 8 until plugin upgrades to a Jenkins version that does not support Java 8
  configurations: [
    [platform: 'windows', jdk: '17', jenkins: '2.382'],
    [platform: 'linux',   jdk: '11', jenkins: '2.375.1'],
    [platform: 'linux',   jdk: '8'],
  ]
)
