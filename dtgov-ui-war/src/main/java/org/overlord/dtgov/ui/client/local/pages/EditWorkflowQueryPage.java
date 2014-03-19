package org.overlord.dtgov.ui.client.local.pages;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.TransitionAnchor;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated("/org/overlord/dtgov/ui/client/local/site/editQuery.html#page")
@Page(path="editWorkflowQuery")
@Dependent
public class EditWorkflowQueryPage extends AbstractPage {
	// Breadcrumbs
    @Inject @DataField("back-to-dashboard")
    TransitionAnchor<DashboardPage> backToDashboard;
}
