package org.jvnet.jenkins.plugins.javachoiceparameter.JavaChoiceParameterValue;

import hudson.model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.lang.reflect.Field;
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
public class JavaParameterDefinition extends SimpleParameterDefinition {

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

//    public static List<String>  getJDKNames(){
//        return Arrays.asList("JDK1", "JDK2");
//    }

    public static List<String>  getSelectableJDKNames(){
        return Arrays.asList("JDK1", "JDK2");
    }


    @Override
    public String getDescription() {
        return "JDK  Parameter";
    }


    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jo) {
        final String name = jo.getString("name");
        final Object joValue = jo.get("value") == null ? jo.get("jdks") : jo.get("value");

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
    public ParameterValue createValue(String value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets the names of all configured slaves, regardless whether they are
     * online.
     *
     * @return list with all slave names
     */
    @SuppressWarnings("deprecation")
    public static List<String> getJDKNames() {
        ComputerSet computers = Hudson.getInstance().getComputer();
        List<String> slaveNames = computers.get_slaveNames();

        // slaveNames is unmodifiable, therefore create a new list
        List<String> test = new ArrayList<String>();
        test.addAll(slaveNames);

        // add 'magic' name for master, so all nodes can be handled the same way
        if (!test.contains("master")) {
            test.add(0, "master");
        }
        return test;
    }
}
