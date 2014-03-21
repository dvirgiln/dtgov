package org.overlord.dtgov.ui.client.local.pages.workflowQuery;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.overlord.dtgov.ui.client.local.ClientMessages;
import org.overlord.dtgov.ui.client.local.beans.UiConfiguration;
import org.overlord.dtgov.ui.client.local.pages.deployments.AddDeploymentFormSubmitHandler;
import org.overlord.dtgov.ui.client.local.services.NotificationService;
import org.overlord.dtgov.ui.client.local.services.WorkflowQueriesRpcService;
import org.overlord.dtgov.ui.client.local.services.rpc.IRpcServiceInvocationHandler;
import org.overlord.dtgov.ui.client.shared.beans.NotificationBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryResultSetBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQuerySummaryBean;
import org.overlord.sramp.integration.java.model.JavaModel;
import org.overlord.sramp.ui.client.local.widgets.bootstrap.ModalDialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.InlineLabel;

@Templated("/org/overlord/dtgov/ui/client/local/site/dialogs/delete-workflow-query-dialog.html#delete-workflow-query-dialog")
@Dependent
public class DeleteWorkflowQueryDialog extends ModalDialog{

    @Inject @DataField("delete-workflow-submit-button")
    private Button submitButton;
    
    @Inject
    protected WorkflowQueriesRpcService workflowQueryService;
    
    @Inject
    protected NotificationService notificationService;
    
    @Inject
    protected ClientMessages i18n;
    
    private WorkflowQuerySummaryBean workflowQuery;
    
    private NotificationBean notification;
    
    @Inject @DataField("form-workflow-query-name-input")
    protected InlineLabel project_name;
    
    /**
     * Constructor.
     */
    public DeleteWorkflowQueryDialog() {
    }
    
    

    
    

    public void setWorkflowQuery(WorkflowQuerySummaryBean workflowQuery) {
		this.workflowQuery = workflowQuery;
		project_name.setText(workflowQuery.getName());
	}


    /**
     * Post construct.
     */
    @PostConstruct
    protected void onPostConstruct() {
    	if(workflowQuery!=null){
    		project_name.setText(workflowQuery.getName());
    	}
    	
    }



	/**
     * @see org.overlord.sramp.ui.client.local.widgets.bootstrap.ModalDialog#show()
     */
    @Override
    public void show() {
        super.show();
    }

    /**
     * Called when the user clicks the 'submit' (Add) button.
     * @param event
     */
    @EventHandler("delete-workflow-submit-button")
    public void onSubmitClick(ClickEvent event) {
        this.hide(false);

         notification = notificationService.startProgressNotification(
                i18n.format("delete-workflow-query-submit.deleting-query"), //$NON-NLS-1$
                i18n.format("delete-workflow-query-submit.deleting-query-msg")); //$NON-NLS-1$
    	
    	workflowQueryService.delete(workflowQuery.getUuid(),  new IRpcServiceInvocationHandler<Void>() {
            @Override
            public void onReturn(Void data) {
                	destroy();
                	notificationService.completeProgressNotification(
                            notification.getUuid(),
                            i18n.format("delete-workflow-query-submit.successfully-deleted"), //$NON-NLS-1$
                            i18n.format("delete-workflow-query-submit.successfully-deleted-msg",workflowQuery.getName())); //$NON-NLS-1$
               
            }
            @Override
            public void onError(Throwable error) {
            	notificationService.sendErrorNotification(i18n.format("delete-workflow-query-submit.error",workflowQuery.getName()), error); //$NON-NLS-1$

            }
        });
    }
}
