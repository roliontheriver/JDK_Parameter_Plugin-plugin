package org.jvnet.jenkins.plugins.javachoiceparameter.JavaChoiceParameterValue;

import hudson.model.ChoiceParameterDefinition;
import hudson.model.JobParameterValue;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: barisbatiege
 * Date: 6/14/13
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class JavaParameterDefinition extends ChoiceParameterDefinition {

    static final String VERSION = "version";

    @DataBoundConstructor
    public JavaParameterDefinition(){
        super(VERSION,(String[]) null, null);
    }

    @Override
    public String getDescription() {
        return "JDK  Parameter";
    }

//    @Override
//    public JavaParameterValue createValue(CLICommand command, String value) throws IOException, InterruptedException {
//        return wrap((JobParameterValue) super.createValue(command, value));
//    }

    @Override
    public JavaParameterValue createValue(StaplerRequest req, JSONObject jo) {
        return wrap((JobParameterValue) super.createValue(req, jo));
    }

    private JavaParameterValue wrap(JobParameterValue rhs) {
        try {
            // TODO: once we bump up to 1.486 or so, there should be a getter for this
            Field jdk_field = JobParameterValue.class.getDeclaredField("version");
            jdk_field.setAccessible(true);

            JavaParameterValue v = new JavaParameterValue(rhs.getName(), (String) jdk_field.get(rhs));
            return v;
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }
    }
}
