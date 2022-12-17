package com.datalex.jdkparameter;

import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.JDK;
import hudson.model.StringParameterValue;
import hudson.tasks.BuildWrapper;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: barisbatiege
 * Date: 6/14/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class JavaParameterValue extends StringParameterValue {

    private static final Logger LOGGER = Logger.getLogger(JavaParameterValue.class.getName());

    private String value;

    @DataBoundConstructor
    public JavaParameterValue(String name, String description, String value){
        super(name, description);
        this.value = value;
    }

    public String getvalue() {
        return value;
    }

    public void setvalue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(value, ((JavaParameterValue)obj).getValue());
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 37;
    }

    @Override
    public void buildEnvVars(AbstractBuild<?, ?> build, EnvVars env) {
        env.put(this.name, this.value);
        env.put(this.name.toUpperCase(Locale.ENGLISH), this.value);
    }

    @Override
    public BuildWrapper createBuildWrapper(AbstractBuild<?,?> build) {
        JDK selected = null;
        String originalJDK = null;
        boolean jdkIsAvailable = false;
        selected = new JDK(JavaParameterDefinition.DEFAULT_JDK,null);

        for(JDK jdk : jenkins.model.Jenkins.getInstance().getJDKs()) {
            if(jdk.getName().equalsIgnoreCase(value))  {
                selected = jdk;
                jdkIsAvailable = true;
                break;
            }
        }

        if (value.equals(JavaParameterDefinition.DEFAULT_JDK)){
            jdkIsAvailable = true;
        }

        try {
            originalJDK = build.getProject().getJDK() == null ? JavaParameterDefinition.DEFAULT_JDK : build.getProject().getJDK().getName();
            build.getProject().setJDK(selected);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "[JDK Parameter]: Could not set the JDK", e);
        }
        JavaParameterBuildWrapper wrapper = new JavaParameterBuildWrapper();
        wrapper.setOriginalJDK(originalJDK);
        wrapper.setJdkIsAvailable(jdkIsAvailable);
        return wrapper;
    }

}
