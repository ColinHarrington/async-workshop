<%@ page import="gr8.OrderEntry" %>



<div class="fieldcontain ${hasErrors(bean: orderEntryInstance, field: 'book', 'error')} required">
	<label for="book">
		<g:message code="orderEntry.book.label" default="Book" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="book" name="book.id" from="${gr8.Book.list()}" optionKey="id" required="" value="${orderEntryInstance?.book?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: orderEntryInstance, field: 'order', 'error')} required">
	<label for="order">
		<g:message code="orderEntry.order.label" default="Order" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="order" name="order.id" from="${gr8.Order.list()}" optionKey="id" required="" value="${orderEntryInstance?.order?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: orderEntryInstance, field: 'quantity', 'error')} required">
	<label for="quantity">
		<g:message code="orderEntry.quantity.label" default="Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="quantity" type="number" value="${orderEntryInstance.quantity}" required=""/>
</div>

