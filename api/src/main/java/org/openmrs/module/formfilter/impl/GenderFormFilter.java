package org.openmrs.module.formfilter.impl;

import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.formfilter.FormFilterInterface;


public class GenderFormFilter implements FormFilterInterface {

	@Override
    public boolean shouldDisplayForm(Patient p, User u) {
	    // TODO Auto-generated method stub
	    return false;
    }

}
