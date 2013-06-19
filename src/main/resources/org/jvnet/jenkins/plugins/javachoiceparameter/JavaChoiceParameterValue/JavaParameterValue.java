package org.jvnet.jenkins.plugins.javachoiceparameter.JavaChoiceParameterValue;

import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.ParameterValue;
import org.apache.commons.lang.StringUtils;
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

//    @Override
//    public String toString() {
//        StringBuilder s = new StringBuilder("[JDKParameterValue: ");
//        s.append(name).append("=").append(getJDK());
//        if (getJDKs != null && !getJDKs.isEmpty()) {
//            s.append(", nextNodes=").append(StringUtils.join(getJDKs, ','));
//        }
//        s.append("]");
//        return s.toString();
//    }
//
//    public List<String> getJDKs(){
//        return Collections.unmodifiableList(JDKs == null ? new ArrayList<String>() : JDKs);
//    }
//


    
//    public List<String> getJDKs(){
//        return Collections.unmodifiableList(jdks == null ? new ArrayList<String>() : jdks);
//    }

//    @Override
//    public void buildEnvVars(AbstractBuild<?, ?> build, EnvVars env){
//        String JAVA_HOME="xxxx";
//
//    }


}

