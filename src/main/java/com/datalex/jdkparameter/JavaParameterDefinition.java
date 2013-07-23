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
    public final List<JDK> allowedJDKs;
   // List<JDK> allJDKs = jenkins.model.Jenkins.getInstance().getJDKs();



    @DataBoundConstructor
    public JavaParameterDefinition(String name,String description ,
                                   String defaultJDK, List<String> allowedJDKs ){
        super(name, description);
        this.defaultJDK = defaultJDK;

        if (allowedJDKs.contains("(All)") )  {
//            this.allowedJDKs = getJDKModels();
            this.allowedJDKs = jenkins.model.Jenkins.getInstance().getJDKs();

        } else {
            List<JDK> converted = new ArrayList<JDK>();
            for(String hashcode : allowedJDKs) {
                for(JDKModel model : getAllJDKModels()){
                    if(hashcode.equals( String.valueOf(model.getId()))) {
                        converted.add(model.getJdk());
                        continue;
                    }

                }
            }

            this.allowedJDKs = converted;
        }
    }

    //gets the names of configured JDKs
    public static List<JDKModel>  getJDKNames(){
        List<JDKModel> result = getAllJDKModels();
        result.add(1, new JDKModel(Integer.MIN_VALUE, "(Default)", null));
        result.add(0, new JDKModel(Integer.MIN_VALUE + 1,"(All)", null));
        return result;
    }

    private static List<JDKModel> getAllJDKModels() {
        List<JDK> jdkList = jenkins.model.Jenkins.getInstance().getJDKs();
        return getJDKasModels(jdkList);
    }

    private static List<JDKModel> getJDKasModels(List<JDK> jdkList) {
        List<JDKModel> result = new ArrayList<JDKModel>();
        for(JDK jdk : jdkList) {
            result.add(new JDKModel(jdk.getHome().hashCode(), jdk.getName(), jdk));
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

    public List<JDKModel> getAllowedJDKs() {
        return getJDKasModels(allowedJDKs);
    }


    public String getDefaultJDK() {
        return defaultJDK;
    }

//
//    //gets the list of JDKs to put in "selectable JDKs" array in job config, includes the base JDKs from jenkins
//    public List<String>  getSelectableJDKNames(){
//        List<String> result = getJDKModels();
//        //String allJDKs = result;
//        //result.add(allJDKs);
//
//        return result;
//    }


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
