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

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.test.Verifies;

/**
 * This test validates AgeFormFilter class.
 */
public class AgeFormFilterTest {
	
	/**
	 * Testing the condition to show a form
	 * 
	 * @see {@link AgeFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value="display form when patient age lies between filter minimum and maximum age values",method="shouldDisplayForm(Patient, User)")
	public void shouldDisplayForm_shouldDisplayFormWhenPatientAgeLiesBetweenFilterMinimumAndMaximumAgeValues() {
		AgeFormFilter ageFormFilter = new AgeFormFilter("minimumAge=10&maximumAge=30");
		
		Patient patient = new Patient();
		patient.setBirthdateFromAge(20, new Date());
		
		Assert.assertTrue("Patient age should lie between specified minimum age and maximum age.",
		    ageFormFilter.shouldDisplayForm(patient, new User()));
		
	}
	
	/**
	 * Testing the condition to not show a form.
	 * 
	 * @see {@link AgeFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value="should not display form when patient age does not lie between filter minimum and maximum age values",method="shouldDisplayForm(Patient,User)")
	public void shouldDisplayForm_shouldNotDisplayFormWhenPatientAgeDoesNotLieBetweenFilterMinimumAndMaximumAgeValues() {
		AgeFormFilter ageFormFilter = new AgeFormFilter("minimumAge=10&maximumAge=30");
		Patient patient = new Patient();
		patient.setBirthdateFromAge(2, new Date());
		Assert.assertFalse("Patient age does not lie between specified minimum age and maximum age.",
		    ageFormFilter.shouldDisplayForm(patient, new User()));
	}
	
}
