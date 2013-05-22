
<%@ page import="gr8.Book" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

		<r:require module="grailsEvents"/>
		<r:script>
			var grailsEvents = new grails.Events("http://localhost:8080/async/");
			grailsEvents.on('globalBannerMessageUpdated', function(data) {
					   $("#globalBannerMessage").html(data.message).show();
					}
			);
		</r:script>
	</head>
	<body>
		<a href="#list-book" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-book" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="isbn" title="${message(code: 'book.isbn.label', default: 'Isbn')}" />

						<g:sortableColumn property="qty" title="${message(code: 'book.qty.label', default: 'Qty')}" />

						<g:sortableColumn property="title" title="${message(code: 'book.title.label', default: 'Title')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${bookInstanceList}" status="i" var="bookInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${bookInstance.id}">${fieldValue(bean: bookInstance, field: "isbn")}</g:link></td>

						<td>${fieldValue(bean: bookInstance, field: "qty")}</td>

						<td>${fieldValue(bean: bookInstance, field: "title")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${bookInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
