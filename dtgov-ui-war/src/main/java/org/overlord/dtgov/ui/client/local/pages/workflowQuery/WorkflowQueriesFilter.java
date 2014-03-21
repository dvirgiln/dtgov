package org.overlord.dtgov.ui.client.local.pages.workflowQuery;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.overlord.dtgov.ui.client.local.beans.UiConfiguration;
import org.overlord.dtgov.ui.client.local.services.ConfigurationService;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueriesFilterBean;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * The workflow queries filtersPanel sidebar.  Whenever the user changes any of the settings in
 * the filter sidebar, a ValueChangeEvent will be fired.
 *
 * @author dvirgiln@redhat.com
 */
@Templated("/org/overlord/dtgov/ui/client/local/site/workflowQueries.html#queries-filter-sidebar")
@Dependent
public class WorkflowQueriesFilter extends Composite implements HasValue<WorkflowQueriesFilterBean> {
	
	 
    @Inject
    private ConfigurationService configService;
    
    private WorkflowQueriesFilterBean currentState = new WorkflowQueriesFilterBean();
    
	@Inject @DataField
	protected WorkflowTypeListBox workflow;
	
	@Inject @DataField
	protected TextBox name;
	
    @Inject @DataField
    protected Button clearFilters;
	
	public WorkflowQueriesFilter(){
		
	}
    /**
     * Called after construction and injection.
     */
    @SuppressWarnings("unchecked")
    @PostConstruct
    protected void postConstruct() {
       
       ClickHandler clearFilterHandler = new ClickHandler() {
           @Override
           public void onClick(ClickEvent event) {
               setValue(new WorkflowQueriesFilterBean(), true);
           }
       };
       clearFilters.addClickHandler(clearFilterHandler);
       
       @SuppressWarnings("rawtypes")
       ValueChangeHandler valueChangeHandler = new ValueChangeHandler() {
           @Override
           public void onValueChange(ValueChangeEvent event) {
               onFilterValueChange();
           }
       };
       workflow.addValueChangeHandler(valueChangeHandler);
       name.addValueChangeHandler(valueChangeHandler);
    }

    
    /**
     * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object, boolean)
     */
    @Override
    public void setValue(WorkflowQueriesFilterBean value, boolean fireEvents) {
        workflow.setValue(value.getWorkflow() == null ? "" : value.getWorkflow()); //$NON-NLS-1$
        name.setValue(value.getName() == null ? "" : value.getName()); //$NON-NLS-1$
        WorkflowQueriesFilterBean oldState = this.currentState;
        currentState = value;
        if (fireEvents) {
            ValueChangeEvent.fireIfNotEqual(this, oldState, currentState);
        }
    }
    
    /**
     * Refresh any data in the filter panel.
     */
    public void refresh() {
        UiConfiguration uiConfig = configService.getUiConfig();

        // Update the items in the deployment type drop-down
        this.workflow.clear();
        Map<String, String> workflowTypes = uiConfig.getWorkflowTypes();
        for (Entry<String, String> entry : workflowTypes.entrySet()) {
            this.workflow.addItem(entry.getKey(), entry.getValue());
        }

    }
    
    /**
     * Called whenever any filter value changes.
     */
    protected void onFilterValueChange() {
        WorkflowQueriesFilterBean newState = new WorkflowQueriesFilterBean();
        newState.setWorkflow(workflow.getValue()).setName(name.getValue());

        WorkflowQueriesFilterBean oldState = this.currentState;
        this.currentState = newState;
        // Only fire a change event if something actually changed.
        ValueChangeEvent.fireIfNotEqual(this, oldState, currentState);
    }
    /**
     * @return the current filter settings
     */
    public WorkflowQueriesFilterBean getValue() {
        return this.currentState;
    }

    /**
     * @param value the new filter settings
     */
    public void setValue(WorkflowQueriesFilterBean value) {
        setValue(value, false);
    }
    
    /**
     * @see com.google.gwt.event.logical.shared.HasValueChangeHandlers#addValueChangeHandler(com.google.gwt.event.logical.shared.ValueChangeHandler)
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<WorkflowQueriesFilterBean> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }
}
