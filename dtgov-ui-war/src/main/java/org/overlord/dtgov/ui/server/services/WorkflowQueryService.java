package org.overlord.dtgov.ui.server.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.errai.bus.server.annotations.Service;
import org.overlord.dtgov.ui.client.shared.beans.DeploymentResultSetBean;
import org.overlord.dtgov.ui.client.shared.beans.DeploymentSummaryBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueriesFilterBean;
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

    private static final int PAGE_SIZE = 20;
    
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
       WorkflowQuerySummaryBean query1=new WorkflowQuerySummaryBean();
       query1.setDescription("to be done1");
       query1.setQuery("/s-ramp/ext/SwitchYardApplication");
       query1.setUuid("0");
       query1.setName("Name1");
       query1.setWorkflow("overlord.demo.SimpleReleaseProcess");
       queries.add(query1);
       
       WorkflowQuerySummaryBean query2=new WorkflowQuerySummaryBean();
       query2.setDescription("to be done2");
       query2.setQuery("/s-ramp/ext/SwitchYardApplication");
       query2.setUuid("2");
       query2.setName("Name2");
       query2.setWorkflow("overlord.demo.SimpleReleaseProcess");
       queries.add(query2);
       
       WorkflowQuerySummaryBean query3=new WorkflowQuerySummaryBean();
       query3.setDescription("to be done3");
       query3.setQuery("/s-ramp/ext/SwitchYardApplication3");
       query3.setUuid("3");
       query3.setName("Name3");
       query3.setWorkflow("overlord.demo.SimpleReleaseProcess");
       queries.add(query3);
       
       WorkflowQuerySummaryBean query4=new WorkflowQuerySummaryBean();
       query4.setDescription("to be done4");
       query4.setQuery("/s-ramp/ext/SwitchYardApplication4");
       query4.setUuid("4");
       query4.setName("Name4");
       query4.setWorkflow("overlord.demo.SimpleReleaseProcess");
       queries.add(query4);
       int startIndex = (page-1) * PAGE_SIZE;
       rval.setQueries(queries);
       rval.setItemsPerPage(PAGE_SIZE);
       rval.setTotalResults(4);
       rval.setStartIndex(startIndex);
       return rval;

	}

	@Override
	public void delete(String uuid) throws DtgovUiException {
		// TODO Auto-generated method stub
	}

}
