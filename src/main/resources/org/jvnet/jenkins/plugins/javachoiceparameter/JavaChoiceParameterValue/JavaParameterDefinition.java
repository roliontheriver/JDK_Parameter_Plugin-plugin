package org.jvnet.jenkins.plugins.javachoiceparameter.JavaChoiceParameterValue;

import hudson.model.ChoiceParameterDefinition;
import hudson.model.JobParameterValue;
import hudson.model.ParameterValue;
import hudson.model.SimpleParameterDefinition;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
    public JavaParameterDefinition(String name, String version, List<String>allowedJDKs){
        super(name, version);
        this.defaultJDKs = defaultJDKs;
        this.allowedJDKs = allowedJDKs;
    }

    public String getJDKNames = "JDKs,JDKs everywhere";


    @Override
    public String getDescription() {
        return "JDK  Parameter";
    }
    @Deprecated
        public JavaParameterDefinition(String name, String version, List<String> defaultJDKs, List<String> allowedJDKs)


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
}
