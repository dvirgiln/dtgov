package org.overlord.dtgov.ui.client.local.pages.workflowQuery;

/*
 * Copyright 2013 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.ui.nav.client.local.TransitionAnchorFactory;
import org.overlord.commons.gwt.client.local.widgets.SortableTemplatedWidgetTable;
import org.overlord.dtgov.ui.client.local.ClientMessages;
import org.overlord.dtgov.ui.client.local.pages.DeploymentDetailsPage;
import org.overlord.dtgov.ui.client.local.pages.EditWorkflowQueryPage;
import org.overlord.dtgov.ui.client.shared.beans.Constants;
import org.overlord.dtgov.ui.client.shared.beans.DeploymentSummaryBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQuerySummaryBean;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.InlineLabel;

/**
 * A table of workflow queries.
 *
 * @author dvirgiln@redhat.com
 */
@Dependent
public class WorkflowQueryTable extends SortableTemplatedWidgetTable {

    @Inject
    protected ClientMessages i18n;
    @Inject
    protected TransitionAnchorFactory<EditWorkflowQueryPage> editQueryLinkFactory;

    /**
     * Constructor.
     */
    public WorkflowQueryTable() {
    }

    /**
     * @see org.overlord.sramp.ui.client.local.widgets.common.SortableTemplatedWidgetTable#getDefaultSortColumn()
     */
    @Override
    public SortColumn getDefaultSortColumn() {
        SortColumn sortColumn = new SortColumn();
        sortColumn.columnId = Constants.SORT_COLID_WORKFLOW;
        sortColumn.ascending = false;
        return sortColumn;
    }

    /**
     * @see org.overlord.monitoring.ui.client.local.widgets.common.SortableTemplatedWidgetTable#configureColumnSorting()
     */
    @Override
    protected void configureColumnSorting() {
        setColumnSortable(0, Constants.SORT_COLID_QUERY);
        setColumnSortable(2, Constants.SORT_COLID_WORKFLOW);
        sortBy(Constants.SORT_COLID_WORKFLOW, false);
    }

    /**
     * Adds a single row to the table.
     * @param deploymentSummaryBean
     */
    public void addRow(final WorkflowQuerySummaryBean workFlowQuerySummaryBean) {
        int rowIdx = this.rowElements.size();

        //Anchor name = editQueryLinkFactory.get("uuid", deploymentSummaryBean.getUuid()); //$NON-NLS-1$
        Anchor query = editQueryLinkFactory.get(); //$NON-NLS-1$
        query.setText(workFlowQuerySummaryBean.getQuery());
        InlineLabel workflow = new InlineLabel(workFlowQuerySummaryBean.getWorkflow());
       

        add(rowIdx, 0, query);
        add(rowIdx, 1, workflow);
        //add(rowIdx, 2, initiatedOn);
    }

}
