package org.overlord.dtgov.ui.client.local.pages;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.databinding.client.api.PropertyChangeEvent;
import org.jboss.errai.databinding.client.api.PropertyChangeHandler;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageState;
import org.jboss.errai.ui.nav.client.local.TransitionAnchor;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.overlord.commons.gwt.client.local.widgets.HtmlSnippet;
import org.overlord.commons.gwt.client.local.widgets.TemplatedFormPanel;
import org.overlord.dtgov.ui.client.local.ClientMessages;
import org.overlord.dtgov.ui.client.local.beans.UiConfiguration;
import org.overlord.dtgov.ui.client.local.pages.workflowQuery.WorkflowTypeListBox;
import org.overlord.dtgov.ui.client.local.services.ConfigurationService;
import org.overlord.dtgov.ui.client.local.services.NotificationService;
import org.overlord.dtgov.ui.client.local.services.WorkflowQueriesRpcService;
import org.overlord.dtgov.ui.client.local.services.rpc.IRpcServiceInvocationHandler;
import org.overlord.dtgov.ui.client.local.util.DOMUtil;
import org.overlord.dtgov.ui.client.shared.beans.DeploymentBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryResultSetBean;

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
    
    @Inject
    protected ClientMessages i18n;
    
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
	
    @PageState
    private String uuid;
    
    @Inject
    protected WorkflowQueriesRpcService workflowQueryService;
   
    @Inject @DataField("workflow-query-loading-spinner")
    protected HtmlSnippet workflowQueryLoading;
    
    protected Element pageContent;
    
    @Inject
    protected NotificationService notificationService;
    
	 
    @Inject
    private ConfigurationService configService;
    
    /**
     * Called after the widget is constructed.
     */
    @PostConstruct
    protected void onPostConstruct() {
    	pageContent = DOMUtil.findElementById(getElement(), "workflow-query-content-wrapper"); //$NON-NLS-1$
    	
        UiConfiguration uiConfig = configService.getUiConfig();

       
        this.workflow.clear();
        Map<String, String> workflowTypes = uiConfig.getWorkflowTypes();
        for (Entry<String, String> entry : workflowTypes.entrySet()) {
            this.workflow.addItem(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * @see org.overlord.sramp.ui.client.local.pages.AbstractPage#onPageShowing()
     */
    @Override
    protected void onPageShowing() {
    	System.out.println("");
    	if(uuid!=null && !uuid.isEmpty()){
    		pageContent.addClassName("hide"); //$NON-NLS-1$
            workflowQueryLoading.getElement().removeClassName("hide"); //$NON-NLS-1$
            
            workflowQueryService.get(uuid,
                    new IRpcServiceInvocationHandler<WorkflowQueryBean>() {
                @Override
                public void onReturn(WorkflowQueryBean data) {
                   updateContent(data);
                }
                @Override
                public void onError(Throwable error) {
                    notificationService.sendErrorNotification(i18n.format("deployments.error-loading"), error); //$NON-NLS-1$
                    workflowQueryLoading.getElement().addClassName("hide");
                }
            });
    	}
       // if(StringUtils.isNotBlank(uuid)){
        	/* pageContent.addClassName("hide"); //$NON-NLS-1$
             workflowQueryLoading.getElement().removeClassName("hide"); //$NON-NLS-1$
             
             workflowQueryService.get(uuid,
                     new IRpcServiceInvocationHandler<WorkflowQueryBean>() {
                 @Override
                 public void onReturn(WorkflowQueryBean data) {
                    updateContent(data);
                 }
                 @Override
                 public void onError(Throwable error) {
                     notificationService.sendErrorNotification(i18n.format("deployments.error-loading"), error); //$NON-NLS-1$
                     workflowQueryLoading.getElement().addClassName("hide");
                 }
             });*/
             
        //}
       
    }
    /**
     * Called when the deployment is loaded.
     * @param deployment
     */
    protected void updateContent(WorkflowQueryBean data) {
        pageContent.removeClassName("hide"); //$NON-NLS-1$
        
        queryNameBox.setValue(data.getName());
        
        queryBox.setValue(data.getQuery());
        
        descriptionBox.setValue(data.getDescription());
        
        for(int i=0;i<workflow.getItemCount();i++){
        	if(workflow.getItemText(i).equals(data.getWorkflow())){
        		workflow.setSelectedIndex(i);
        		break;
        	}
        }
    }
    
    
}
