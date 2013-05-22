<%@ page import="gr8.Book" %>



<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'authors', 'error')} ">
	<label for="authors">
		<g:message code="book.authors.label" default="Authors" />
		
	</label>
	<g:select name="authors" from="${gr8.Author.list()}" multiple="multiple" optionKey="id" size="5" value="${bookInstance?.authors*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'isbn', 'error')} ">
	<label for="isbn">
		<g:message code="book.isbn.label" default="Isbn" />
		
	</label>
	<g:textField name="isbn" value="${bookInstance?.isbn}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'qty', 'error')} required">
	<label for="qty">
		<g:message code="book.qty.label" default="Qty" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="qty" type="number" value="${bookInstance.qty}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="book.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${bookInstance?.title}"/>
</div>

