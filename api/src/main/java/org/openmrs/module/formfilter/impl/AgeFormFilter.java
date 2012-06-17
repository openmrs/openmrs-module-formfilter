package org.openmrs.module.formfilter.impl;

import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.formfilter.FormFilterInterface;


public class AgeFormFilter implements FormFilterInterface {
	
	
	private int minimumAge;
	
	private int maximumAge;
	
	@Override
    public boolean shouldDisplayForm(Patient p, User u) {
	    // TODO Auto-generated method stub
	    return false;
    }

	
    public int getMinimumAge() {
    	return minimumAge;
    }

	
    public void setMinimumAge(int minimumAge) {
    	this.minimumAge = minimumAge;
    }

	
    public int getMaximumAge() {
    	return maximumAge;
    }

	
    public void setMaximumAge(int maximumAge) {
    	this.maximumAge = maximumAge;
    }
	
	

}
