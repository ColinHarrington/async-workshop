<%@ page import="gr8.Book" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}"/>
	<title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-book" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
	<ul>
		<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
		<li><g:link class="list" controller="book" action="list">Books</g:link></li>
		<li><g:link class="delete" controller="cart" action="empty" onclick="return confirm('Are you sure?');">Empty Cart</g:link></li>
	</ul>
</div>

<div id="list-book" class="content scaffold-list cart-list" role="main">
	<h1>Cart</h1>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<g:if test="${books}">
		<table>
			<thead>
			<tr>

				<g:sortableColumn property="isbn" title="${message(code: 'book.isbn.label', default: 'Isbn')}"/>

				<g:sortableColumn property="title" title="${message(code: 'book.title.label', default: 'Title')}"/>

				<th>
				Actions
				</th>
			</tr>
			</thead>
			<tbody>
			<g:each in="${books}" status="i" var="book">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

					<td><g:link controller="book" action="show"
					            id="${book.id}">${fieldValue(bean: book, field: "isbn")}</g:link></td>

					<td>${fieldValue(bean: book, field: "title")}</td>

					<td><g:link action="remove" id="${book.id}">Remove</g:link></td>
				</tr>
			</g:each>
			</tbody>
		</table>
			<fieldset class="buttons">
				<g:link action="checkout">Checkout</g:link>
			</fieldset>
	</g:if>
	<g:else>
		<h2>Empty Cart</h2>
	</g:else>
</div>
</body>
</html>
