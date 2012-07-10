<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>
<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
<script type="text/javascript">

	/*Javascript to validate form*/
	function validate_form() {
		validate = true;
		if (document.formfilter_form.filterName.value == "") {
			document.getElementById("filterName_error").innerHTML = "<span class='error'><spring:message code='formfilter.cannotBeEmpty' /></span>";
			validate = false;
		} else {
			document.getElementById("filterName_error").innerHTML = "";
		}		

		if (document.formfilter_form.propertyType.value == "Select") {
			document.getElementById("propertyType_error").innerHTML = "<span class='error'><spring:message code='formfilter.selectAOption' /></span>";
			validate = false;
		} else {
			document.getElementById("propertyType_error").innerHTML = "";

			if (document.formfilter_form.propertyType.value == "GenderProperty") {
				var genderValue="";
				 var radioButtons = document.getElementsByName("gender");
				      for (var x = 0; x < radioButtons.length; x ++) {
				         if (radioButtons[x].checked) {
				           genderValue=radioButtons[x].value;
				         }
				      }
				
				if (genderValue == "") {
					document.getElementById("gender_error").innerHTML = "<span class='error'><spring:message code='formfilter.chooseAOption' /></span>";
					validate = false;
				} else {
					document.getElementById("gender_error").innerHTML = "";
				}
			}

			if (document.formfilter_form.propertyType.value == "AgeProperty") {
				
				if (!document.formfilter_form.maximumAge.value.match("^[0-9]+") || !document.formfilter_form.minimumAge.value.match("^[0-9]+")) {
					document.getElementById("age_error").innerHTML = "<span class='error'><spring:message code='formfilter.cannotBeEmptyAndOnlyNumbers' /></span>";
					validate = false;
				} else {

					if (document.formfilter_form.minimumAge.value <= document.formfilter_form.maximumAge.value) {
						document.getElementById("age_error").innerHTML = "";
					} else {
						document.getElementById("age_error").innerHTML = "<span class='error'><spring:message code='formfilter.minimumAgeLessThanMaximumAge' /></span>";
						validate = false;

					}
				}
			}
			
			if (document.formfilter_form.propertyType.value == "DateProperty") {
				
				if (!document.formfilter_form.date.value.match("^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$")) {
					document.getElementById("date_error").innerHTML = "<span class='error'><spring:message code='formfilter.wrongDateFormat' /></span>";
					validate = false;
				} else {
					document.getElementById("date_error").innerHTML = "";
					var showValue="";
					 var radioButtons = document.getElementsByName("show");
					      for (var x = 0; x < radioButtons.length; x ++) {
					         if (radioButtons[x].checked) {
					           showValue=radioButtons[x].value;
					         }
					      }
					
					if (showValue == "") {
						document.getElementById("show_error").innerHTML = "<span class='error'><spring:message code='formfilter.chooseAOption' /></span>";
						validate = false;
					} else {
						document.getElementById("show_error").innerHTML = "";
					}
				
				
				}
			}
			
			
			if (document.formfilter_form.propertyType.value == "RoleProperty") {
				
				if (document.formfilter_form.role.value == "") {
					document.getElementById("role_error").innerHTML = "<span class='error'><spring:message code='formfilter.selectAOption' /></span>";
					validate = false;
				} else {
					document.getElementById("role_error").innerHTML = "";
				}
			}
			
			if (document.formfilter_form.propertyType.value == "PrivilegeProperty") {
				
				if (document.formfilter_form.privilege.value == "") {
					document.getElementById("privilege_error").innerHTML = "<span class='error'><spring:message code='formfilter.selectAOption' /></span>";
					validate = false;
				} else {
					document.getElementById("privilege_error").innerHTML = "";
				}
			}
			
			if (document.formfilter_form.propertyType.value == "CohortProperty") {
				
				if (document.formfilter_form.cohort.value == "") {
					document.getElementById("cohort_error").innerHTML = "<span class='error'><spring:message code='formfilter.selectAOption' /></span>";
					validate = false;
				} else {
					document.getElementById("cohort_error").innerHTML = "";
				}
			}
			
			
			
		}

		return validate;
	}
