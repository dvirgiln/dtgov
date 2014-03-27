/*
 * @author David Virgil Naranjo
 */
package org.overlord.dtgov.ui.client.local.pages.workflowQuery;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

// TODO: Auto-generated Javadoc
/**
 * Custom GWT Event created to pass a signal from the modal box to the main page
 * that the Workflow Query has been removed and the list of items need to be
 * refreshed.
 * 
 * @author David Virgil Naranjo
 * 
 */
public class DeleteWorkflowQueryEvent extends GwtEvent<DeleteWorkflowQueryHandler> {

    /** The Constant TYPE. */
    public static final Type<DeleteWorkflowQueryHandler> TYPE = new Type<DeleteWorkflowQueryHandler>();

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
     */
    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<DeleteWorkflowQueryHandler> getAssociatedType() {
        return TYPE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared
     * .EventHandler)
     */
    @Override
    protected void dispatch(DeleteWorkflowQueryHandler handler) {
        handler.onWorkflowQueryDeleted(this);
    }

    /**
     * Register.
     * 
     * @param eventBus
     *            the event bus
     * @param handler
     *            the handler
     * @return the handler registration
     */
    public static HandlerRegistration register(EventBus eventBus, DeleteWorkflowQueryHandler handler) {
        return eventBus.addHandler(TYPE, handler);
    }

}
