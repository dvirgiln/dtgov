package org.overlord.dtgov.ui.client.local.pages;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageState;
import org.jboss.errai.ui.nav.client.local.TransitionAnchor;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.overlord.commons.gwt.client.local.widgets.HtmlSnippet;
import org.overlord.commons.gwt.client.local.widgets.UnorderedListPanel;
import org.overlord.dtgov.ui.client.local.ClientMessages;
import org.overlord.dtgov.ui.client.local.beans.UiConfiguration;
import org.overlord.dtgov.ui.client.local.pages.workflowQuery.WorkflowQueryPropertiesTable;
import org.overlord.dtgov.ui.client.local.pages.workflowQuery.WorkflowTypeListBox;
import org.overlord.dtgov.ui.client.local.services.ConfigurationService;
import org.overlord.dtgov.ui.client.local.services.NotificationService;
import org.overlord.dtgov.ui.client.local.services.WorkflowQueriesRpcService;
import org.overlord.dtgov.ui.client.local.services.rpc.IRpcServiceInvocationHandler;
import org.overlord.dtgov.ui.client.local.util.DOMUtil;
import org.overlord.dtgov.ui.client.shared.beans.NotificationBean;
import org.overlord.dtgov.ui.client.shared.beans.ValidationError;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryBean;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryProperty;
import org.overlord.dtgov.ui.client.shared.exceptions.DtgovFormValidationException;
import org.overlord.dtgov.ui.client.shared.exceptions.DtgovUiException;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkflowQueryPage.
 */
@Templated("/org/overlord/dtgov/ui/client/local/site/workflowQuery.html#page")
@Page(path = "workflowQuery")
@Dependent
public class WorkflowQueryPage extends AbstractPage {
    // Breadcrumbs
    /** The _back to dashboard. */
    @Inject
    @DataField("back-to-dashboard")
    private TransitionAnchor<DashboardPage> _backToDashboard;

    /** The _i18n. */
    @Inject
    private ClientMessages _i18n;

    // Breadcrumbs
    /** The _back to queries. */
    @Inject
    @DataField("back-to-admin-queries")
    private TransitionAnchor<WorkflowQueriesPage> _backToQueries;

    /** The _query box. */
    @Inject
    @DataField("form-workflow-query-input")
    private TextBox _queryBox;

    /** The _description box. */
    @Inject
    @DataField("form-workflow-description-input")
    private TextBox _descriptionBox;

    /** The _workflow. */
    @Inject
    @DataField("form-workflow-type-input")
    private WorkflowTypeListBox _workflow;

    /** The _query name box. */
    @Inject
    @DataField("form-workflow-query-name-input")
    private TextBox _queryNameBox;

    /** The _properties table. */
    @Inject
    @DataField("properties-table")
    private WorkflowQueryPropertiesTable _propertiesTable;

    /** The _uuid. */
    @PageState("uuid")
    private String _uuid;

    /** The _workflow query service. */
    @Inject
    private WorkflowQueriesRpcService _workflowQueryService;

    /** The _workflow query loading. */
    @Inject
    @DataField("workflow-query-loading-spinner")
    private HtmlSnippet _workflowQueryLoading;

    /** The _form validation error div. */
    @Inject
    @DataField("form-validation-div")
    private HtmlSnippet _formValidationErrorDiv;

    /** The _page content. */
    private Element _pageContent;

    /** The _notification service. */
    @Inject
    private NotificationService _notificationService;

    /** The _validation_errors. */
    @Inject
    @DataField("form-validation-errors")
    private UnorderedListPanel _validation_errors;

    /** The _submit button. */
    @Inject
    @DataField("btn-save")
    private Button _submitButton;

    /** The _add property. */
    @Inject
    @DataField("btn-add-property")
    private Button _addProperty;

    /** The _reset button. */
    @Inject
    @DataField("btn-reset")
    private Button _resetButton;

    /** The _config service. */
    @Inject
    private ConfigurationService _configService;

    /** The _notification. */
    private NotificationBean _notification;

