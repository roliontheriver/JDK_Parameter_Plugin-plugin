package com.datalex.jdkparameter;

import hudson.model.AbstractBuild;
import hudson.model.JDK;
import hudson.model.ParameterValue;
import hudson.tasks.BuildWrapper;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.logging.Level;
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

    private String selectedJDK;

    @DataBoundConstructor
    public JavaParameterValue(String name, String description, String selectedJDK){
        super(name, description);
        this.selectedJDK = selectedJDK;
    }

    public String getSelectedJDK() {
        return selectedJDK;
    }

    public void setSelectedJDK(String selectedJDK) {
        this.selectedJDK = selectedJDK;
    }

    @Override
    public BuildWrapper createBuildWrapper(AbstractBuild<?,?> build) {
        JDK selected = null;
        String originalJDK = null;
        for(JDK jdk : jenkins.model.Jenkins.getInstance().getJDKs()) {
            if(jdk.getName().equalsIgnoreCase(selectedJDK))  {
                selected = jdk;
                break;
            }
        }
        if(selected==null ) {
            selected = new JDK("(Default)",null);
        }
        try {
            originalJDK = build.getProject().getJDK() == null ? "(Default)" : build.getProject().getJDK().getName();
            build.getProject().setJDK(selected);
            LOGGER.info( "[JDK Parameter]: Setting JDK to " + selected);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "[JDK Parameter]: Could not set the JDK", e);
        }
        JavaParameterBuildWrapper wrapper = new JavaParameterBuildWrapper();
        wrapper.setOriginalJDK(originalJDK);
        return wrapper;
    }

}