</script>



<h2>
<spring:message code="formfilter.formFilter" />
</h2>


<form method="POST" name="formfilter_form" id="formfilter_form" onsubmit="return validate_form();">
	<table>
		<tr>
			<td>${formFilter.form.name}			
			<input type="hidden" name="formFilterId" value="${formFilter.formFilterId}" />
			<input type="hidden" name="formFilterPropertyId" value="${formFilterProperty.formFilterPropertyId}" />
			</td>
		</tr>

		<tr>
			<td><spring:message code="formfilter.name" /></td>
			<td> 
				<input type="text" name="filterName" value="${formFilterProperty.filterName}" />
				<span id="filterName_error" ></span>			
			</td>			
		</tr>
		<tr>
			<td><spring:message code="formfilter.description" /></td>
			<td>
				<textarea name="filterDescription" >${formFilterProperty.filterDescription}</textarea>
				
			</td>
		</tr>
			
		<tr>
			<td><spring:message code="formfilter.selectFilterType" /></td>
			<td>
				<select name="propertyType" id="propertyType" >
					<option value="Select" ><spring:message code="formfilter.select" /></option> 
					<option value="AgeProperty"><spring:message code="formfilter.age" /></option>
					<option value="GenderProperty"><spring:message code="formfilter.gender" /></option>
					<option value="DateProperty"><spring:message code="formfilter.date" /></option>
					<option value="RoleProperty"><spring:message code="formfilter.role" /></option>
					<option value="PrivilegeProperty"><spring:message code="formfilter.privilege" /></option>
					<option value="CohortProperty"><spring:message code="formfilter.cohort" /></option>
				</select>
				<span id="propertyType_error" ></span>
			</td>
		</tr>
		
 
		<tr id="AgeProperty" >
		  <td><spring:message code="formfilter.ageRange" /></td>
		  <td>
		  <table>
		  	<tr><td><spring:message code="formfilter.minimumAge" /></td><td><input type="text" name="minimumAge" value="${properties.minimumAge}" /></td></tr>
		  	<tr><td><spring:message code="formfilter.maximumAge" /></td><td><input type="text" name="maximumAge" value="${properties.maximumAge}" /></td></tr>
		  </table>
		  <div id="age_error" ></div>
		  </td>	  
		</tr>
		

		<tr id="GenderProperty" >
			<td><spring:message code="formfilter.gender" /></td>
			<td>
			<input type="radio" name="gender" value="M" <c:if test="${properties.gender == 'M'}">checked="true"</c:if> /><spring:message code="formfilter.male" />
			<input type="radio" name="gender" value="F" <c:if test="${properties.gender == 'F'}">checked="true"</c:if> /><spring:message code="formfilter.female" />
			<input type="radio" name="gender" value="U" <c:if test="${properties.gender == 'U'}">checked="true"</c:if> /><spring:message code="formfilter.unknown" />
			<span id="gender_error"  ></span>  
			</td>
		</tr>
		
		<tr id="DateProperty">
			<td><spring:message code="formfilter.select" /></td>
			<td>
				<table>
					<tr>
						<td><spring:message code="formfilter.date" /></td>
						<td>:</td>
						<td>
							<input type="text" name="date" size="10" value="${properties.date}" onClick="showCalendar(this)" id="date" />(<spring:message code="general.format"/>: <openmrs:datePattern />)
							<span id="date_error"  ></span>				
						</td>
					</tr>
					<tr>
						<td><spring:message code="formfilter.show" /></td>
						<td>:</td>
						<td>
						<input type="radio" id="before" value="before" name="show"  <c:if test="${properties.show == 'before'}">checked="true"</c:if> /><spring:message code="formfilter.before" />
						<input type="radio" id="after" value="after" name="show" <c:if test="${properties.show == 'after'}">checked="true"</c:if> /><spring:message code="formfilter.after" />						
						</td>
					</tr>
				</table>
				<div id="show_error"></div>
				</td>
		</tr>
		
		<tr id="RoleProperty">
		  <td><spring:message code="formfilter.role" /></td>
		  <td>
		  <select id="role" name="role" >
		     <option> </option>
		     <c:forEach var="item" items="${roles}">
		       <option value="${item.role}" <c:if test="${properties.role == item.role}"> selected="selected"</c:if>  >${item.role}</option>
		     </c:forEach>
		  </select>
		  <span id="role_error"  ></span>		  
		  </td>
		</tr>
		
		<tr id="PrivilegeProperty" >
		  <td><spring:message code="formfilter.privilege" /></td>
		  <td>
		  <select id="privilege" name="privilege" >
		  <option></option>
		     <c:forEach var="item" items="${privileges}">
		       <option value="${item.privilege}" <c:if test="${properties.privilege == item.privilege}"> selected="selected"</c:if>  >${item.privilege}</option>
		     </c:forEach>
		  </select>
		  <span id="privilege_error"  ></span>		  
		  </td>
		</tr>
		
		<tr id="CohortProperty" >
		  <td><spring:message code="formfilter.cohort" /></td>
		  <td>
		  <select id="cohort" name="cohort" >
		  <option></option>
		     <c:forEach var="item" items="${cohorts}">
		       <option value="${item.cohortId}" <c:if test="${properties.cohortId == item.cohortId}"> selected="selected"</c:if>  >${item.name}</option>
		     </c:forEach>
		  </select>
		  <span id="cohort_error"  ></span>		  
		  </td>
		</tr>
		
		
		<tr>
			<td><input type="submit"  value="Save" /></td>
			<td><input type="button"  value="Cancel" onclick="location.href='viewformfilter.form?formFilterId=${formFilter.formFilterId}'" /></td>
		</tr>


	</table>
	
