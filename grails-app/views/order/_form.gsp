<%@ page import="gr8.Order" %>



<div class="fieldcontain ${hasErrors(bean: orderInstance, field: 'entries', 'error')} ">
	<label for="entries">
		<g:message code="order.entries.label" default="Entries" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${orderInstance?.entries?}" var="e">
    <li><g:link controller="orderEntry" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="orderEntry" action="create" params="['order.id': orderInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'orderEntry.label', default: 'OrderEntry')])}</g:link>
</li>
</ul>

</div>

