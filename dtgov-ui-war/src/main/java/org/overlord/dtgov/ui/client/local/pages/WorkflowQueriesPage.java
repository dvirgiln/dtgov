package org.overlord.dtgov.ui.client.local.pages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageShown;
import org.jboss.errai.ui.nav.client.local.TransitionAnchor;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.overlord.commons.gwt.client.local.events.TableSortEvent;
import org.overlord.commons.gwt.client.local.widgets.HtmlSnippet;
import org.overlord.commons.gwt.client.local.widgets.Pager;
import org.overlord.commons.gwt.client.local.widgets.SortableTemplatedWidgetTable.SortColumn;
import org.overlord.dtgov.ui.client.local.ClientMessages;
import org.overlord.dtgov.ui.client.local.pages.deployments.AddDeploymentDialog;
import org.overlord.dtgov.ui.client.local.pages.workflowQuery.DeleteWorkflowQueryDialog;
import org.overlord.dtgov.ui.client.local.pages.workflowQuery.WorkflowQueriesFilter;
import org.overlord.dtgov.ui.client.local.pages.workflowQuery.WorkflowQueryTable;
import org.overlord.dtgov.ui.client.local.services.ApplicationStateKeys;
import org.overlord.dtgov.ui.client.local.services.ApplicationStateService;
import org.overlord.dtgov.ui.client.local.services.NotificationService;
import org.overlord.dtgov.ui.client.local.services.WorkflowQueriesRpcService;
import org.overlord.dtgov.ui.client.local.services.rpc.IRpcServiceInvocationHandler;
import org.overlord.dtgov.ui.client.shared.beans.DeploymentsFilterBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueriesFilterBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryResultSetBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQuerySummaryBean;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;


@Templated("/org/overlord/dtgov/ui/client/local/site/workflowQueries.html#page")
@Page(path="workflowQueries")
@Dependent
public class WorkflowQueriesPage extends AbstractPage {

    @Inject
    protected ClientMessages i18n;
	
    @Inject
    protected WorkflowQueriesRpcService workflowQueryService;
    
    @Inject
    protected NotificationService notificationService;
    
    @Inject
    protected ApplicationStateService stateService;
    
	// Breadcrumbs
    @Inject @DataField("back-to-dashboard")
    TransitionAnchor<DashboardPage> backToDashboard;

    
    
    @Inject @DataField("queries-filter-sidebar")
    protected WorkflowQueriesFilter filtersPanel;
    
    @Inject @DataField("queries-none")
    protected HtmlSnippet noDataMessage;
    @Inject @DataField("queries-searching")
    protected HtmlSnippet searchInProgressMessage;
    
    @Inject @DataField("queries-table")
    protected WorkflowQueryTable workflowQueryTable;
    
    @Inject @DataField("queries-pager")
    protected Pager pager;
    
    @DataField("queries-range")
    protected SpanElement rangeSpan = Document.get().createSpanElement();
    @DataField("queries-total")
    protected SpanElement totalSpan = Document.get().createSpanElement();

    private int currentPage = 1;
    
    @Inject @DataField("btn-refresh")
    protected Button refreshButton;
    
    @Inject @DataField("btn-add")
    protected Button addButton;
    
    @Inject
    protected Instance<DeleteWorkflowQueryDialog> deleteWorkflowQueryDialog;
    /**
     * Constructor.
     */
    public WorkflowQueriesPage() {
    }
    
    /**
     * Called after construction.
     */
    @PostConstruct
    protected void postConstruct() {
        filtersPanel.addValueChangeHandler(new ValueChangeHandler<WorkflowQueriesFilterBean>() {
            @Override
            public void onValueChange(ValueChangeEvent<WorkflowQueriesFilterBean> event) {
                doSearch();
            }
        });
       
        pager.addValueChangeHandler(new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange(ValueChangeEvent<Integer> event) {
                doSearch(event.getValue());
            }
        });
        workflowQueryTable.addTableSortHandler(new TableSortEvent.Handler() {
            @Override
            public void onTableSort(TableSortEvent event) {
                doSearch(currentPage);
            }
        });

        // Hide column 1 when in mobile mode.
        //deploymentsTable.setColumnClasses(1, "desktop-only"); //$NON-NLS-1$

