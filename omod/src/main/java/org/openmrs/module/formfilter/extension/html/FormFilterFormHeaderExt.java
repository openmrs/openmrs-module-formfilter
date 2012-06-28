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
package org.openmrs.module.formfilter.extension.html;

import java.util.Map;
import java.util.TreeMap;

import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.module.Extension;



/**
 * Adds Form Filter link to the form schema design page.
 */
public class FormFilterFormHeaderExt extends Extension {

	private String formId;
	
	/**
     * @see org.openmrs.module.Extension#getMediaType()
     */
    @Override
    public MEDIA_TYPE getMediaType() {
	    // TODO Auto-generated method stub
	    return Extension.MEDIA_TYPE.html;
    }
    
    @Override
	public void initialize(Map<String, String> parameters) {
		formId = parameters.get("formId");
	}
	
public Map<String, String> getLinks() {
		
		Map<String, String> map = new TreeMap<String, String>();
		
		if (formId != null && formId.trim().length() > 0) {
			
			map.put("module/formfilter/viewformfilter.form?formId="+formId, "formfilter.filterForm");
		
		}
		
		return map;
	}
	

}
