package com.datalex.jdkparameter;

import hudson.Extension;
import hudson.model.*;
import hudson.tools.JDKInstaller;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: barisbatiege
 * Date: 6/14/13
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class JavaParameterDefinition extends ParameterDefinition {

    public static final String VERSION = "version";
    public final String defaultJDK;
    public final List<String> allowedJDKs;



    @DataBoundConstructor
    public JavaParameterDefinition(String name,String description ,
                                   String defaultJDK, List<String>allowedJDKs ){
        super(name, description);
        this.defaultJDK = defaultJDK;
        this.allowedJDKs = allowedJDKs;
    }

    public static List<String>  getJDKNames(){
        List<JDK> jdkList = jenkins.model.Jenkins.getInstance().getJDKs();
        List<String> result = new ArrayList<String>();
        for(JDK jdk : jdkList) {
            result.add(jdk.getName());
        }
        return result;
    }

    /**
     * Returns a list of nodes the job could run on. If allowed nodes is empty,
     * it falls back to all nodes
     *
     * @return list of nodenames.
     */
    public List<String> getAllowedJDKs() {
        return allowedJDKs;
    }


    public String getDefaultJDK() {
        return defaultJDK;
    }


    public static List<String>  getSelectableJDKNames(){
        List<JDK> jdkList = jenkins.model.Jenkins.getInstance().getJDKs();
        List<String> result = new ArrayList<String>();
        for(JDK jdk : jdkList) {
            result.add(jdk.getName());
        }
        return result;
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jo) {
        final String name = jo.getString("name");
        final String description = jo.getString("description");
        final Object joValue = jo.get("defaultJDK");
        final JSONArray json_allowedJDKS = (JSONArray)jo.get("allowedJDKs");

        String defaultJDK = (String) joValue;
        List<String> allowedJDKs = new ArrayList<String>();

        for (Object strObj : json_allowedJDKS) {
           allowedJDKs.add((String) strObj);
        }

        JavaParameterValue value = new JavaParameterValue(name,defaultJDK, allowedJDKs, description);
        value.setDescription(getDescription());
        return value;
    }

    @Override
    public ParameterValue createValue(StaplerRequest req) {
        String[] value = req.getParameterValues(getName());
        String defaultJDK1 = (String)req.getAttribute("defaultJDK");
        String[] allowedJDKs = (String[]) req.getAttribute("allowedJDKs");
        String description = (String) req.getAttribute("description");
        List<String> jdks = new ArrayList<String>();
        Collections.addAll(jdks, allowedJDKs);

        if (value == null || value.length < 1) {
            return getDefaultParameterValue();
        } else {
            return new JavaParameterValue(getName(),  defaultJDK1, jdks, description);
        }
    }

    @Extension
    public static class DescriptorImpl extends ParameterDescriptor {

        @Override
        public String getDisplayName() {
            return "JDK Parameter";
        }
    }
}
