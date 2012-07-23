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
package org.openmrs.module.formfilter.impl;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.Patient;
import org.openmrs.User;

/**
 * This test validates DateFormFilter class.
 * @author Goutham.Vasireddi
 */
public class DateFormFilterTest {

	/**
	 * Testing the condition to show a form
	 */
	@Test
	public void shouldDisplayForm()
	{
		DateFormFilter dateFormFilter=new DateFormFilter("date=01/01/2000&show=after");
				
		Assert.assertTrue("Todays date should be after filter date.",dateFormFilter.shouldDisplayForm(new Patient(), new User()));			
	
	}
	
	/**
	 * Testing the condition to not show a form.
	 */
	@Test
	public void shouldNotDisplayForm()
	{
		DateFormFilter dateFormFilter=new DateFormFilter("date=01/01/2000&show=before");
		
		Assert.assertFalse("Todays date is after the filter date.",dateFormFilter.shouldDisplayForm(new Patient(), new User()));			

	}


	
}
