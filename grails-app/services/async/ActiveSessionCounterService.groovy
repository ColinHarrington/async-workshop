package async

import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

class ActiveSessionCounterService implements HttpSessionListener {

	boolean transactional = false

	int getActiveSessions() {
		return activeSessions
	}

	private int activeSessions = 0


	@Override
	void sessionCreated(HttpSessionEvent httpSessionEvent) {
		println " ++ Session Created"
		activeSessions++
	}

	@Override
	void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		println " -- Session Destroyed"
		activeSessions--
	}
}
