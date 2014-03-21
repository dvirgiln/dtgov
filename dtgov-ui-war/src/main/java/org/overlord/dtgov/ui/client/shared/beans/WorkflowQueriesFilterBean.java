package org.overlord.dtgov.ui.client.shared.beans;

import java.io.Serializable;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class WorkflowQueriesFilterBean implements Serializable{
	private String workflow;
	
	private String name;

	public WorkflowQueriesFilterBean(){
		
	}
	
	
	public WorkflowQueriesFilterBean(String workflow,String name) {
		super();
		this.workflow = workflow;
		this.name=name;
	}

	

	public String getName() {
		return name;
	}


	public WorkflowQueriesFilterBean setName(String name) {
		this.name = name;
		return this;
	}


	public String getWorkflow() {
		return workflow;
	}

	public WorkflowQueriesFilterBean setWorkflow(String workflow) {
		this.workflow = workflow;
		return this;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		WorkflowQueriesFilterBean other = (WorkflowQueriesFilterBean) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return "WorkflowQueriesFilterBean [workflow=" + workflow + ", name="
				+ name + "]";
	}
	

	
	
	
	
}
