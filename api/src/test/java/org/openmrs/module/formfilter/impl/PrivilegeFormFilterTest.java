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
import org.openmrs.Privilege;
import org.openmrs.Role;
import org.openmrs.User;

/**
 * This test validates PrivilegeFormFilter class.
 */
public class PrivilegeFormFilterTest {
	
	/**
	 * Testing the condition to show a form
	 */
	@Test
	public void shouldDisplayForm() {
		PrivilegeFormFilter privilegeFormFilter = new PrivilegeFormFilter("privilege=Manage Concept Classes");
		
		User user = new User();
		Role role = new Role("Anonymous");
		role.addPrivilege(new Privilege("Manage Concept Classes"));
		user.addRole(role);
		
		Assert.assertTrue("Assigned filter privilege  does not match users privileges.",
		    privilegeFormFilter.shouldDisplayForm(new Patient(), user));
		
	}
	
	/**
	 * Testing the condition to not show a form.
	 */
	@Test
	public void shouldNotDisplayForm() {
		PrivilegeFormFilter privilegeFormFilter = new PrivilegeFormFilter("privilege=Manage Concept Classes");
		Assert.assertFalse("Assigned filter privilege matches even user has no privileges",
		    privilegeFormFilter.shouldDisplayForm(new Patient(), new User()));
	}
	
}
