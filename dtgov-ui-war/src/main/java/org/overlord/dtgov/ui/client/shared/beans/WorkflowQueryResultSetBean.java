package org.overlord.dtgov.ui.client.shared.beans;

import java.io.Serializable;
import java.util.List;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Models the set of Workflow Query  summary objects.
 *
 * @author dvirgiln@redhat.com
 */
@Portable
public class WorkflowQueryResultSetBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4302100421983844252L;

	private List<WorkflowQuerySummaryBean> queries;
    private long totalResults;
    private int itemsPerPage;
    private int startIndex;
    
    public WorkflowQueryResultSetBean(){
    	
    }

	public List<WorkflowQuerySummaryBean> getQueries() {
		return queries;
	}

	public void setQueries(List<WorkflowQuerySummaryBean> queries) {
		this.queries = queries;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


    
    
}
