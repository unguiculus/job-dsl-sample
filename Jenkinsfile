timestamps {
    node {
        stage('Checkout') {
            checkout scm
        }

        stage('Test') {
            sh './gradlew test'
        }

        if (BRANCH_NAME == 'master') {
            stage('Build') {
                jobDsl(additionalClasspath: 'src/main/groovy', removedJobAction: 'DELETE', removedViewAction: 'DELETE',
                        targets: 'jobs/jobs.groovy', unstableOnDeprecation: true)
            }
        }
    }
}
