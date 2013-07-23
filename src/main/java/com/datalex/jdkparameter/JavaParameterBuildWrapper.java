package com.datalex.jdkparameter;

import hudson.tasks.BuildWrapper;

/**
 * Created with IntelliJ IDEA.
 * User: barisbatiege
 * Date: 6/28/13
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class JavaParameterBuildWrapper extends BuildWrapper {

//    @Override
//    public Environment setUp(AbstractBuild build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException{
//        String jdkValue = (String)build.getBuildVariables().get("jdk");
//
//        EnvVars env = build.getEnvironment(listener);
//        env.overrideAll(build.getBuildVariables());
//
//        boolean success = true;
//
//        return null;
//
//    }
//
////    @Extension
//    public static class DescriptorImpl extends BuildWrapperDescriptor {
//
//        @Override
//        public String getDisplayName() {
//            return "Select JDK to be used";
//        }
//
//        @Override
//        public boolean isApplicable(AbstractProject<?, ?> item) {
//            return true;
//        }
//
//    }
}
