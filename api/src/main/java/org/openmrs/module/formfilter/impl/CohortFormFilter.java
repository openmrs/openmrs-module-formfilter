package org.openmrs.module.formfilter.impl;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Cohort;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.CohortService;
import org.openmrs.api.context.Context;
import org.openmrs.module.formfilter.FormFilterHandler;


public class CohortFormFilter implements FormFilterHandler {

	protected Log log = LogFactory.getLog(getClass());
	
	/**
	 * Default Constructor.
	 */
	public CohortFormFilter(){
		
	}
	
	/**
	 * Constructor sets class field values.
	 * @param properties ,string property from FormFilterProperty class in key=value based format 
	 * Example: cohortId=1  
	 */
	public CohortFormFilter(String properties){
		for (String string : properties.split("&")) {
	        String str[]=string.split("=");
	        try {
	            Field field=this.getClass().getDeclaredField(str[0]);
	            field.set(this, (Object)Integer.parseInt(str[1]));
            }catch (Exception e) {
            	log.info(e);
            }
		}	

	}
	
	private int cohortId;
	
		
    public int getCohortId() {
    	return cohortId;
    }

	
    public void setCohortId(int cohortId) {
    	this.cohortId = cohortId;
    }

	@Override
    public boolean shouldDisplayForm(Patient p, User u) {
	    // TODO Auto-generated method stub
		CohortService cohortService=Context.getCohortService();
		Cohort cohort=cohortService.getCohort(cohortId);
		if(cohort.contains(p))
		{
			return true;
		}		
	    return false;
    }

}
