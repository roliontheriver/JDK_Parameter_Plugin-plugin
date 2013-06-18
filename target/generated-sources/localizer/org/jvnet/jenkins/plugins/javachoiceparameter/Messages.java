// CHECKSTYLE:OFF

package org.jvnet.jenkins.plugins.javachoiceparameter;

import org.jvnet.localizer.Localizable;
import org.jvnet.localizer.ResourceBundleHolder;

@SuppressWarnings({
    "",
    "PMD"
})
public class Messages {

    private final static ResourceBundleHolder holder = ResourceBundleHolder.get(Messages.class);

    /**
     * Trigger builds on selected nodes
     * 
     */
    public static String NextNodeBuildNotifier_displayName() {
        return holder.format("NextNodeBuildNotifier.displayName");
    }

    /**
     * Trigger builds on selected nodes
     * 
     */
    public static Localizable _NextNodeBuildNotifier_displayName() {
        return new Localizable(holder, "NextNodeBuildNotifier.displayName");
    }

    /**
     * Node-List Factory
     * 
     */
    public static String NodeListBuildParameterFactory_displayName() {
        return holder.format("NodeListBuildParameterFactory.displayName");
    }

    /**
     * Node-List Factory
     * 
     */
    public static Localizable _NodeListBuildParameterFactory_displayName() {
        return new Localizable(holder, "NodeListBuildParameterFactory.displayName");
    }

    /**
     * Run with label [{0}]
     * 
     */
    public static String LabelBadgeAction_label_tooltip(Object arg1) {
        return holder.format("LabelBadgeAction.label_tooltip", arg1);
    }

    /**
     * Run with label [{0}]
     * 
     */
    public static Localizable _LabelBadgeAction_label_tooltip(Object arg1) {
        return new Localizable(holder, "LabelBadgeAction.label_tooltip", arg1);
    }

    /**
     * The project is configured to NOT run builds concurrent, but the node parameter [{0}] is configured to trigger new builds concurrent!
     * 
     */
    public static String BuildWrapper_project_not_concurrent(Object arg1) {
        return holder.format("BuildWrapper.project_not_concurrent", arg1);
    }

    /**
     * The project is configured to NOT run builds concurrent, but the node parameter [{0}] is configured to trigger new builds concurrent!
     * 
     */
    public static Localizable _BuildWrapper_project_not_concurrent(Object arg1) {
        return new Localizable(holder, "BuildWrapper.project_not_concurrent", arg1);
    }

    /**
     * Run with label [{0}] (node: {1})
     * 
     */
    public static String LabelBadgeAction_label_tooltip_node(Object arg1, Object arg2) {
        return holder.format("LabelBadgeAction.label_tooltip_node", arg1, arg2);
    }

    /**
     * Run with label [{0}] (node: {1})
     * 
     */
    public static Localizable _LabelBadgeAction_label_tooltip_node(Object arg1, Object arg2) {
        return new Localizable(holder, "LabelBadgeAction.label_tooltip_node", arg1, arg2);
    }

    /**
     * Skipping execution on offline node [{0}] 
     * 
     */
    public static String NodeListBuildParameterFactory_skippOfflineNode(Object arg1) {
        return holder.format("NodeListBuildParameterFactory.skippOfflineNode", arg1);
    }

    /**
     * Skipping execution on offline node [{0}] 
     * 
     */
    public static Localizable _NodeListBuildParameterFactory_skippOfflineNode(Object arg1) {
        return new Localizable(holder, "NodeListBuildParameterFactory.skippOfflineNode", arg1);
    }

    /**
     * Please define a node where the job should be triggered on
     * 
     */
    public static String NodeListBuildParameterFactory_nodeNotDefined() {
        return holder.format("NodeListBuildParameterFactory.nodeNotDefined");
    }

    /**
     * Please define a node where the job should be triggered on
     * 
     */
    public static Localizable _NodeListBuildParameterFactory_nodeNotDefined() {
        return new Localizable(holder, "NodeListBuildParameterFactory.nodeNotDefined");
    }

    /**
     * Build on every online node
     * 
     */
    public static String AllNodesBuildParameterFactory_displayName() {
        return holder.format("AllNodesBuildParameterFactory.displayName");
    }

    /**
     * Build on every online node
     * 
     */
    public static Localizable _AllNodesBuildParameterFactory_displayName() {
        return new Localizable(holder, "AllNodesBuildParameterFactory.displayName");
    }

    /**
     * A node with the name {0} could not be found.
     * 
     */
    public static String NodeListBuildParameterFactory_nodeNotFound(Object arg1) {
        return holder.format("NodeListBuildParameterFactory.nodeNotFound", arg1);
    }

    /**
     * A node with the name {0} could not be found.
     * 
     */
    public static Localizable _NodeListBuildParameterFactory_nodeNotFound(Object arg1) {
        return new Localizable(holder, "NodeListBuildParameterFactory.nodeNotFound", arg1);
    }

    /**
     * A build with label/node [{0}] was requested
     * 
     */
    public static String NextLabelCause_description(Object arg1) {
        return holder.format("NextLabelCause.description", arg1);
    }

    /**
     * A build with label/node [{0}] was requested
     * 
     */
    public static Localizable _NextLabelCause_description(Object arg1) {
        return new Localizable(holder, "NextLabelCause.description", arg1);
    }

    /**
     * The project is configured to run builds concurrent, but the node parameter [{0}] is configured to trigger new builds depending on the state of the last build only!
     * 
     */
    public static String BuildWrapper_param_not_concurrent(Object arg1) {
        return holder.format("BuildWrapper.param_not_concurrent", arg1);
    }

    /**
     * The project is configured to run builds concurrent, but the node parameter [{0}] is configured to trigger new builds depending on the state of the last build only!
     * 
     */
    public static Localizable _BuildWrapper_param_not_concurrent(Object arg1) {
        return new Localizable(holder, "BuildWrapper.param_not_concurrent", arg1);
    }

    /**
     * All Nodes for Label Factory
     * 
     */
    public static String AllNodesForLabelBuildParameterFactory_displayName() {
        return holder.format("AllNodesForLabelBuildParameterFactory.displayName");
    }

    /**
     * All Nodes for Label Factory
     * 
     */
    public static Localizable _AllNodesForLabelBuildParameterFactory_displayName() {
        return new Localizable(holder, "AllNodesForLabelBuildParameterFactory.displayName");
    }

    /**
     * Run on node: {0}
     * 
     */
    public static String LabelBadgeAction_node_tooltip(Object arg1) {
        return holder.format("LabelBadgeAction.node_tooltip", arg1);
    }

    /**
     * Run on node: {0}
     * 
     */
    public static Localizable _LabelBadgeAction_node_tooltip(Object arg1) {
        return new Localizable(holder, "LabelBadgeAction.node_tooltip", arg1);
    }

}
