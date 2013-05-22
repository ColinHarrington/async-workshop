
<%@ page import="gr8.OrderEntry" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'orderEntry.label', default: 'OrderEntry')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-orderEntry" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-orderEntry" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list orderEntry">
			
				<g:if test="${orderEntryInstance?.book}">
				<li class="fieldcontain">
					<span id="book-label" class="property-label"><g:message code="orderEntry.book.label" default="Book" /></span>
					
						<span class="property-value" aria-labelledby="book-label"><g:link controller="book" action="show" id="${orderEntryInstance?.book?.id}">${orderEntryInstance?.book?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${orderEntryInstance?.order}">
				<li class="fieldcontain">
					<span id="order-label" class="property-label"><g:message code="orderEntry.order.label" default="Order" /></span>
					
						<span class="property-value" aria-labelledby="order-label"><g:link controller="order" action="show" id="${orderEntryInstance?.order?.id}">${orderEntryInstance?.order?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${orderEntryInstance?.quantity}">
				<li class="fieldcontain">
					<span id="quantity-label" class="property-label"><g:message code="orderEntry.quantity.label" default="Quantity" /></span>
					
						<span class="property-value" aria-labelledby="quantity-label"><g:fieldValue bean="${orderEntryInstance}" field="quantity"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${orderEntryInstance?.id}" />
					<g:link class="edit" action="edit" id="${orderEntryInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
