<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<h2>
<spring:message code="formfilter.addFormProperty" />
</h2>

<form method="post" >
	<table>
		<tr>
			<td>${form.name}</td>
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
		
		<!-- 
		<tr id="AgeProperty" >
		  <td></td>
		  <td>Minimum Age:<input type="text" name="minimumAge" maxlength="2"" /><br>Maximum Age:<input type="text" name="maximumAge" maxlength="2" /></td>
		</tr>
		 -->
		<tr id="AgeProperty" >
		<td>Minimum Age</td><td><input type="text" name="minimumAge" maxlength="3" width="3" /></td>
		</tr>
		
		<tr id="AgeProperty" >
		<td>Maximum Age<td><td><input type="text" name="maximumAge" maxlength="3" width="3" /></td>
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