    /**
     * On post construct.
     */
    @PostConstruct
    protected void onPostConstruct() {
        _formValidationErrorDiv.getElement().addClassName("hide");
        _pageContent = DOMUtil.findElementById(getElement(), "workflow-query-content-wrapper"); //$NON-NLS-1$

        UiConfiguration uiConfig = _configService.getUiConfig();

        this._workflow.clear();
        Map<String, String> workflowTypes = uiConfig.getWorkflowTypes();
        for (Entry<String, String> entry : workflowTypes.entrySet()) {
            this._workflow.addItem(entry.getKey(), entry.getValue());
        }

    }

    /**
     * Inits the.
     */
    private void init() {
        if (_uuid != null && !_uuid.isEmpty()) {
            _pageContent.addClassName("hide"); //$NON-NLS-1$
            _workflowQueryLoading.getElement().removeClassName("hide"); //$NON-NLS-1$

            _workflowQueryService.get(_uuid, new IRpcServiceInvocationHandler<WorkflowQueryBean>() {
                @Override
                public void onReturn(WorkflowQueryBean data) {
                    updateContent(data);
                    _workflowQueryLoading.getElement().addClassName("hide");
                }

                @Override
                public void onError(Throwable error) {
                    _notificationService.sendErrorNotification(
                            _i18n.format("deployments.error-loading"), error); //$NON-NLS-1$
                    _workflowQueryLoading.getElement().addClassName("hide");
                }
            });
        }
    }

    /* (non-Javadoc)
     * @see org.overlord.dtgov.ui.client.local.pages.AbstractPage#onPageShowing()
     */
    @Override
    protected void onPageShowing() {

        if (_uuid != null && !_uuid.isEmpty()) {
            init();
        }

    }

    /**
     * Update content.
     * 
     * @param data
     *            the data
     */
    protected void updateContent(WorkflowQueryBean data) {
        _pageContent.removeClassName("hide"); //$NON-NLS-1$

        _queryNameBox.setValue(data.getName());

        _queryBox.setValue(data.getQuery());

        _descriptionBox.setValue(data.getDescription());

        for (int i = 0; i < _workflow.getItemCount(); i++) {
            if (_workflow.getItemText(i).equals(data.getWorkflow())) {
                _workflow.setSelectedIndex(i);
                break;
            }
        }

        _propertiesTable.setValue(data.getProperties());

    }

    /**
     * On submit click.
     * 
     * @param event
     *            the event
     */
    @EventHandler("btn-save")
    public void onSubmitClick(ClickEvent event) {
        _notification = _notificationService.startProgressNotification(
                _i18n.format("workflowQuery-submit.save"), //$NON-NLS-1$
                _i18n.format("workflowQuery-submit.save-msg"));

        final WorkflowQueryBean query = this.createWorkflowQueryBean();

        _workflowQueryService.save(query, new IRpcServiceInvocationHandler<String>() {
            @Override
            public void onReturn(String data) {
                _notificationService.completeProgressNotification(_notification.getUuid(),
                        _i18n.format("workflowQuery-submit.successfully-saved"),
                        _i18n.format("workflowQuery-submit.successfully-saved-message", query.getName()));
                _backToQueries.click();
            }

            @Override
            public void onError(Throwable error) {
                if (error instanceof DtgovFormValidationException) {
                    List<ValidationError> errors = ((DtgovFormValidationException) error).getErrors();
                    // validation_errors.setItems(errors);
                    for (ValidationError err : errors) {
                        _validation_errors.add(new InlineLabel(_i18n.format(err.getErrorLabel())));
                    }

                    _formValidationErrorDiv.getElement().removeClassName("hide");
                    _notificationService.sendErrorNotification(
                            _i18n.format("workflowQuery-submit.error-saving"),
                            _i18n.format("workflowQuery-submit.error-saving-msg"), (DtgovUiException) error); //$NON-NLS-1$
                } else {
                    _notificationService.sendErrorNotification(
                            _i18n.format("workflowQuery-submit.error-saving"), error); //$NON-NLS-1$
                }

            }
        });
    }

    /**
     * Ond add property.
     * 
     * @param event
     *            the event
     */
    @EventHandler("btn-add-property")
    public void ondAddProperty(ClickEvent event) {
        _propertiesTable.addNewRow();
    }

