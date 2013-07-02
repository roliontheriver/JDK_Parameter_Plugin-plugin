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
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: barisbatiege
 * Date: 6/28/13
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class JavaParameterBuildWrapper extends BuildWrapper {

    public List<JDK> getJDKs() {
        List<JDK> jdks = jenkins.model.Jenkins.getInstance().getJDKs();
        if(jdks==null)
            jdks = new ArrayList<JDK>();
        return jdks;
    }

    public JDK getJDK(String name) {


        if(name==null) {
            // if only one JDK is configured, "default JDK" should mean that JDK.
            List<JDK> jdks = getJDKs();
            if(jdks.size()==1)  return jdks.get(0);
            return null;
        }
        for (JDK j : getJDKs()) {
            if(j.getName().equals(name))
                return j;
        }
        return null;
    }


    @Override
    public Environment setUp(AbstractBuild build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException{
        String jdkValue = (String)build.getBuildVariables().get("jdk");

        EnvVars env = build.getEnvironment(listener);
        env.overrideAll(build.getBuildVariables());

        boolean success = true;

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
