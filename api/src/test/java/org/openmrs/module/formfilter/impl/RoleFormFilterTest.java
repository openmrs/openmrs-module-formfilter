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
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.Verifies;

/**
 * This test validates RoleFormFilter class.
 */
public class RoleFormFilterTest extends BaseModuleContextSensitiveTest {
	
	/**
	 * Testing the condition to show a form when useRoleInheritanceComparison=yes
	 * 
	 * @throws Exception
	 * @see {@link RoleFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value = "should display form when user has any mentioned role and when useRoleInheritanceComparison value is Yes", method = "shouldDisplayForm(Patient, User)")
	public void shouldDisplayForm_shouldDisplayFormWhenUserHasAnyMentionedRoleAndWhenUseRoleInheritanceComparisonValueIsNo() throws Exception {
		authenticate();
		executeDataSet("formFilterTestDataSyncCreateTest.xml");
		RoleFormFilter roleFormFilter = new RoleFormFilter("roles=Provider,");
		User user = new User();
		user.addRole(Context.getUserService().getRole("Anonymous"));
		Assert.assertTrue(roleFormFilter.shouldDisplayForm(new Patient(), user));
		
	}
	
	/**
	 * Testing the condition to not show a form when useRoleInheritanceComparison=yes
	 * 
	 * @throws Exception
	 * @see {@link RoleFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value = "should not display form when user does not have any mentioned role and when useRoleInheritanceComparison value is Yes", method = "shouldDisplayForm(Patient, User)")
	public void shouldDisplayForm_shouldNotDisplayFormWhenUserDoesNotHaveAnyMentionedRoleAndWhenUseRoleInheritanceComparisonValueIsYes() throws Exception {
		authenticate();
		executeDataSet("formFilterTestDataSyncCreateTest.xml");
		RoleFormFilter roleFormFilter = new RoleFormFilter("roles=Provider,");
		Assert.assertFalse(roleFormFilter.shouldDisplayForm(new Patient(), new User()));
	}
	
	/**
	 * Testing the condition to not show a form when useRoleInheritanceComparison=no
	 * 
	 * @throws Exception
	 * @see {@link RoleFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value = "should not display form when user does not have specified mentioned role and when useRoleInheritanceComparison value is No", method = "shouldDisplayForm(Patient, User)")
	public void shouldDisplayForm_shouldNotDisplayFormWhenUserDoesNotHaveSpecifiedMentionedRoleAndWhenUseRoleInheritanceComparisonValueIsNo() throws Exception {
		authenticate();
		executeDataSet("roleTestDataSyncCreateTest.xml");
		RoleFormFilter roleFormFilter = new RoleFormFilter("roles=Anonymous,");
		User user = new User();
		user.addRole(Context.getUserService().getRole("Provider"));
		Assert.assertFalse(roleFormFilter.shouldDisplayForm(new Patient(), user));
	}
	
	/**
	 * Testing the condition to show a form when useRoleInheritanceComparison=no
	 * 
	 * @throws Exception
	 * @see {@link RoleFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value = "should display form when user has specified mentioned role and when useRoleInheritanceComparison value is No", method = "shouldDisplayForm(Patient, User)")
	public void shouldDisplayForm_shouldDisplayFormWhenUserHasSpecifiedMentionedRoleAndWhenUseRoleInheritanceComparisonValueIsNo() throws Exception {
		authenticate();
		executeDataSet("roleTestDataSyncCreateTest.xml");
		RoleFormFilter roleFormFilter = new RoleFormFilter("roles=Anonymous,");
		User user = new User();
		user.addRole(Context.getUserService().getRole("Anonymous"));
		Assert.assertTrue(roleFormFilter.shouldDisplayForm(new Patient(), user));
		
	}
	
}
