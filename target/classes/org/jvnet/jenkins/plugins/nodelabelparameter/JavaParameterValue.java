package org.jvnet.jenkins.plugins.nodelabelparameter;

import hudson.EnvVars;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.JDK;
import hudson.model.ParameterValue;
import hudson.tasks.BuildWrapper;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: barisbatiege
 * Date: 6/14/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class JavaParameterValue extends ParameterValue {

    public JavaParameterValue(String name, String version){
        super(name, version);
    }

    @DataBoundConstructor
    public JavaParameterValue(String version){
        super(version);
    }

    @Override
    public void buildEnvVars(AbstractBuild<?, ?> build, EnvVars env){
        String JAVA_HOME="xxxx";

    }


}

