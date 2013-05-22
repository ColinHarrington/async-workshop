
<%@ page import="gr8.OrderEntry" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'orderEntry.label', default: 'OrderEntry')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-orderEntry" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-orderEntry" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="orderEntry.book.label" default="Book" /></th>
					
						<th><g:message code="orderEntry.order.label" default="Order" /></th>
					
						<g:sortableColumn property="quantity" title="${message(code: 'orderEntry.quantity.label', default: 'Quantity')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${orderEntryInstanceList}" status="i" var="orderEntryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${orderEntryInstance.id}">${fieldValue(bean: orderEntryInstance, field: "book")}</g:link></td>
					
						<td>${fieldValue(bean: orderEntryInstance, field: "order")}</td>
					
						<td>${fieldValue(bean: orderEntryInstance, field: "quantity")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${orderEntryInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
