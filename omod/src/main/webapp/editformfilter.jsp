<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<h2>
<spring:message code="formfilter.details" />
</h2>

<table >
<tr class="boxHeader"><td>
${formfilter.form.name}

<a style="float:right"  href="addformproperty.form?formFilterId=${formfilter.formFilterId}"><spring:message code="formfilter.addFormProperty" /></a>
</td></tr>

<tr><td>

<table>
  <tr>
    <th>Name</th>
    <th>Description</th>
    <th>Properties</th>
    
  </tr>
  <tr>
    <td>Row 1: Col 1</td>
    <td>Row 1: Col 2</td>
    <td>Row 1: Col 1</td>
    
  </tr>
</table>


</td></tr>
</table>



<%@ include file="/WEB-INF/template/footer.jsp"%>