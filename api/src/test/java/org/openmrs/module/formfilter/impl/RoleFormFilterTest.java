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
import org.openmrs.Role;
import org.openmrs.User;
import org.openmrs.test.Verifies;

/**
 * This test validates RoleFormFilter class.
 */
public class RoleFormFilterTest {
	
	/**
	 * Testing the condition to show a form
	 * 
	 * @see {@link RoleFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value = "should display form when user has mentioned role", method = "shouldDisplayForm(Patient, User)")
	public void shouldDisplayForm_shouldDisplayFormWhenUserHasMentionedRole() {
		RoleFormFilter roleFormFilter = new RoleFormFilter("role=Anonymous");
		User user = new User();
		user.addRole(new Role("Anonymous"));
		Assert.assertTrue("Filter role  does not match user roles.", roleFormFilter.shouldDisplayForm(new Patient(), user));
		
	}
	
	/**
	 * Testing the condition to not show a form.
	 * 
	 * @see {@link RoleFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value = "should not display form when user does not have mentioned role.", method = "shouldDisplayForm(Patient, User)")
	public void shouldDisplayForm_shouldNotDisplayFormWhenUserDoesNotHaveMentionedRole() {
		RoleFormFilter roleFormFilter = new RoleFormFilter("role=Anonymous");
		Assert.assertFalse("Filter role matches even user has no roles",
		    roleFormFilter.shouldDisplayForm(new Patient(), new User()));
	}
	
}
