package gr8

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener

class MyApplicationListenerService implements ApplicationListener {

	boolean transactional = false

	@Override
	void onApplicationEvent(ApplicationEvent e) {
		log.debug "${e.dump()}"
		println "++++++++++ ${e.dump()}"
	}
}
