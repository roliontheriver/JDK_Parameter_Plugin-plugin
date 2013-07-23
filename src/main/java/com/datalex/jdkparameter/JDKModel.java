package com.datalex.jdkparameter;

import hudson.model.JDK;

/**
 * Created with IntelliJ IDEA.
 * User: barisbatiege
 * Date: 7/23/13
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class JDKModel {

    protected int id;
    protected String description;
    protected JDK jdk;

    public JDKModel(int id, String description, JDK jdk) {
        this.id = id;
        this.description = description;
        this.jdk = jdk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JDK getJdk() {
        return jdk;
    }

    public void setJdk(JDK jdk) {
        this.jdk = jdk;
    }
}
