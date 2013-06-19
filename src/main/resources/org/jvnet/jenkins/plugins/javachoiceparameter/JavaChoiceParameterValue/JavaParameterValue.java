package org.jvnet.jenkins.plugins.javachoiceparameter.JavaChoiceParameterValue;

import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.ParameterValue;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.ArrayList;
import java.util.Collections;
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

    @DataBoundConstructor
    public JavaParameterValue(String name, List<String> jdks){
        super(name);
    }
    
//    public List<String> getJDKs(){
//        return Collections.unmodifiableList(JDKs == null ? new ArrayList<String>() : JDKs);
//    }

    @Override
    public void buildEnvVars(AbstractBuild<?, ?> build, EnvVars env){
        String JAVA_HOME="xxxx";

    }


}

