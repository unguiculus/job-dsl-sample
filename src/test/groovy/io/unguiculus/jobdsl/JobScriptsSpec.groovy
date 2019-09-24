package io.unguiculus.jobdsl

import groovy.util.FileNameFinder
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.JobManagement
import org.junit.ClassRule
import org.jvnet.hudson.test.JenkinsRule
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Tests that all dsl scripts in the jobs directory will compile.
 */
class JobScriptsSpec extends Specification {
    @Shared
    @ClassRule
    JenkinsRule jenkinsRule = new JenkinsRule()

    @Unroll
    void 'test script #file.name'(File file) {
        given:
        JobManagement jm = new JenkinsFileJobManagement(System.out, [:], new File('.'), new File('build', 'xml'))

        when:
        new DslScriptLoader(jm).runScript(file.text)

        then:
        noExceptionThrown()

        where:
        file << new FileNameFinder().getFileNames('jobs', '**/*.groovy').collect { new File(it) }
    }
}

