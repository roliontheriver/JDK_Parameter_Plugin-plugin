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
    public final List<String> defaultJDKs;
    public final List<String> allowedJDKs;
    @Deprecated
    public transient String defaultValue;

    @DataBoundConstructor
    public JavaParameterDefinition(String name, String version, List<String>allowedJDKs, List<String>defaultJDKs){
        super(name, version);
        this.defaultJDKs = defaultJDKs;
        this.allowedJDKs = allowedJDKs;
    }

    public static List<String>  getJDKNames(){
        JDKInstaller.JDKFamilyList list = null;
        try {
            list = JDKInstaller.JDKList.all().get(JDKInstaller.JDKList.class).toList();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        List<String> result = new ArrayList<String>();
        for(JDKInstaller.JDKFamily jdk : list.data) {
            result.add(jdk.name);
        }

        return result;
    }

    public static List<String>  getSelectableJDKNames(){
        JDKInstaller.JDKFamilyList list = null;
        try {
            list = JDKInstaller.JDKList.all().get(JDKInstaller.JDKList.class).toList();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        List<String> result = new ArrayList<String>();
        for(JDKInstaller.JDKFamily jdk : list.data) {
            result.add(jdk.name);
        }

        return result;
    }


    @Override
    public String getDescription() {
        return "JDK  Parameter";
    }


    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jo) {
        final String name = jo.getString("name");
        final Object joValue = jo.get("defaultJDKs") == null ? jo.get("allJDKs") : jo.get("defaultJDKs");

        List<String> defaultJDKs = new ArrayList<String>();
        if (joValue instanceof String){
            defaultJDKs.add((String) joValue);
        }
        else if (joValue instanceof JSONArray) {
                JSONArray ja = (JSONArray) joValue;
                for (Object strObj : ja) {
                    defaultJDKs.add((String) strObj);

            }
        }

        JavaParameterValue value = new JavaParameterValue(name, defaultJDKs);
        value.setDescription(getDescription());
        return value;
    }

    @Override
    public ParameterValue createValue(StaplerRequest req) {
        String[] value = req.getParameterValues(getName());
        if (value == null || value.length < 1) {
            return getDefaultParameterValue();
        } else {
            return new JavaParameterValue(getName(), Arrays.asList("JDK1", "JDK2"));
        }
    }

//    /**
//     * Gets the names of all configured slaves, regardless whether they are
//     * online.
//     *
//     * @return list with all slave names
//     */
//    @SuppressWarnings("deprecation")
//    public static List<String> getJDKNames() {
//        ComputerSet computers = Hudson.getInstance().getComputer();
//        List<String> slaveNames = computers.get_slaveNames();
//
//        // slaveNames is unmodifiable, therefore create a new list
//        List<String> test = new ArrayList<String>();
//        test.addAll(slaveNames);
//
//        // add 'magic' name for master, so all nodes can be handled the same way
//        if (!test.contains("master")) {
//            test.add(0, "master");
//        }
//        return test;
//    }


    @Extension
    public static class DescriptorImpl extends ParameterDescriptor {

        @Override
        public String getDisplayName() {
            return "JDK Choice Parameter";
        }

//        @Override
//        public String getHelpFile() {
//            return "/plugin/validating-string-parameter/help.html";
//        }

//        /**
//         * Called to validate the passed user entered value against the configured regular expression.
//         */
//        public FormValidation doValidate(@QueryParameter("regex") String regex,
//                                         @QueryParameter("failedValidationMessage") final String failedValidationMessage,
//                                         @QueryParameter("value") final String value) {
//            try {
//                if (Pattern.matches(regex, value)) {
//                    return FormValidation.ok();
//                } else {
//                    return failedValidationMessage == null || "".equals(failedValidationMessage)
//                            ? FormValidation.error("Value entered does not match regular expression: " + regex)
//                            : FormValidation.error(failedValidationMessage);
//                }
//            } catch (PatternSyntaxException pse) {
//                return FormValidation.error("Invalid regular expression [" + regex + "]: " + pse.getDescription());
//            }
//        }
    }
}
