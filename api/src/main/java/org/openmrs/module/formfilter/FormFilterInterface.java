package org.openmrs.module.formfilter;

import org.openmrs.Patient;
import org.openmrs.User;


public interface FormFilterInterface {
	
	public boolean shouldDisplayForm(Patient p, User u);
	

}
