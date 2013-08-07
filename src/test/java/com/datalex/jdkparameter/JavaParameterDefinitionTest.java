package com.datalex.jdkparameter;

import junit.framework.Assert;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: barisbatiege
 * Date: 6/21/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class JavaParameterDefinitionTest {

    @org.junit.Rule
    public JenkinsRule j = new JenkinsRule();

    @Test
    public void testSomething() {
        Collection<String> jdkList  = JavaParameterDefinition.getJDKNames();
        Assert.assertNotNull(jdkList);
        Assert.assertTrue(jdkList.isEmpty());
    }

    @Test
    public void testGetJDKNames(){
        List<String> JDKs = JavaParameterDefinition.getJDKNames();
        Assert.assertNotNull();
    }





}
