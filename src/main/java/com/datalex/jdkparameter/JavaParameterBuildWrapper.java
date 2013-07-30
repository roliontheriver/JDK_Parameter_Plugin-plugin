package com.datalex.jdkparameter;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.JDK;
import hudson.tasks.BuildWrapper;
import hudson.tasks.BuildWrapperDescriptor;

import java.io.IOException;

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

//        JDK defaultJDK = new JDK("(Default)", null);
//            System.out.println("Current JDK here"+ build.getProject().getJDK() == null ? "(Default)" : build.getProject().getJDK().getName());
//            System.out.println("Restoring original JDK here"+ getOriginalJDK());

        return new Environment() {

            @Override
            public boolean tearDown(hudson.model.AbstractBuild build, hudson.model.BuildListener listener)
                    throws java.io.IOException, java.lang.InterruptedException {
                JDK original = null;
                for(JDK jdk : jenkins.model.Jenkins.getInstance().getJDKs()) {
                    if(jdk.getName().equalsIgnoreCase(getOriginalJDK())) {
                        original = jdk;
                    }
                }
                original = original == null? new JDK("(Default)", null) : original;
                build.getProject().setJDK(original);
                System.out.println("Current JDK after change here"+ build.getProject().getJDK() == null ? "(Default)" : build.getProject().getJDK().getName());
                return true;
            }
        };
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
