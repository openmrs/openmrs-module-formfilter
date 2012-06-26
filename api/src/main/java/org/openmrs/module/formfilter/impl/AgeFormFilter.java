package org.openmrs.module.formfilter.impl;

import java.lang.reflect.Field;

import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.formfilter.FormFilterHandler;


public class AgeFormFilter implements FormFilterHandler {
	
	public AgeFormFilter()
	{
		
	}
	
	public AgeFormFilter(String properties){
		for (String string : properties.split("&")) {
	        String str[]=string.split("=");
	        try {
	            Field field=this.getClass().getDeclaredField(str[0]);
	            field.set(this, (Object)str[1]);
            }catch (Exception e) {        

            }
		}
		System.out.println(getMinimumAge()+"\t"+getMaximumAge());

	}
	
	private int minimumAge;
	
	private int maximumAge;
	
		
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
	
    @Override
    public boolean shouldDisplayForm(Patient p, User u) {
	    // TODO Auto-generated method stub
    	System.out.println(p.getAge() +"\t"+minimumAge+"\t"+maximumAge);
	    if((p.getAge()>=getMinimumAge()) && (p.getAge()<=getMaximumAge()))
	    {
	    	return true;
	    }
		
	    return false;
    }

}
