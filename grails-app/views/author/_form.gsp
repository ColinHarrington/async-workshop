<%@ page import="gr8.Author" %>



<div class="fieldcontain ${hasErrors(bean: authorInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="author.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${authorInstance?.name}"/>
</div>