        this.rangeSpan.setInnerText("?"); //$NON-NLS-1$
        this.totalSpan.setInnerText("?"); //$NON-NLS-1$
        workflowQueryTable.setDeleteWorkflowQueryDialog(deleteWorkflowQueryDialog);
    }
    
    /**
     * Event handler that fires when the user clicks the refresh button.
     * @param event
     */
    @EventHandler("btn-refresh")
    public void onRefreshClick(ClickEvent event) {
        doSearch(currentPage);
    }
    
    /**
     * Search for artifacts based on the current filter settings and search text.
     */
    protected void doSearch() {
        doSearch(1);
    }

    /**
     * Search for deployments based on the current filter settings.
     * @param page
     */
    protected void doSearch(int page) {
        onSearchStarting();
        currentPage = page;

        final WorkflowQueriesFilterBean filterBean = filtersPanel.getValue();
        final SortColumn currentSortColumn = this.workflowQueryTable.getCurrentSortColumn();
        
        stateService.put(ApplicationStateKeys.WORKFLOW_QUERIES_FILTER, filterBean);
        stateService.put(ApplicationStateKeys.WORKFLOW_QUERIES_PAGE, currentPage);
        stateService.put(ApplicationStateKeys.WORKFLOW_QUERIES_SORT_COLUMN, currentSortColumn);

        workflowQueryService.search(filterBean, page, currentSortColumn.columnId, currentSortColumn.ascending,
                new IRpcServiceInvocationHandler<WorkflowQueryResultSetBean>() {
            @Override
            public void onReturn(WorkflowQueryResultSetBean data) {
                updateTable(data);
                updatePager(data);
            }
            @Override
            public void onError(Throwable error) {
                notificationService.sendErrorNotification(i18n.format("deployments.error-loading"), error); //$NON-NLS-1$
                noDataMessage.setVisible(true);
                searchInProgressMessage.setVisible(false);
            }
        });
    }

    /**
     * Called when a new search is kicked off.
     */
    protected void onSearchStarting() {
        this.pager.setVisible(false);
        this.searchInProgressMessage.setVisible(true);
        this.workflowQueryTable.setVisible(false);
        this.noDataMessage.setVisible(false);
        this.rangeSpan.setInnerText("?"); //$NON-NLS-1$
        this.totalSpan.setInnerText("?"); //$NON-NLS-1$
    }


    /**
     * Updates the table of deployments with the given data.
     * @param data
     */
    protected void updateTable(WorkflowQueryResultSetBean data) {
        this.workflowQueryTable.clear();
        this.searchInProgressMessage.setVisible(false);
        if (data.getQueries().size() > 0) {
            for (WorkflowQuerySummaryBean deploymentSummaryBean : data.getQueries()) {
                this.workflowQueryTable.addRow(deploymentSummaryBean);
            }
            this.workflowQueryTable.setVisible(true);
        } else {
            this.noDataMessage.setVisible(true);
        }
    }

    /**
     * Updates the pager with the given data.
     * @param data
     */
    protected void updatePager(WorkflowQueryResultSetBean data) {
        int numPages = ((int) (data.getTotalResults() / data.getItemsPerPage())) + (data.getTotalResults() % data.getItemsPerPage() == 0 ? 0 : 1);
        int thisPage = (data.getStartIndex() / data.getItemsPerPage()) + 1;
        this.pager.setNumPages(numPages);
        this.pager.setPage(thisPage);
        if (numPages > 1)
            this.pager.setVisible(true);

        int startIndex = data.getStartIndex() + 1;
        int endIndex = startIndex + data.getQueries().size() - 1;
        String rangeText = "" + startIndex + "-" + endIndex; //$NON-NLS-1$ //$NON-NLS-2$
        String totalText = String.valueOf(data.getTotalResults());
        this.rangeSpan.setInnerText(rangeText);
        this.totalSpan.setInnerText(totalText);
    }
    
    /**
     * Kick off a search at this point so that we show some data in the UI.
     * @see org.overlord.dtgov.ui.client.local.pages.AbstractPage#onPageShowing()
     */
    @Override
    protected void onPageShowing() {
        // Refresh the filters
        filtersPanel.refresh();

        WorkflowQueriesFilterBean filterBean = (WorkflowQueriesFilterBean) stateService.get(ApplicationStateKeys.WORKFLOW_QUERIES_FILTER, new WorkflowQueriesFilterBean());
        //String searchText = (String) stateService.get(ApplicationStateKeys.DEPLOYMENTS_SEARCH_TEXT, ""); //$NON-NLS-1$
        Integer page = (Integer) stateService.get(ApplicationStateKeys.WORKFLOW_QUERIES_PAGE, 1);
        SortColumn sortColumn = (SortColumn) stateService.get(ApplicationStateKeys.WORKFLOW_QUERIES_SORT_COLUMN, this.workflowQueryTable.getDefaultSortColumn());

        this.filtersPanel.setValue(filterBean);
        this.workflowQueryTable.sortBy(sortColumn.columnId, sortColumn.ascending);
        
        // Kick off a search
        doSearch(page);
    }
    
    /**
     * Called whenver the page is shown.
     */
    @PageShown
    public void onPageShown() {
    	//doSearch();
    }

}
