package io.unguiculus.jobdsl

import hudson.FilePath
import javaposse.jobdsl.dsl.Item
import javaposse.jobdsl.dsl.NameNotProvidedException
import javaposse.jobdsl.plugin.JenkinsJobManagement
import javaposse.jobdsl.plugin.LookupStrategy

import static java.nio.charset.StandardCharsets.UTF_8

class JenkinsFileJobManagement extends JenkinsJobManagement {

    private final File xmlDir;

    public JenkinsFileJobManagement(PrintStream outputLogger, Map<String, String> envVars, File workspace, File xmlDir) {
        super(outputLogger, envVars, null, new FilePath(workspace.getAbsoluteFile()), LookupStrategy.JENKINS_ROOT)
        this.xmlDir = xmlDir
    }

    @Override
    public boolean createOrUpdateConfig(Item dslItem, boolean ignoreExisting)
            throws NameNotProvidedException {
        super.createOrUpdateConfig(dslItem, ignoreExisting)

        String path = dslItem.getName()
        File configXml = new File(xmlDir, path + '.xml')
        configXml.getParentFile().mkdirs()

        String xml = dslItem.getXml()
        configXml.setText(dslItem.getXml(), UTF_8.name())

        true
    }
}
