/*
 * @author David Virgil Naranjo
 */
package org.overlord.dtgov.ui.client.shared.beans;

import java.io.Serializable;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * The Class WorkflowQueriesFilterBean.
 */
@Portable
public class WorkflowQueriesFilterBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6203921263827575233L;

    /** The _workflow. */
    private String _workflow;

    /** The _name. */
    private String _name;

    /**
     * Instantiates a new workflow queries filter bean.
     */
    public WorkflowQueriesFilterBean() {

    }

    /**
     * Instantiates a new workflow queries filter bean.
     * 
     * @param workflow
     *            the workflow
     * @param name
     *            the name
     */
    public WorkflowQueriesFilterBean(String workflow, String name) {
        super();
        this._workflow = workflow;
        this._name = name;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            the name
     * @return the workflow queries filter bean
     */
    public WorkflowQueriesFilterBean setName(String name) {
        this._name = name;
        return this;
    }

    /**
     * Gets the workflow.
     * 
     * @return the workflow
     */
    public String getWorkflow() {
        return _workflow;
    }

    /**
     * Sets the workflow.
     * 
     * @param workflow
     *            the workflow
     * @return the workflow queries filter bean
     */
    public WorkflowQueriesFilterBean setWorkflow(String workflow) {
        this._workflow = workflow;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_name == null) ? 0 : _name.hashCode());
        result = prime * result + ((_workflow == null) ? 0 : _workflow.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WorkflowQueriesFilterBean other = (WorkflowQueriesFilterBean) obj;
        if (_name == null) {
            if (other._name != null)
                return false;
        } else if (!_name.equals(other._name))
            return false;
        if (_workflow == null) {
            if (other._workflow != null)
                return false;
        } else if (!_workflow.equals(other._workflow))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WorkflowQueriesFilterBean [workflow=" + _workflow + ", name=" + _name + "]";
    }

}
