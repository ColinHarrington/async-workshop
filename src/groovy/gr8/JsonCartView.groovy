package gr8

import async.ActiveSessionCounterService
import groovy.json.JsonBuilder
import org.springframework.web.servlet.View

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JsonCartView implements View {
	ActiveSessionCounterService activeSessionCounterService

	@Override
	String getContentType() {
		return "text/json"
	}

	@Override
	void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		JsonBuilder builder = new JsonBuilder()

		def root = builder {
			ip request.getRemoteAddr()
			activeSessions activeSessionCounterService.activeSessions
		}

		String result = root.toString()

		response.setContentType(getContentType())

		def writer = response.writer

		writer.println result
	}
}
