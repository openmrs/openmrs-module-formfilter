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
import org.openmrs.test.Verifies;

/**
 * This test validates GenderFormFilter class.
 */
public class GenderFormFilterTest {
	
	/**
	 * Testing the condition to show a form
	 * 
	 * @see {@link GenderFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value = "should display form when filter gender match with patient gender", method = "shouldDisplayForm(Patient, User)")
	public void shouldDisplayForm_shouldDisplayFormWhenFilterGenderMatchWithPatientGender() {
		GenderFormFilter genderFormFilter = new GenderFormFilter("gender=M");
		
		Patient patient = new Patient();
		patient.setGender("M");
		
		Assert.assertTrue("Patient gender matches with filter gender.",
		    genderFormFilter.shouldDisplayForm(patient, new User()));
		
	}
	
	/**
	 * Testing the condition to not show a form.
	 * 
	 * @see {@link GenderFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value = "should not display form when filter gender does not match with patient gender", method = "shouldDisplayForm(Patient, User)")
	public void shouldDisplayForm_shouldNotDisplayFormWhenFilterGenderDoesNotMatchWithPatientGender() {
		GenderFormFilter genderFormFilter = new GenderFormFilter("gender=F");
		
		Patient patient = new Patient();
		patient.setGender("M");
		
		Assert.assertFalse("Patient gender does not matches with filter gender.",
		    genderFormFilter.shouldDisplayForm(patient, new User()));
	}
	
}
