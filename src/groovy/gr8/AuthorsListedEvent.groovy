package gr8

import org.springframework.context.ApplicationEvent


class AuthorsListedEvent extends ApplicationEvent {

	AuthorsListedEvent(Object source) {
		super(source)
	}
}
