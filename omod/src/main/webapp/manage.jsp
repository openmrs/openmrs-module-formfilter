<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables.css" />
<openmrs:htmlInclude file="/scripts/jquery/jquery-1.3.2.min.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />
<openmrs:hasPrivilege privilege="Manage Forms">

<%@ include file="template/localHeader.jsp"%>
<h2>
<spring:message code="formfilter.selectForm" />
</h2>

<script type="text/javascript">

	$j = jQuery.noConflict();
	
	<%-- global var and datatable filter for showRetired --%>
	var showRetiredForms= false;
	$j.fn.dataTableExt.afnFiltering.push(
		function( oSettings, aData, iDataIndex ) {
			if (oSettings.sTableId != 'formFilterTable')
				return true;
			else
				return showRetiredForms || aData[4] == 'false';
		}
	);
	
	$j(document).ready(function() {
		
		var oTable = $j("#formFilterTable").dataTable({
			"bPaginate" : false,
			"bAutoWidth" : false,
			"aaSorting" : [ [ 0, 'asc' ] ],
			"aoColumns":
				[
					{ "iDataSort": 1 },
					{ "bVisible": false, "sType": "numeric" },
					null,
					{ "sClass": "EncounterTypeClass" },
					{ "bVisible": false }
				]
		});
		
		oTable.fnDraw(); <%-- trigger filter-and-draw of datatable now --%>

		<%-- trigger filter-and-draw of the datatable whenever the showRetired checkbox changes --%>
		$j('#showRetired').click(function() {
			showRetiredForms= this.checked;
			oTable.fnDraw();
		});

		<%-- move the showRetired checkbox inside the flow of the datatable after the filter --%>
		$j('#handleForShowRetired').appendTo($j('#formFilterTable_filter'));

		
	});	
	
</script>
	
	<div class="boxHeader">
		<b><spring:message code="formfilter.forms" /></b>
	</div>
	
	<div class="box" >
	<span id="handleForShowRetired">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" id="showRetired"/> <spring:message code="SearchResults.includeRetired"/>
	</span>

	<table id="formFilterTable" cellpadding="2" cellspacing="0">
		<thead>
			<tr>
				<th><spring:message code="general.name"/></th>
				<th><!-- Hidden column for sorting previous column --></th>
				<th><spring:message code="Form.version"/></th>
				<th class="EncounterTypeClass"><spring:message code="Encounter.type"/></th>
				<th><!-- Hidden column for retired --></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="form" items="${formList}" varStatus="rowCounter">
				<tr<c:if test="${form.retired}"> class="retired" </c:if>>
						<td>
							<a href="viewformfilter.form?formId=${form.formId}" >${form.name}</a>

						</td>
						<td>
							${rowCounter.count}
						</td>
						<td>
							${form.version}
							<c:if test="${!form.published}"><i>(<spring:message code="Form.unpublished"/>)</i></c:if>
						</td>
						<td>
							${form.encounterType.name}
						</td>
						<td>${form.retired}</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

</openmrs:hasPrivilege>
<%@ include file="/WEB-INF/template/footer.jsp"%>