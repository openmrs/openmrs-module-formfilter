package org.openmrs.module.formfilter.impl;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.formfilter.FormFilterHandler;


public class GenderFormFilter implements FormFilterHandler {

	protected Log log = LogFactory.getLog(getClass());
	
	public GenderFormFilter(){
		
	}
	
	public GenderFormFilter(String properties){
		
		for (String string : properties.split("&")) {
	        String str[]=string.split("=");
	        try {
	            Field field=this.getClass().getDeclaredField(str[0]);
	            field.set(this, (Object)str[1]);
            }catch (Exception e) {        
            	log.info(e); 

            }
		}

		
	}
	
	private String gender;
	
	
    public String getGender() {
    	return gender;
    }

	
    public void setGender(String gender) {
    	this.gender = gender;
    }






    @Override
    public boolean shouldDisplayForm(Patient p, User u) {
	    // TODO Auto-generated method stub
		if(getGender().equalsIgnoreCase("U") && p.getGender()== null)
			return true;
		
		if (p.getGender().equalsIgnoreCase(gender))
	        return true;
        
	    return false;
    }

}
