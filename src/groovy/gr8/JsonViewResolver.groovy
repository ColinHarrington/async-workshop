package gr8

import async.ActiveSessionCounterService
import org.springframework.web.servlet.View
import org.springframework.web.servlet.ViewResolver

class JsonViewResolver implements ViewResolver {
	ActiveSessionCounterService activeSessionCounterService

	View resolveViewName(String viewName, Locale locale) {
		println viewName

		if (viewName?.matches(/.*json\{(.*)\}/)){
			return new JsonCartView(activeSessionCounterService: activeSessionCounterService)
		} else {
			return null
		}
	}
}