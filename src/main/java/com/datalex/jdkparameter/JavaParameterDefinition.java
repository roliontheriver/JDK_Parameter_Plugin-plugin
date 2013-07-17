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
    public final String baseJDK;



    @DataBoundConstructor
    public JavaParameterDefinition(String name,String description ,
                                   String defaultJDK, List<String>allowedJDKs, String baseJDK ){
        super(name, description);
        this.defaultJDK = defaultJDK;
        this.allowedJDKs = allowedJDKs;
        this.baseJDK = baseJDK;
    }

    //gets the names of configured JDKs
    public static List<String>  getJDKNames(){
        List<JDK> jdkList = jenkins.model.Jenkins.getInstance().getJDKs();
        List<String> result = new ArrayList<String>();
        for(JDK jdk : jdkList) {
            result.add(jdk.getName());
        }
        result.add(0, "(Default)");
        return result;
    }
    //gets the list of default Jenkins JDKs
    public static String getBaseJDK(){
        JDKInstaller.JDKFamilyList baseJDK = null;
        try{
        baseJDK = JDKInstaller.JDKList.all().get(JDKInstaller.JDKList.class).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = "";
        for(JDKInstaller.JDKFamily jdk : baseJDK.data){
            result = jdk.name;
        }

        return result;
    }

    public List<String> getAllowedJDKs() {
        return allowedJDKs;
    }


    public String getDefaultJDK() {
        return defaultJDK;
    }


    //gets the list of JDKs to put in "selectable JDKs" array in job config, includes the base JDKs from jenkins
    public List<String>  getSelectableJDKNames(){
        List<JDK> jdkList = jenkins.model.Jenkins.getInstance().getJDKs();
        List<String> result = new ArrayList<String>();
        for(JDK jdk : jdkList) {
            result.add(jdk.getName());
            result.add(baseJDK);
        }
        if (!jdkList.isEmpty())
        result.add(baseJDK);

        return result;
    }

    //WIP, supposed to get JDK from node and add it to Jenkins default JDKs list for this instance
    public JDK forNode(Node node, TaskListener log) throws IOException, InterruptedException{
        return new JDK(getBaseJDK(), translateFor(node, log));
    }

    private String translateFor(Node node, TaskListener log) {
        return null;
    }


    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jo) {
        final String name = jo.getString("name");
        final String selectedJDK = jo.getString("selectedJDK");
        return  new JavaParameterValue(name, getDescription(), selectedJDK);
    }

    @Override
    public ParameterValue createValue(StaplerRequest req) {
        String name = (String)req.getAttribute("name");
        String selectedJDK = (String)req.getAttribute("selectedJDK");
        return new JavaParameterValue(name, getDescription(), selectedJDK);
    }

    @Extension
    public static class DescriptorImpl extends ParameterDescriptor {

        @Override
        public String getDisplayName() {
            return "JDK Parameter";
        }
    }
}
