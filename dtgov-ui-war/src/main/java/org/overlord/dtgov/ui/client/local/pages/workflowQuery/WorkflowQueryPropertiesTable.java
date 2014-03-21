package org.overlord.dtgov.ui.client.local.pages.workflowQuery;

import javax.inject.Inject;

import org.jboss.errai.ui.nav.client.local.TransitionAnchorFactory;
import org.overlord.commons.gwt.client.local.widgets.SortableTemplatedWidgetTable;
import org.overlord.commons.gwt.client.local.widgets.SortableTemplatedWidgetTable.SortColumn;
import org.overlord.commons.gwt.client.local.widgets.TemplatedWidgetTable;
import org.overlord.dtgov.ui.client.local.ClientMessages;
import org.overlord.dtgov.ui.client.local.pages.WorkflowQueryPage;
import org.overlord.dtgov.ui.client.shared.beans.Constants;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQuerySummaryBean;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.InlineLabel;

public class WorkflowQueryPropertiesTable  extends TemplatedWidgetTable {
    @Inject
    protected ClientMessages i18n;

    /**
     * Constructor.
     */
    public WorkflowQueryPropertiesTable() {
    }





    /**
     * Adds a single row to the table.
     * @param deploymentSummaryBean
     */
    public void addRow(final WorkflowQuerySummaryBean workFlowQuerySummaryBean) {
        int rowIdx = this.rowElements.size();

        InlineLabel workflow = new InlineLabel(workFlowQuerySummaryBean.getWorkflow());
       

        //add(rowIdx, 0, query);
        //add(rowIdx, 1, workflow);
        //add(rowIdx, 2, initiatedOn);
    }

}
