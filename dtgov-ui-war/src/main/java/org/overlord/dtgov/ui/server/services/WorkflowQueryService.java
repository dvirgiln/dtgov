package org.overlord.dtgov.ui.server.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.errai.bus.server.annotations.Service;
import org.overlord.dtgov.ui.client.shared.beans.DeploymentResultSetBean;
import org.overlord.dtgov.ui.client.shared.beans.DeploymentSummaryBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueriesFilterBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryProperty;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryResultSetBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQuerySummaryBean;
import org.overlord.dtgov.ui.client.shared.exceptions.DtgovUiException;
import org.overlord.dtgov.ui.client.shared.services.IWorkflowQueryService;
import org.overlord.dtgov.ui.server.DtgovUIConfig;
import org.overlord.dtgov.ui.server.services.sramp.SrampApiClientAccessor;
import org.overlord.sramp.atom.err.SrampAtomException;
import org.overlord.sramp.client.SrampClientException;
import org.overlord.sramp.client.SrampClientQuery;
import org.overlord.sramp.client.query.ArtifactSummary;
import org.overlord.sramp.client.query.QueryResultSet;
import org.overlord.sramp.common.ArtifactType;


/**
 * Concrete implementation of the workflow query service.
 *
 * @author dvirgiln@redhat.com
 */
@Service
public class WorkflowQueryService implements IWorkflowQueryService{

    private static final int PAGE_SIZE = 10;
    
    public WorkflowQueryService(){
    	
    }
    
	@Override
	public WorkflowQueryResultSetBean search(WorkflowQueriesFilterBean filters,
			int page, String sortColumnId, boolean sortAscending)
			throws DtgovUiException {
		// TODO GET THE QUERIES FROM THE CORRECT PLACE, EITHER FILESYSTEM OR S-RAMP
		
        int pageSize = PAGE_SIZE;
        WorkflowQueryResultSetBean rval = new WorkflowQueryResultSetBean();
       List<WorkflowQuerySummaryBean> queries=new ArrayList<WorkflowQuerySummaryBean>();
      int total_results=15;
       for(int i=0;i<total_results;i++){
    	   
           WorkflowQuerySummaryBean query1=new WorkflowQuerySummaryBean();
           query1.setDescription("to be done"+i);
           query1.setQuery("/s-ramp/ext/SwitchYardApplication");
           query1.setUuid(""+i);
           query1.setName("Name"+i);
           query1.setWorkflow("overlord.demo.SimpleReleaseProcess");
           queries.add(query1);
       }
       
       
       int startIndex = (page-1) * PAGE_SIZE;
       rval.setQueries(queries);
       rval.setItemsPerPage(PAGE_SIZE);
       rval.setTotalResults(total_results);
       rval.setStartIndex(startIndex);
       return rval;

	}

	@Override
	public void delete(String uuid) throws DtgovUiException {
		// TODO Auto-generated method stub
	}

	@Override
	public WorkflowQueryBean get(String uuid) throws DtgovUiException {
		WorkflowQueryBean query1=new WorkflowQueryBean();
	    query1.setDescription("to be done1");
	    query1.setQuery("/s-ramp/ext/SwitchYardApplication");
	    query1.setUuid("0");
	    query1.setName("Name1");
	    query1.setWorkflow("overlord.demo.SimpleReleaseProcess");
	    for(int i=0;i<5;i++){
	    	WorkflowQueryProperty property=new WorkflowQueryProperty("Key"+i, "value"+i);
	    	query1.addWorkflowQueryProperty(property);
	    }
	    
	   return query1;
	}

}
