/*
 * @author David Virgil Naranjo
 */
package org.overlord.dtgov.ui.client.shared.beans;

import java.io.Serializable;

import org.jboss.errai.common.client.api.annotations.Portable;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkflowQueryProperty.
 */
@Portable
public class WorkflowQueryProperty implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -939097586625281875L;

    /** The _key. */
    private String _key;

    /** The _value. */
    private String _value;

    /**
     * Instantiates a new workflow query property.
     */
    public WorkflowQueryProperty() {

    }

    /**
     * Gets the key.
     * 
     * @return the key
     */
    public String getKey() {
        return _key;
    }

    /**
     * Sets the key.
     * 
     * @param key
     *            the key
     * @return the workflow query property
     */
    public WorkflowQueryProperty setKey(String key) {
        this._key = key;
        return this;
    }

    /**
     * Gets the value.
     * 
     * @return the value
     */
    public String getValue() {
        return _value;
    }

    /**
     * Sets the value.
     * 
     * @param value
     *            the value
     * @return the workflow query property
     */
    public WorkflowQueryProperty setValue(String value) {
        this._value = value;
        return this;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_key == null) ? 0 : _key.hashCode());
        result = prime * result + ((_value == null) ? 0 : _value.hashCode());
        return result;
    }

    /* (non-Javadoc)
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
        WorkflowQueryProperty other = (WorkflowQueryProperty) obj;
        if (_key == null) {
            if (other._key != null)
                return false;
        } else if (!_key.equals(other._key))
            return false;
        if (_value == null) {
            if (other._value != null)
                return false;
        } else if (!_value.equals(other._value))
            return false;
        return true;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WorkflowQueryProperty [key=" + _key + ", value=" + _value + "]";
    }

}
