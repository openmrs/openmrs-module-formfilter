<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<h2>
<spring:message code="formfilter.addFormProperty" />
</h2>

<form method="POST" >
	<table>
		<tr>
			<td>${formFilter.form.name}
			<input type="hidden" name="formFilterId" value="${formFilter.formFilterId}" /></td>
		</tr>

		<tr>
			<td>Name</td>
			<td><input type="text" name="filterName"></td>
		</tr>
		<tr>
			<td>Description</td>
			<td><textarea name="filterDescription"></textarea>
			</td>
		</tr>
			
		<tr>
			<td>Select Property Type</td>
			<td>
				<select name="propertyType" id="propertyType" > 
					<option value="AgeProperty">Age</option>
					<option value="GenderProperty">Gender</option>
				</select>
			</td>
		</tr>
		
 
		<tr id="AgeProperty" >
		  <td>Age Range</td>
		  <td>
		  <table>
		  	<tr><td>Minimum Age:</td><td><input type="text" name="minimumAge" /></td></tr>
		  	<tr><td>Maximum Age:</td><td><input type="text" name="maximumAge" /></td></tr>
		  </table>	  
		</tr>
		

		<tr id="GenderProperty" >
			<td>Gender</td>
			<td><input type="radio" name="gender" value="M" />Male<input type="radio" name="gender" value="F" />Female  </td>
		</tr>
		
		<tr>
			<td><input type="submit"  value="Save" /></td>
			<td><input type="button"  value="Cancel"  /></td>
		</tr>


	</table>
	
	
<script>
    var temp='AgeProperty';
    $j(document).ready(function() {
    	
        $j('#GenderProperty').hide();
    });
   
    $j('#propertyType').change(function(){
       $j('#'+temp).hide();
         temp=$j('#propertyType').val();
        $j('#'+temp).show();
    });



</script>
	

</form>


<%@ include file="/WEB-INF/template/footer.jsp"%>