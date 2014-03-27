package org.overlord.dtgov.ui.client.shared.exceptions;

import java.util.List;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.overlord.dtgov.ui.client.shared.beans.ValidationError;

/**
 * @author David Virgil Naranjo
 *
 */

@Portable
public class DtgovFormValidationException extends DtgovUiException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6434832250991091319L;
	
	private List<ValidationError> errors;

	public List<ValidationError> getErrors() {
		return errors;
	}


	public DtgovFormValidationException() {
		super();
	}
	
	public DtgovFormValidationException(List<ValidationError> errors) {
		super();
		this.errors=errors;
	}

	public DtgovFormValidationException(String message, Throwable cause,List<ValidationError> errors) {
		super(message, cause);
		this.errors=errors;
	}

	public DtgovFormValidationException(String message,List<ValidationError> errors) {
		super(message);
		this.errors=errors;
	}

	public DtgovFormValidationException(Throwable cause,List<ValidationError> errors) {
		super(cause);
		this.errors=errors;
	}


	
	

}
