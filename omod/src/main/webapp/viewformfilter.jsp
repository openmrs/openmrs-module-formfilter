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
		<a style="float:right"  href="addformproperty.form?filterId=${formfilter.formFilterId}"><spring:message code="formfilter.addFormProperty" /></a>
	  </td>
	</tr>

	<tr class="boxHeader">
	  <td>List Of Assigned Filters</td>
	</tr>

	<tr class="box">
	   <td>
		<table>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Properties</th>
				</tr>
				<c:forEach var="property" items="${formfilter.formFilterProperties}">
				<tr>
					<td>${property.filterName}</td>
					<td>${property.filterDescription}</td>
					<td>
					<c:forTokens items="${property.properties}" delims="&" var="prop" varStatus="status">
					 ${prop}<br/>
					</c:forTokens>
					</td>
					<td>
					   <a href="#">Edit</a>|<a href="#">Delete</a>
					</td>					
				</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>



<%@ include file="/WEB-INF/template/footer.jsp"%>