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
package org.overlord.dtgov.ui.client.local.beans;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;

/**
 * Bean that reads and holds UI configuration.  This bean reads its information from a global JavaScript
 * variable
 * @author eric.wittmann@redhat.com
 */
public class UiConfiguration {

    private Map<String, String> deploymentTypes = new LinkedHashMap<String, String>();
    private Map<String, String> deploymentStages = new LinkedHashMap<String, String>();

    /**
     * Constructor.
     */
    public UiConfiguration() {
        read();
    }

    /**
     * @return the deploymentTypes
     */
    public Map<String, String> getDeploymentTypes() {
        return deploymentTypes;
    }

    /**
     * @return the deploymentStages
     */
    public Map<String, String> getDeploymentStages() {
        return deploymentStages;
    }

    /**
     * Read the configuration information from the OVERLORD_DTGOVUI_CONFIG javascript variable.
     */
    private final native void read() /*-{
        var dis = this;
        try {
            var deploymentConfig = $wnd.OVERLORD_DTGOVUI_CONFIG.deployments;
            // Read the deployment types
            var dTypes = deploymentConfig.types;
            for (var k in dTypes) {
                if (dTypes.hasOwnProperty(k)) {
                    var label = k;
                    var type = dTypes[k];
                    dis.@org.overlord.dtgov.ui.client.local.beans.UiConfiguration::addDeploymentType(Ljava/lang/String;Ljava/lang/String;)(label, type);
                }
            }

            // Read the deployment stages
            var dStages = deploymentConfig.stages;
            for (var k in dStages) {
                if (dStages.hasOwnProperty(k)) {
                    var label = k;
                    var classifier = dStages[k];
                    dis.@org.overlord.dtgov.ui.client.local.beans.UiConfiguration::addDeploymentStage(Ljava/lang/String;Ljava/lang/String;)(label, classifier);
                }
            }
        } catch (e) {
            // TODO do something interesting here?
        }
    }-*/;

    /**
     * Adds a single deployment type to the map.
     * @param label
     * @param type
     */
    private void addDeploymentType(String label, String type) {
        this.getDeploymentTypes().put(label, type);
        GWT.log("[UiConfig] - Registered Deployment Type: " + label + "=" + type);
    }

    /**
     * Adds a single deployment stage to the map.
     * @param label
     * @param classifier
     */
    private void addDeploymentStage(String label, String classifier) {
        this.getDeploymentStages().put(label, classifier);
        GWT.log("[UiConfig] - Registered Deployment Stage: " + label + "=" + classifier);
    }

}