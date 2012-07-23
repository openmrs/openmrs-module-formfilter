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
 * This test validates GenderFormFilter class.
 * 
 * @author Goutham.Vasireddi
 */
public class GenderFormFilterTest {
	
	/**
	 * Testing the condition to show a form
	 */
	@Test
	public void shouldDisplayForm() {
		GenderFormFilter genderFormFilter = new GenderFormFilter("gender=M");
		
		Patient patient = new Patient();
		patient.setGender("M");
		
		Assert.assertTrue("Patient gender matches with filter gender.",
		    genderFormFilter.shouldDisplayForm(patient, new User()));
		
	}
	
	/**
	 * Testing the condition to not show a form.
	 */
	@Test
	public void shouldNotDisplayForm() {
		GenderFormFilter genderFormFilter = new GenderFormFilter("gender=F");
		
		Patient patient = new Patient();
		patient.setGender("M");
		
		Assert.assertFalse("Patient gender does not matches with filter gender.",
		    genderFormFilter.shouldDisplayForm(patient, new User()));
	}
	
}
