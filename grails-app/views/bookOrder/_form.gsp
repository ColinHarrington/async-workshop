<%@ page import="gr8.BookOrder" %>



<div class="fieldcontain ${hasErrors(bean: bookOrderInstance, field: 'approved', 'error')} ">
	<label for="approved">
		<g:message code="bookOrder.approved.label" default="Approved" />
		
	</label>
	<g:checkBox name="approved" value="${bookOrderInstance?.approved}" />
</div>

<div class="fieldcontain ${hasErrors(bean: bookOrderInstance, field: 'entries', 'error')} ">
	<label for="entries">
		<g:message code="bookOrder.entries.label" default="Entries" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${bookOrderInstance?.entries?}" var="e">
    <li><g:link controller="orderEntry" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="orderEntry" action="create" params="['bookOrder.id': bookOrderInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'orderEntry.label', default: 'OrderEntry')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: bookOrderInstance, field: 'fulfilled', 'error')} ">
	<label for="fulfilled">
		<g:message code="bookOrder.fulfilled.label" default="Fulfilled" />
		
	</label>
	<g:checkBox name="fulfilled" value="${bookOrderInstance?.fulfilled}" />
</div>

