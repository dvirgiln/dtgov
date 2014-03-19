package org.overlord.dtgov.ui.client.local.pages.workflowQuery;

import javax.inject.Inject;

import org.overlord.dtgov.ui.client.local.ClientMessages;
import org.overlord.dtgov.ui.client.local.widgets.common.AbstractFilterListBox;

public class WorkflowTypeListBox  extends AbstractFilterListBox {
	 @Inject
	    protected ClientMessages i18n;

	    /**
	     * Constructor.
	     */
	    public WorkflowTypeListBox() {
	    }

	    /**
	     * @see org.overlord.dtgov.ui.client.local.widgets.common.AbstractFilterListBox#configureItems()
	     */
	    @Override
	    protected void configureItems() {
	        this.addItem(i18n.format("any"), ""); //$NON-NLS-1$ //$NON-NLS-2$
	    }

	    /**
	     * @see com.google.gwt.user.client.ui.ListBox#clear()
	     */
	    @Override
	    public void clear() {
	        super.clear();
	        this.addItem(i18n.format("any"), ""); //$NON-NLS-1$ //$NON-NLS-2$
	    }

	    /**
	     * Clears all items - doesn't add the "Any" item back in.
	     */
	    public void clearAll() {
	        super.clear();
	    }

}
