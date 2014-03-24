package org.overlord.dtgov.ui.client.local.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.overlord.dtgov.ui.client.local.services.rpc.DelegatingErrorCallback;
import org.overlord.dtgov.ui.client.local.services.rpc.DelegatingRemoteCallback;
import org.overlord.dtgov.ui.client.local.services.rpc.IRpcServiceInvocationHandler;
import org.overlord.dtgov.ui.client.shared.beans.DeploymentBean;
import org.overlord.dtgov.ui.client.shared.beans.DeploymentResultSetBean;
import org.overlord.dtgov.ui.client.shared.beans.DeploymentsFilterBean;
import org.overlord.dtgov.ui.client.shared.beans.DerivedArtifactsBean;
import org.overlord.dtgov.ui.client.shared.beans.ExpandedArtifactsBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueriesFilterBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryResultSetBean;
import org.overlord.dtgov.ui.client.shared.exceptions.DtgovUiException;
import org.overlord.dtgov.ui.client.shared.services.IDeploymentsService;
import org.overlord.dtgov.ui.client.shared.services.IWorkflowQueryService;

/**
 * Client-side service for making RPC calls to the remote workflow queries service.
 *
 * @author dvirgiln@redhat.com
 */
@ApplicationScoped
public class WorkflowQueriesRpcService{

    @Inject
    private Caller<IWorkflowQueryService> remoteWorkflowQueryService;

    /**
     * Constructor.
     */
    public WorkflowQueriesRpcService() {
    }

    /**
     * @see org.overlord.dtgov.ui.client.shared.services.IDeploymentsService#search(DeploymentsFilterBean, String, int, String, boolean)
     */
    public void search(WorkflowQueriesFilterBean filters, int page, String sortColumnId, boolean sortAscending,
            final IRpcServiceInvocationHandler<WorkflowQueryResultSetBean> handler) {
        // TODO only allow one search at a time.  If another search comes in before the previous one
        // finished, cancel the previous one.  In other words, only return the results of the *last*
        // search performed.
        RemoteCallback<WorkflowQueryResultSetBean> successCallback = new DelegatingRemoteCallback<WorkflowQueryResultSetBean>(handler);
        ErrorCallback<?> errorCallback = new DelegatingErrorCallback(handler);
        try {
        	remoteWorkflowQueryService.call(successCallback, errorCallback).search(filters, page, sortColumnId, sortAscending);
        } catch (DtgovUiException e) {
            errorCallback.error(null, e);
        }
    }


    /**
     * @see org.overlord.dtgov.ui.client.shared.services.IDeploymentsService#search(DeploymentsFilterBean, String, int, String, boolean)
     */
    public void delete(String uuid,
            final IRpcServiceInvocationHandler<Void> handler) {
        RemoteCallback<Void> successCallback = new DelegatingRemoteCallback<Void>(handler);
        ErrorCallback<?> errorCallback = new DelegatingErrorCallback(handler);
        try {
        	remoteWorkflowQueryService.call(successCallback, errorCallback).delete(uuid);
        } catch (DtgovUiException e) {
            errorCallback.error(null, e);
        }
    }
    
    
    /**
     * @see org.overlord.dtgov.ui.client.shared.services.IDeploymentsService#search(DeploymentsFilterBean, String, int, String, boolean)
     */
    public void get(String uuid,
            final IRpcServiceInvocationHandler<WorkflowQueryBean> handler) {
        RemoteCallback<WorkflowQueryBean> successCallback = new DelegatingRemoteCallback<WorkflowQueryBean>(handler);
        ErrorCallback<?> errorCallback = new DelegatingErrorCallback(handler);
        try {
        	remoteWorkflowQueryService.call(successCallback, errorCallback).get(uuid);
        } catch (DtgovUiException e) {
            errorCallback.error(null, e);
        }
    }


}




