package gr8

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener

class LoggingApplicationListenerService implements ApplicationListener {

	boolean transactional = false

	@Override
	void onApplicationEvent(ApplicationEvent e) {
//		println " :::::::Event:::::::${e.dump()}"
	}
}
