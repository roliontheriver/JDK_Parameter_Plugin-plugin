package com.datalex.jdkparameter;

import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.ParameterValue;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: barisbatiege
 * Date: 6/14/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class JavaParameterValue extends ParameterValue {

    private static final Logger LOGGER = Logger.getLogger(JavaParameterValue.class.getName());

    private String defaultJDK;
    private List<String> allowedJDKs;

    @DataBoundConstructor
    public JavaParameterValue(String name, String defaultJDK, List<String> allowedJDKs, String description){
        super(name, description);
        this.defaultJDK = defaultJDK;
        this.allowedJDKs = allowedJDKs;
    }

    public String getDefaultJDK() {
        return defaultJDK;
    }

    public void setDefaultJDK(String defaultJDK) {
        this.defaultJDK = defaultJDK;
    }

    public List<String> getAllowedJDKs() {
        return allowedJDKs;
    }

    public void setAllowedJDKs(List<String> allowedJDKs) {
        this.allowedJDKs = allowedJDKs;
    }

    @Override
    public void buildEnvVars(AbstractBuild<?, ?> build, EnvVars env){
        String JAVA_HOME="xxxx";

    }


}

