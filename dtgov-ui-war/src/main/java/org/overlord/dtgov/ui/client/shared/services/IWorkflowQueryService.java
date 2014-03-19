package org.overlord.dtgov.ui.client.shared.services;

import org.jboss.errai.bus.server.annotations.Remote;
import org.overlord.dtgov.ui.client.shared.beans.TaskInboxFilterBean;
import org.overlord.dtgov.ui.client.shared.beans.TaskInboxResultSetBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueriesFilterBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryResultSetBean;
import org.overlord.dtgov.ui.client.shared.exceptions.DtgovUiException;

/**
 * Provides a way to manage workflow queries.
 *
 * @author dvirgiln@redhat.com
 */
@Remote
public interface IWorkflowQueryService {

    /**
     * Search for tasks using the given filters and search text.
     * @param filters
     * @param page
     * @param sortColumnId
     * @param sortAscending
     * @throws DtgovUiException
     */
    public WorkflowQueryResultSetBean search(WorkflowQueriesFilterBean filters, int page, String sortColumnId,
            boolean sortAscending) throws DtgovUiException;

}
