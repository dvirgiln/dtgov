package org.overlord.dtgov.ui.client.shared.beans;

import java.io.Serializable;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * @author David Virgil Naranjo
 *
 */
@Portable
public class ValidationError implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2473229547611141961L;

	private String errorLabel;
	
	public ValidationError(){}
	
	public ValidationError(String errorLabel){
		this.errorLabel=errorLabel;
	}

	public String getErrorLabel() {
		return errorLabel;
	}
	
	
	
}
