import gr8.JsonViewResolver

beans = {
	jsonViewResolver(JsonViewResolver) {
		activeSessionCounterService = ref('activeSessionCounterService')
	}
}
