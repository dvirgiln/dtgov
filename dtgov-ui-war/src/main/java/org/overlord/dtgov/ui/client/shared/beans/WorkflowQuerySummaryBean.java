package org.overlord.dtgov.ui.client.shared.beans;

import java.io.Serializable;
import java.util.Date;

import org.jboss.errai.common.client.api.annotations.Portable;
@Portable
public class WorkflowQuerySummaryBean implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7284042552981203699L;
	private String uuid;
    private String description;
    private String query;
    private String workflow;
    
    public WorkflowQuerySummaryBean(){
    	
    }
	public String getUuid() {
		return uuid;
	}
	public WorkflowQuerySummaryBean setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public WorkflowQuerySummaryBean setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getQuery() {
		return query;
	}
	public WorkflowQuerySummaryBean setQuery(String query) {
		this.query = query;
		return this;
	}
	public String getWorkflow() {
		return workflow;
	}
	public WorkflowQuerySummaryBean setWorkflow(String workflow) {
		this.workflow = workflow;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result
				+ ((workflow == null) ? 0 : workflow.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkflowQuerySummaryBean other = (WorkflowQuerySummaryBean) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (workflow == null) {
			if (other.workflow != null)
				return false;
		} else if (!workflow.equals(other.workflow))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "WorkflowQuerySummaryBean [uuid=" + uuid + ", description="
				+ description + ", query=" + query + ", workflow=" + workflow
				+ "]";
	}
    
    
}