    /**
     * Reset.
     * 
     * @param event
     *            the event
     */
    @EventHandler("btn-reset")
    public void reset(ClickEvent event) {
        _formValidationErrorDiv.getElement().addClassName("hide");
        if (_uuid != null && !_uuid.isEmpty()) {
            init();
        } else {
            _descriptionBox.setText("");
            _queryBox.setText("");
            _queryNameBox.setText("");
            _workflow.setSelectedIndex(0);
            _propertiesTable.clear();
        }

    }

    /**
     * Creates the workflow query bean.
     * 
     * @return the workflow query bean
     */
    private WorkflowQueryBean createWorkflowQueryBean() {
        WorkflowQueryBean query = new WorkflowQueryBean();
        query.setDescription(_descriptionBox.getValue());
        query.setName(_queryNameBox.getValue());
        query.setQuery(_queryBox.getValue());
        query.setUuid(_uuid);
        query.setWorkflow(_workflow.getValue());
        for (WorkflowQueryProperty property : _propertiesTable.getProperties()) {
            query.addWorkflowQueryProperty(property.getKey(), property.getValue());
        }
        return query;
    }

    /**
     * Gets the _back to dashboard.
     * 
     * @return the _back to dashboard
     */
    public TransitionAnchor<DashboardPage> get_backToDashboard() {
        return _backToDashboard;
    }

    /**
     * Sets the _back to dashboard.
     * 
     * @param _backToDashboard
     *            the new _back to dashboard
     */
    public void set_backToDashboard(TransitionAnchor<DashboardPage> _backToDashboard) {
        this._backToDashboard = _backToDashboard;
    }

    /**
     * Gets the i18n.
     * 
     * @return the i18n
     */
    public ClientMessages getI18n() {
        return _i18n;
    }

    /**
     * Sets the i18n.
     * 
     * @param i18n
     *            the new i18n
     */
    public void setI18n(ClientMessages i18n) {
        this._i18n = i18n;
    }

    /**
     * Gets the back to queries.
     * 
     * @return the back to queries
     */
    public TransitionAnchor<WorkflowQueriesPage> getBackToQueries() {
        return _backToQueries;
    }

    /**
     * Sets the back to queries.
     * 
     * @param backToQueries
     *            the new back to queries
     */
    public void setBackToQueries(TransitionAnchor<WorkflowQueriesPage> backToQueries) {
        this._backToQueries = backToQueries;
    }

    /**
     * Gets the query box.
     * 
     * @return the query box
     */
    public TextBox getQueryBox() {
        return _queryBox;
    }

    /**
     * Sets the query box.
     * 
     * @param queryBox
     *            the new query box
     */
    public void setQueryBox(TextBox queryBox) {
        this._queryBox = queryBox;
    }

    /**
     * Gets the description box.
     * 
     * @return the description box
     */
    public TextBox getDescriptionBox() {
        return _descriptionBox;
    }

    /**
     * Sets the description box.
     * 
     * @param descriptionBox
     *            the new description box
     */
    public void setDescriptionBox(TextBox descriptionBox) {
        this._descriptionBox = descriptionBox;
    }

    /**
     * Gets the workflow.
     * 
     * @return the workflow
     */
    public WorkflowTypeListBox getWorkflow() {
        return _workflow;
    }

    /**
     * Sets the workflow.
     * 
     * @param workflow
     *            the new workflow
     */
    public void setWorkflow(WorkflowTypeListBox workflow) {
        this._workflow = workflow;
    }

    /**
     * Gets the query name box.
     * 
     * @return the query name box
     */
    public TextBox getQueryNameBox() {
        return _queryNameBox;
    }

    /**
     * Sets the query name box.
     * 
     * @param queryNameBox
     *            the new query name box
     */
    public void setQueryNameBox(TextBox queryNameBox) {
        this._queryNameBox = queryNameBox;
    }

    /**
     * Gets the properties table.
     * 
     * @return the properties table
     */
    public WorkflowQueryPropertiesTable getPropertiesTable() {
        return _propertiesTable;
    }

    /**
     * Sets the properties table.
     * 
     * @param propertiesTable
     *            the new properties table
     */
    public void setPropertiesTable(WorkflowQueryPropertiesTable propertiesTable) {
        this._propertiesTable = propertiesTable;
    }

    /**
     * Gets the uuid.
     * 
     * @return the uuid
     */
    public String getUuid() {
        return _uuid;
    }

