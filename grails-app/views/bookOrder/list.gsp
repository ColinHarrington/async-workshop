
<%@ page import="gr8.BookOrder" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bookOrder.label', default: 'BookOrder')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-bookOrder" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-bookOrder" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="approved" title="${message(code: 'bookOrder.approved.label', default: 'Approved')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'bookOrder.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="fulfilled" title="${message(code: 'bookOrder.fulfilled.label', default: 'Fulfilled')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'bookOrder.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${bookOrderInstanceList}" status="i" var="bookOrderInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${bookOrderInstance.id}">${fieldValue(bean: bookOrderInstance, field: "approved")}</g:link></td>
					
						<td><g:formatDate date="${bookOrderInstance.dateCreated}" /></td>
					
						<td><g:formatBoolean boolean="${bookOrderInstance.fulfilled}" /></td>
					
						<td><g:formatDate date="${bookOrderInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${bookOrderInstanceTotal}" />
			</div>
		</div>
	</body>
</html>