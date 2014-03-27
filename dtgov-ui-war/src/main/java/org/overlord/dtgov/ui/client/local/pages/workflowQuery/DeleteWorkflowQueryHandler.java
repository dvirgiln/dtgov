/*
 * @author David Virgil Naranjo
 */
package org.overlord.dtgov.ui.client.local.pages.workflowQuery;

import com.google.gwt.event.shared.EventHandler;

// TODO: Auto-generated Javadoc
/**
 * Custom GWT Handler created to pass a signal from the modal box to the main
 * page that the Workflow Query has been removed and the list of items need to
 * be refreshed.
 * 
 * @author David Virgil Naranjo
 * 
 */
public interface DeleteWorkflowQueryHandler extends EventHandler {

    /**
     * On workflow query deleted.
     * 
     * @param event
     *            the event
     */
    void onWorkflowQueryDeleted(DeleteWorkflowQueryEvent event);

}
