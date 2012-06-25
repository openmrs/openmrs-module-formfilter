<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>
<%-- <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%-- <%@ taglibprefix="c" uri="http://java.sun.com/jstl/core"%> --%>

<script type="text/javascript">
	function validate_form() {
		validate = true;
		if (document.formfilter_form.filterName.value == "") {
			document.getElementById("filterName_error").innerHTML = "<span class='error'>Cannot be empty</span>";
			validate = false;
		} else {
			document.getElementById("filterName_error").innerHTML = "";
		}

		if (document.formfilter_form.filterDescription.value == "") {
			document.getElementById("filterDescription_error").innerHTML = "<span class='error'>Cannot be empty</span>";
			validate = false;
		} else {
			document.getElementById("filterDescription_error").innerHTML = "";
		}

		if (document.formfilter_form.propertyType.value == "Select") {
			document.getElementById("propertyType_error").innerHTML = "<span class='error'>Select a option</span>";
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
					document.getElementById("gender_error").innerHTML = "<span class='error'>Choose a option</span>";
					validate = false;
				} else {
					document.getElementById("gender_error").innerHTML = "";
				}
			}

			if (document.formfilter_form.propertyType.value == "AgeProperty") {
				
				if (!document.formfilter_form.maximumAge.value.match("^[0-9]+") || !document.formfilter_form.minimumAge.value.match("^[0-9]+")) {
					document.getElementById("age_error").innerHTML = "<span class='error'>Cannot be empty and only numbers allowed</span>";
					validate = false;
				} else {

					if (document.formfilter_form.minimumAge.value <= document.formfilter_form.maximumAge.value) {
						document.getElementById("age_error").innerHTML = "";
					} else {
						document.getElementById("age_error").innerHTML = "<span class='error'>Minimum age should be less than Maximum age value</span>";
						validate = false;

					}
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
			<td>Name</td>
			<td> 
				<input type="text" name="filterName" value="${formFilterProperty.filterName}" />
				<span id="filterName_error" ></span>			
			</td>			
		</tr>
		<tr>
			<td>Description</td>
			<td>
				<textarea name="filterDescription" >${formFilterProperty.filterDescription}</textarea>
				<span id="filterDescription_error" ></span>
			</td>
		</tr>
			
		<tr>
			<td>Select Property Type</td>
			<td>
				<select name="propertyType" id="propertyType" >
					<option  >Select</option> 
					<option value="AgeProperty">Age</option>
					<option value="GenderProperty">Gender</option>
				</select>
				<span id="propertyType_error" ></span>
			</td>
		</tr>
		
 
		<tr id="AgeProperty" >
		  <td>Age Range</td>
		  <td>
		  <table>
		  	<tr><td>Minimum Age:</td><td><input type="text" name="minimumAge" value="${properties.minimumAge}" /></td></tr>
		  	<tr><td>Maximum Age:</td><td><input type="text" name="maximumAge" value="${properties.maximumAge}" /></td></tr>
		  </table>
		  <div id="age_error" ></div>
		  </td>	  
		</tr>
		

		<tr id="GenderProperty" >
			<td>Gender</td>
			<td>
			Male<input type="radio" name="gender" value="M" <c:if test="${properties.gender == 'M'}">checked="true"</c:if>   />
			Female<input type="radio" name="gender" value="F" <c:if test="${properties.gender == 'F'}">checked="true"</c:if> />
			Others<input type="radio" name="gender" value="U" <c:if test="${properties.gender == 'U'}">checked="true"</c:if> />
			<span id="gender_error"  ></span>  
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
		

	});

	//When filter property type is changed it hides the previous and shows selected one. 
	$j('#propertyType').change(function() {
		$j('#' + temp).hide();
		temp = $j('#propertyType').val();
		$j('#' + temp).show();
	});
</script>
	




<%@ include file="/WEB-INF/template/footer.jsp"%>