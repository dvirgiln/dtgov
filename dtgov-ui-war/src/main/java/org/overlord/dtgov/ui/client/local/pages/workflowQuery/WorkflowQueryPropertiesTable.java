/*
 * @author David Virgil Naranjo
 */
package org.overlord.dtgov.ui.client.local.pages.workflowQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.overlord.commons.gwt.client.local.widgets.TemplatedWidgetTable;
import org.overlord.dtgov.ui.client.local.ClientMessages;
import org.overlord.dtgov.ui.client.local.beans.UiConfiguration;
import org.overlord.dtgov.ui.client.local.services.ConfigurationService;
import org.overlord.dtgov.ui.client.shared.beans.WorkflowQueryProperty;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestionEvent;
import com.google.gwt.user.client.ui.SuggestionHandler;
import com.google.gwt.user.client.ui.TextBox;

// TODO: Auto-generated Javadoc
/**
 * Widget used to create the properties table used in the workflow query page,
 * as part of the form.
 * 
 * @author David Virgil Naranjo
 */
@Dependent
public class WorkflowQueryPropertiesTable extends TemplatedWidgetTable implements
        HasValue<List<WorkflowQueryProperty>> {

    /** The i18n. */
    @Inject
    private ClientMessages i18n;

    /** The config service. */
    @Inject
    private ConfigurationService configService;

    /** The key default values. */
    private MultiWordSuggestOracle keyDefaultValues;

    // private Map<String,String> properties;
    /** The properties. */
    private List<WorkflowQueryProperty> properties;

    /** The property key types. */
    private Map<String, String> propertyKeyTypes;

    /**
     * Constructor.
     */
    public WorkflowQueryPropertiesTable() {
        keyDefaultValues = new MultiWordSuggestOracle();
        properties = new ArrayList<WorkflowQueryProperty>();
    }

    /**
     * Post construct.
     */
    @PostConstruct
    protected void postConstruct() {

        UiConfiguration uiConfig = configService.getUiConfig();

        propertyKeyTypes = uiConfig.getWorkflowPropertyKeyTypes();
        for (String entry : propertyKeyTypes.keySet()) {
            keyDefaultValues.add(entry);
        }
    }

    /**
     * Adds the row.
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public void addRow(String key, String value) {
        int rowIdx = this.rowElements.size();

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.event.logical.shared.HasValueChangeHandlers#
     * addValueChangeHandler
     * (com.google.gwt.event.logical.shared.ValueChangeHandler)
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<List<WorkflowQueryProperty>> handler) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.HasValue#getValue()
     */
    @Override
    public List<WorkflowQueryProperty> getValue() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object)
     */
    @Override
    public void setValue(List<WorkflowQueryProperty> value) {
        this.setValue(value, true);

    }

    /**
     * Adds the row.
     * 
     * @param property
     *            the property
     */
    private void addRow(final WorkflowQueryProperty property) {
        final int rowIdx = this.rowElements.size();
        String propValue = property.getValue();
        FlowPanel actions = new FlowPanel();
        InlineLabel deleteAction = new InlineLabel();
        deleteAction.setStyleName("workflow-icon", true);
        deleteAction.setStyleName("workflow-delete-icon", true);
        actions.add(deleteAction);

        deleteAction.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                deleteRow(rowIdx);
                properties.remove(rowIdx);
                setValue(properties);
            }
        });
        final TextBox valueBox = new TextBox();
        valueBox.setText(propValue);
        valueBox.setStyleName("input-value");
        valueBox.addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                String val = event.getValue();
                /*
                 * Map<String, String> newValue = new LinkedHashMap<String,
                 * String>(WorkflowQueryPropertiesTable.this.properties); if
                 * (val == null) { newValue.remove(propName);
                 * 
                 * } else { newValue.put(propName, val); } setValue(newValue,
                 * true);
                 */
                property.setValue(val);
            }
        });

        SuggestBox propertyKey = new SuggestBox(keyDefaultValues);

        propertyKey.setStyleName("input-value");
        propertyKey.setText(property.getKey());
        propertyKey.addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                String val = event.getValue();
                /*
                 * List<WorkflowQueryProperty> newValue = new
                 * ArrayList<WorkflowQueryProperty
                 * >(WorkflowQueryPropertiesTable.this.properties); String
                 * oldValue=newValue.get(propName); newValue.remove(propName);
                 * if (val != null) { newValue.put(val, oldValue);
                 * 
                 * } setValue(newValue, true);
                 */

                property.setKey(val);

            }
        });
        propertyKey.addEventHandler(new SuggestionHandler() {

            @Override
            public void onSuggestionSelected(SuggestionEvent event) {
                String val = event.getSelectedSuggestion().getReplacementString();
                for (String propertyKey : propertyKeyTypes.keySet()) {
                    if (propertyKey.equals(val)) {
                        valueBox.setText(propertyKeyTypes.get(propertyKey));
                        property.setValue(propertyKeyTypes.get(propertyKey));
                    }
                }

                property.setKey(val);

            }
        });

        add(rowIdx, 0, propertyKey);
        add(rowIdx, 1, valueBox);
        Element row = add(rowIdx, 2, actions);
        setStyleName(row, "actions", true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object,
     * boolean)
     */
    @Override
    public void setValue(List<WorkflowQueryProperty> props, boolean fireEvents) {
        this.properties = new ArrayList<WorkflowQueryProperty>(props);
        clear();
        if (props == null || props.isEmpty()) {
            // Put something here? "No Properties found..." ?
        } else {
            for (final WorkflowQueryProperty property : props) {
                addRow(property);
            }
        }

    }

    /**
     * Adds the new row.
     */
    public void addNewRow() {
        WorkflowQueryProperty property = new WorkflowQueryProperty();
        properties.add(property);
        addRow(property);
    }

    /**
     * Gets the properties.
     * 
     * @return the properties
     */
    public List<WorkflowQueryProperty> getProperties() {
        return properties;
    }

    /**
     * Sets the properties.
     * 
     * @param properties
     *            the new properties
     */
    public void setProperties(List<WorkflowQueryProperty> properties) {
        this.properties = properties;
    }

    /**
     * Gets the i18n.
     * 
     * @return the i18n
     */
    public ClientMessages getI18n() {
        return i18n;
    }

    /**
     * Sets the i18n.
     * 
     * @param i18n
     *            the new i18n
     */
    public void setI18n(ClientMessages i18n) {
        this.i18n = i18n;
    }

    /**
     * Gets the config service.
     * 
     * @return the config service
     */
    public ConfigurationService getConfigService() {
        return configService;
    }

    /**
     * Sets the config service.
     * 
     * @param configService
     *            the new config service
     */
    public void setConfigService(ConfigurationService configService) {
        this.configService = configService;
    }

    /**
     * Gets the key default values.
     * 
     * @return the key default values
     */
    public MultiWordSuggestOracle getKeyDefaultValues() {
        return keyDefaultValues;
    }

    /**
     * Sets the key default values.
     * 
     * @param keyDefaultValues
     *            the new key default values
     */
    public void setKeyDefaultValues(MultiWordSuggestOracle keyDefaultValues) {
        this.keyDefaultValues = keyDefaultValues;
    }

    /**
     * Gets the property key types.
     * 
     * @return the property key types
     */
    public Map<String, String> getPropertyKeyTypes() {
        return propertyKeyTypes;
    }

    /**
     * Sets the property key types.
     * 
     * @param propertyKeyTypes
     *            the property key types
     */
    public void setPropertyKeyTypes(Map<String, String> propertyKeyTypes) {
        this.propertyKeyTypes = propertyKeyTypes;
    }

}
