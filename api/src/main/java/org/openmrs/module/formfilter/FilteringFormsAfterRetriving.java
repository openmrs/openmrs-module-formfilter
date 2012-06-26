/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.formfilter;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

/**
 *
 */
public class FilteringFormsAfterRetriving implements AfterReturningAdvice {

	private Log log = LogFactory.getLog(this.getClass());
	
	/**
     * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    @Override
    public void afterReturning(Object object, Method method, Object[] args, Object target) throws Throwable {
	    // TODO Auto-generated method stub
    	
    	if (method.getName().equals("getFormsModuleCanEnter"))
    	{
    	  log.debug("After getFormsModuleCanEnter");	
    	}
           
	    
    }

}
