<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<openmrs:htmlInclude file="/moduleResources/formfilter/jquery-ui-1.8.2.custom.css" />
<openmrs:htmlInclude file="/moduleResources/formfilter/jquery-1.4.2.min.js" />
<openmrs:htmlInclude file="/moduleResources/formfilter/jquery-ui-1.8.2.custom.min.js" />
<openmrs:htmlInclude file="/moduleResources/formfilter/jquery.easy-confirm-dialog.js" />
<openmrs:hasPrivilege privilege="Manage Forms">


<script type="text/javascript">
$j = jQuery.noConflict();
$j(document).ready(function() {
	$j(".delete").easyconfirm({locale: { title: '<spring:message code="formfilter.deletingFilter" />',text: '<spring:message code="formfilter.areYouSure" />' ,button: ['<spring:message code="formfilter.no" />','<spring:message code="formfilter.yes" />']}});
	
});


</script> 
<h2>
<spring:message code="formfilter.details" />
</h2>
<table>
	<tr>
	  <td>
	  	<h3>${formfilter.form.name}</h3>
		<a style="float:right"  href="addformproperty.form?filterId=${formfilter.formFilterId}"><spring:message code="formfilter.addFilter" /></a>
	  </td>
	</tr>

	<tr class="boxHeader">
	  <td> <spring:message code="formfilter.listOfAssignedFilters" /> </td>
	</tr>

	<tr >
	   <td class="box">	   
		<table id="assignedFilters">
				<tr>
					<th><spring:message code="formfilter.name" /></th>
					<th><spring:message code="formfilter.description" /></th>
					<th><spring:message code="formfilter.properties" /></th>
					<th></th>
				</tr>
				<c:forEach var="filter" items="${formfilter.formFilterProperties}">
				<tr>
					<td>${filter.filterName}</td>
					<td>${filter.filterDescription}</td>
					<td>
					
					<c:forTokens items="${filter.properties}" delims="&" var="prop" varStatus="status">
					
					<c:set var="prop_detail" value="${fn:split(prop, '=')}" />
									
					<c:choose>
							<c:when test="${prop_detail[0] == 'minimumAge' || prop_detail[0]=='maximumAge' }">
								<spring:message code="formfilter.${prop_detail[0]}" /> = ${prop_detail[1]}<br/>
							</c:when>
							
							<c:when test="${prop_detail[0]== 'gender' }" >
								<spring:message code="formfilter.${prop_detail[0]}" /> = <spring:message code="formfilter.${prop_detail[1]}" /><br/>
							</c:when>
							
							<c:when test="${prop_detail[0]=='date' || prop_detail[0]=='show' }" >
								<spring:message code="formfilter.${prop_detail[0]}" /> = ${prop_detail[1]}<br/>
							</c:when>
							
							<c:when test="${prop_detail[0]=='roles'}" >
								<c:set var="roles" value="${fn:split(prop_detail[1], ',')}" />
							    <spring:message code="formfilter.${prop_detail[0]}" /> = ${roles[0]}<c:forEach var="role" items="${roles}" begin="1" >, ${role}</c:forEach>.<br/>								
							</c:when>
							
							<c:when test="${prop_detail[0]=='privileges'}" >
								<c:set var="privileges" value="${fn:split(prop_detail[1], ',')}" />
								<spring:message code="formfilter.${prop_detail[0]}" /> =  ${privileges[0]}<c:forEach var="privilege" items="${privileges}" begin="1" >, ${privilege}</c:forEach>.<br/>
							</c:when>
							
							<c:when test="${prop_detail[0]=='cohort'}" >
							    <spring:message code="formfilter.${prop_detail[0]}" /> = ${prop_detail[1]}<br/>
							</c:when>
							<c:otherwise>
					  		
							</c:otherwise>
					</c:choose>
									
									
					</c:forTokens>
					
					
					</td>
					<td>
					   <a href="addformproperty.form?filterId=${formfilter.formFilterId}&filterPropertyId=${filter.formFilterPropertyId}"><spring:message code="formfilter.edit" /></a>|<a href="deleteFilter.form?formFilterPropertyId=${filter.formFilterPropertyId }&formFilterId=${formfilter.formFilterId}" class="delete" ><spring:message code="formfilter.delete" /></a>
					</td>
				</tr>
				</c:forEach>
			</table>
			
		</td>
	</tr>
</table>


</openmrs:hasPrivilege>
<%@ include file="/WEB-INF/template/footer.jsp"%>