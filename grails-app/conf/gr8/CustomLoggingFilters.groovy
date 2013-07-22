package gr8

class CustomLoggingFilters {

	def filters = {
		all(controller: '*', action: '*') {
			before = {
				log.info ">> Filters << Before"
			}
			after = { Map model ->
				log.info ">> Filters << After"
			}
			afterView = { Exception e ->
				log.info ">> Filters << afterView"
			}
		}
	}
}
