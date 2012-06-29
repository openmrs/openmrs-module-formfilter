<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>


<h2>
<spring:message code="formfilter.details" />
</h2>
<table>
	<tr>
	  <td>
	  	${formfilter.form.name}
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
					 <spring:message code="formfilter.${prop_detail[0]}" /> = ${prop_detail[1]}<br/>					 
					</c:forTokens>
					</td>
					<td>
					   <a href="addformproperty.form?filterId=${formfilter.formFilterId}&filterPropertyId=${filter.formFilterPropertyId}"><spring:message code="formfilter.edit" /></a>|<a href="deleteFilter.form?formFilterPropertyId=${filter.formFilterPropertyId }&formFilterId=${formfilter.formFilterId}"><spring:message code="formfilter.delete" /></a>
					</td>					
				</tr>
				</c:forEach>
			</table>
			
		</td>
	</tr>
</table>



<%@ include file="/WEB-INF/template/footer.jsp"%>