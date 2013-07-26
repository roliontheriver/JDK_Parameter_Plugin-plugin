package com.datalex.jdkparameter;

import hudson.EnvVars;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.JDK;
import hudson.tasks.BuildWrapper;
import hudson.tasks.BuildWrapperDescriptor;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: barisbatiege
 * Date: 6/28/13
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class JavaParameterBuildWrapper extends BuildWrapper {

    private String originalJDK;

    public String getOriginalJDK() {
        return originalJDK;
    }

    public void setOriginalJDK(String originalJDK) {
        this.originalJDK = originalJDK;
    }

    @Override
    public Environment setUp(AbstractBuild build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException, IOException {
        JDK defaultJDK = new JDK("(Default)", null);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! current JDK here"+ build.getProject().getJDK() == null ? "(Default)" : build.getProject().getJDK().getName());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! restoring original JDK here"+ getOriginalJDK());
        List<JDK> jdks = jenkins.model.Jenkins.getInstance().getJDKs();
        jdks.add(defaultJDK);
        JDK original = null;
        for(JDK jdk : jdks) {
            if(jdk.getName().equalsIgnoreCase(getOriginalJDK())) {
                original = jdk;
            }
        }
        build.getProject().setJDK(original);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! current JDK after change here"+ build.getProject().getJDK() == null ? "(Default)" : build.getProject().getJDK().getName());

        EnvVars env = build.getEnvironment(listener);
        env.overrideAll(build.getBuildVariables());

        return null;
    }

        @Extension
    public static class DescriptorImpl extends BuildWrapperDescriptor {

        @Override
        public String getDisplayName() {
            return "Select JDK to be used";
        }

        @Override
        public boolean isApplicable(AbstractProject<?, ?> item) {
            return true;
        }

    }
}
