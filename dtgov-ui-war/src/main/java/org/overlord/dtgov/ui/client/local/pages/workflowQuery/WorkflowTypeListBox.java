/*
 * @author David Virgil Naranjo
 */
package org.overlord.dtgov.ui.client.local.pages.workflowQuery;

import javax.inject.Inject;

import org.overlord.dtgov.ui.client.local.ClientMessages;
import org.overlord.dtgov.ui.client.local.widgets.common.AbstractFilterListBox;

// TODO: Auto-generated Javadoc
/**
 * Select box that store the Workflow Types.
 * 
 * @author David Virgil Naranjo
 */
public class WorkflowTypeListBox extends AbstractFilterListBox {

    /** The _i18n. */
    @Inject
    private ClientMessages _i18n;

    /**
     * Constructor.
     */
    public WorkflowTypeListBox() {
    }

    /**
     * Configure items.
     * 
     * @see org.overlord.dtgov.ui.client.local.widgets.common.AbstractFilterListBox#configureItems()
     */
    @Override
    protected void configureItems() {
        this.addItem(_i18n.format("any"), ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Clear.
     * 
     * @see com.google.gwt.user.client.ui.ListBox#clear()
     */
    @Override
    public void clear() {
        super.clear();
        this.addItem(_i18n.format("any"), ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Clears all items - doesn't add the "Any" item back in.
     */
    public void clearAll() {
        super.clear();
    }

    /**
     * Gets the i18n.
     * 
     * @return the i18n
     */
    public ClientMessages getI18n() {
        return _i18n;
    }

    /**
     * Sets the i18n.
     * 
     * @param i18n
     *            the new i18n
     */
    public void setI18n(ClientMessages i18n) {
        this._i18n = i18n;
    }

}
