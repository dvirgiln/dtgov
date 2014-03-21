package org.overlord.dtgov.ui.client.local.pages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.jboss.errai.databinding.client.api.PropertyChangeEvent;
import org.jboss.errai.databinding.client.api.PropertyChangeHandler;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageState;
import org.jboss.errai.ui.nav.client.local.TransitionAnchor;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.overlord.commons.gwt.client.local.widgets.TemplatedFormPanel;
import org.overlord.dtgov.ui.client.local.pages.workflowQuery.WorkflowTypeListBox;
import org.overlord.dtgov.ui.client.local.services.WorkflowQueriesRpcService;
import org.overlord.dtgov.ui.client.local.util.DOMUtil;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.TextBox;

@Templated("/org/overlord/dtgov/ui/client/local/site/workflowQuery.html#page")
@Page(path="workflowQuery")
@Dependent
public class WorkflowQueryPage extends AbstractPage {
	// Breadcrumbs
    @Inject @DataField("back-to-dashboard")
    TransitionAnchor<DashboardPage> backToDashboard;
    
	// Breadcrumbs
    @Inject @DataField("back-to-admin-queries")
    TransitionAnchor<WorkflowQueriesPage> backToQueries;
    
    @Inject @DataField("add-workflow-query-form")
    private TemplatedFormPanel form;
    
    @Inject @DataField("form-workflow-query-input")
    protected TextBox queryBox;
    
    @Inject @DataField("form-workflow-description-input")
    protected TextBox descriptionBox;
    
	@Inject @DataField("form-workflow-type-input")
	protected WorkflowTypeListBox workflow;
	
    @Inject @DataField("form-workflow-query-name-input")
    protected TextBox queryNameBox;
	
    /*@PageState
    private String uuid;
    */
    @Inject
    protected WorkflowQueriesRpcService workflowQueryService;
    
    /**
     * Called after the widget is constructed.
     */
    /*@PostConstruct
    protected void onPostConstruct() {
       if(StringUtils.isNotBlank(uuid)){
    	   
       }
    }*/
    
    
    
}
