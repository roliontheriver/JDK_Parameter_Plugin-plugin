package com.datalex.jdkparameter;

import hudson.Extension;
import hudson.model.*;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.util.ArrayList;
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
    List<JDK> allJDKs = jenkins.model.Jenkins.getInstance().getJDKs();



    @DataBoundConstructor
    public JavaParameterDefinition(String name,String description ,
                                   String defaultJDK, List<String>allowedJDKs ){
        super(name, description);
        this.defaultJDK = defaultJDK;
        this.allowedJDKs = allowedJDKs;
    }

    //gets the names of configured JDKs
    public static List<String>  getJDKNames(){
        List<String> result = getJDKSasStrings();
        result.add(0,"(Default)");
        result.add(0, "(All)");
        return result;
    }

    private static List<String> getJDKSasStrings() {
        List<JDK> jdkList = jenkins.model.Jenkins.getInstance().getJDKs();
        List<String> result = new ArrayList<String>();
        for(JDK jdk : jdkList) {
            result.add(jdk.getName());
        }
        return result;
    }
//    //gets the list of default Jenkins JDKs
//    public static String getBaseJDK(){
//        JDKInstaller.JDKFamilyList baseJDK = null;
//        try{
//        baseJDK = JDKInstaller.JDKList.all().get(JDKInstaller.JDKList.class).toList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String result = "";
//        for(JDKInstaller.JDKFamily jdk : baseJDK.data){
//            result = jdk.name;
//        }
//
//        return result;
//    }

    public List<String> getAllowedJDKs() {
        if (allowedJDKs.contains("(All)") ) {
            return getJDKSasStrings();
        } else {
            return allowedJDKs;
        }


    }


    public String getDefaultJDK() {
        return defaultJDK;
    }


    //gets the list of JDKs to put in "selectable JDKs" array in job config, includes the base JDKs from jenkins
    public List<String>  getSelectableJDKNames(){
        List<String> result = getJDKSasStrings();
//        String allJDKs = result;
//        result.add(allJDKs);
        return result;
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