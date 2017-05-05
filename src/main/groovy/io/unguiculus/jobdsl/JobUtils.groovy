package io.unguiculus.jobdsl

import javaposse.jobdsl.dsl.Job

class JobUtils {

    static void addDefaults(Job job) {
        addDescription(job)
        addLogRotator(job)
    }

    static void addDescription(Job job) {
        job.with {
            description('<em style="color: red;">GENERATED JOB - MANUAL CHANGES WILL BE OVERWRITTEN</em>')
            wrappers {
                colorizeOutput()
            }
        }
    }

    static void addLogRotator(Job job) {
        job.with {
            logRotator {
                numToKeep(30)
            }
        }
    }

    static void addScmTrigger(Job job) {
        job.with {
            triggers {
                scm('* * * * *')
            }
        }
    }

    static void addTimestampsWrapper(Job job) {
        job.with {
            wrappers {
                timestamps()
            }
        }
    }
}
