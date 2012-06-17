<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<h2>
<spring:message code="formfilter.details" />
</h2>

<table border="1">
<tr><td>
${form.name}

<a align="right" href="addformproperty.form?formId=${form.formId}"><spring:message code="formfilter.addFormProperty" /></a>
</td></tr>

<tr><td>

<table>
  <tr>
    <th>Column 1 Heading</th>
    <th>Column 2 Heading</th>
    <th>Column 1 Heading</th>
    <th>Column 2 Heading</th>
    <th>Column 1 Heading</th>
    <th>Column 2 Heading</th>
  </tr>
  <tr>
    <td>Row 1: Col 1</td>
    <td>Row 1: Col 2</td>
    <td>Row 1: Col 1</td>
    <td>Row 1: Col 2</td>
    <td>Row 1: Col 1</td>
    <td>Row 1: Col 2</td>
  </tr>
</table>


</td></tr>
</table>



<%@ include file="/WEB-INF/template/footer.jsp"%>