    /**
     * Sets the uuid.
     * 
     * @param uuid
     *            the new uuid
     */
    public void setUuid(String uuid) {
        this._uuid = uuid;
    }

    /**
     * Gets the workflow query service.
     * 
     * @return the workflow query service
     */
    public WorkflowQueriesRpcService getWorkflowQueryService() {
        return _workflowQueryService;
    }

    
    /**
     * Sets the _workflow query service.
     * 
     * @param workflowQueryService
     *            the new _workflow query service
     */
    public void set_workflowQueryService(WorkflowQueriesRpcService workflowQueryService) {
        this._workflowQueryService = workflowQueryService;
    }

    /**
     * Gets the workflow query loading.
     * 
     * @return the workflow query loading
     */
    public HtmlSnippet getWorkflowQueryLoading() {
        return _workflowQueryLoading;
    }

    /**
     * Sets the workflow query loading.
     * 
     * @param workflowQueryLoading
     *            the new workflow query loading
     */
    public void setWorkflowQueryLoading(HtmlSnippet workflowQueryLoading) {
        this._workflowQueryLoading = workflowQueryLoading;
    }

    /**
     * Gets the form validation error div.
     * 
     * @return the form validation error div
     */
    public HtmlSnippet getFormValidationErrorDiv() {
        return _formValidationErrorDiv;
    }

    /**
     * Sets the form validation error div.
     * 
     * @param formValidationErrorDiv
     *            the new form validation error div
     */
    public void setFormValidationErrorDiv(HtmlSnippet formValidationErrorDiv) {
        this._formValidationErrorDiv = formValidationErrorDiv;
    }

    /**
     * Gets the page content.
     * 
     * @return the page content
     */
    public Element getPageContent() {
        return _pageContent;
    }

    /**
     * Sets the page content.
     * 
     * @param pageContent
     *            the new page content
     */
    public void setPageContent(Element pageContent) {
        this._pageContent = pageContent;
    }

    /**
     * Gets the notification service.
     * 
     * @return the notification service
     */
    public NotificationService getNotificationService() {
        return _notificationService;
    }

    /**
     * Sets the notification service.
     * 
     * @param notificationService
     *            the new notification service
     */
    public void setNotificationService(NotificationService notificationService) {
        this._notificationService = notificationService;
    }

    /**
     * Gets the validation_errors.
     * 
     * @return the validation_errors
     */
    public UnorderedListPanel getValidation_errors() {
        return _validation_errors;
    }

    /**
     * Sets the validation_errors.
     * 
     * @param validation_errors
     *            the new validation_errors
     */
    public void setValidation_errors(UnorderedListPanel validation_errors) {
        this._validation_errors = validation_errors;
    }

    /**
     * Gets the submit button.
     * 
     * @return the submit button
     */
    public Button getSubmitButton() {
        return _submitButton;
    }

    /**
     * Sets the submit button.
     * 
     * @param submitButton
     *            the new submit button
     */
    public void setSubmitButton(Button submitButton) {
        this._submitButton = submitButton;
    }

    /**
     * Gets the adds the property.
     * 
     * @return the adds the property
     */
    public Button getAddProperty() {
        return _addProperty;
    }

    /**
     * Sets the adds the property.
     * 
     * @param addProperty
     *            the new adds the property
     */
    public void setAddProperty(Button addProperty) {
        this._addProperty = addProperty;
    }

    /**
     * Gets the reset button.
     * 
     * @return the reset button
     */
    public Button getResetButton() {
        return _resetButton;
    }

    /**
     * Sets the reset button.
     * 
     * @param resetButton
     *            the new reset button
     */
    public void setResetButton(Button resetButton) {
        this._resetButton = resetButton;
    }

    /**
     * Gets the config service.
     * 
     * @return the config service
     */
    public ConfigurationService getConfigService() {
        return _configService;
    }

    /**
     * Sets the config service.
     * 
     * @param configService
     *            the new config service
     */
    public void setConfigService(ConfigurationService configService) {
        this._configService = configService;
    }

    /**
     * Gets the notification.
     * 
     * @return the notification
     */
    public NotificationBean getNotification() {
        return _notification;
    }

    /**
     * Sets the notification.
     * 
     * @param notification
     *            the new notification
     */
    public void setNotification(NotificationBean notification) {
        this._notification = notification;
    }



}
