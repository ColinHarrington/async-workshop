package gr8

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent

class BookstoreSecurtyEventListenerService implements ApplicationListener<AbstractAuthenticationFailureEvent> {

	boolean transactional = false

	@Override
	void onApplicationEvent(AbstractAuthenticationFailureEvent e) {
		println ">>>> AUTHENTICATION FAILED <<<<<"
	}
}