</form>	
<script>
    /*This script holds the functionality of showing properties as user select from dropdown list#propertyType*/
    
    //Stores current visible row id.
    var temp;
    
    //When page loads all the Filter properties are hidden.
    $j(document).ready(function() {
    	
    	
		<c:choose>
			<c:when test="${empty properties.minimumAge && empty properties.maximumAge }">
				$j('#AgeProperty').hide();
			</c:when>
			<c:otherwise>
			  temp='AgeProperty';
			  $j('#propertyType').val("AgeProperty");
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${empty properties.gender }" >
				$j('#GenderProperty').hide();
			</c:when>
			<c:otherwise>
		  		temp='AgeProperty';
		  		$j('#propertyType').val("GenderProperty");
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${empty properties.date}" >
				$j('#DateProperty').hide();
			</c:when>
			<c:otherwise>
		  		temp='DateProperty';
		  		$j('#propertyType').val("DateProperty");
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${empty properties.role}" >
				$j('#RoleProperty').hide();
			</c:when>
			<c:otherwise>
		  		temp='RoleProperty';
		  		$j('#propertyType').val("RoleProperty");
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${empty properties.privilege}" >
				$j('#PrivilegeProperty').hide();
			</c:when>
			<c:otherwise>
		  		temp='PrivilegeProperty';
		  		$j('#propertyType').val("PrivilegeProperty");
			</c:otherwise>
		</c:choose>
		
		<c:choose>
		<c:when test="${empty properties.cohortId}" >
			$j('#CohortProperty').hide();
		</c:when>
		<c:otherwise>
	  		temp='CohortProperty';
	  		$j('#propertyType').val("CohortProperty");
		</c:otherwise>
		</c:choose>

	});

	//When filter property type is changed it hides the previous and shows selected one. 
	$j('#propertyType').change(function() {
		$j('#' + temp).hide();
		temp = $j('#propertyType').val();
		$j('#' + temp).show();
	});
</script>
	




<%@ include file="/WEB-INF/template/footer.jsp"%>