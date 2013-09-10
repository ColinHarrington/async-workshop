package gr8

import grails.converters.JSON
import grails.web.JSONBuilder
import org.springframework.dao.DataIntegrityViolationException

class BookController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }





	/** JSON **/
	def appStatus() {
		render(contentType: "text/json", text: "{\"foo\":\"bar\"}")
	}

	/** writing the raw response **/
	def appStatus2() {
		response.setContentType("text/json")
		response.writer.println('{"spring":"boot"}')
		response.flushBuffer()
	}





	/** Builder syntax **/
	def appStatus3() {
		render(contentType: "text/json") {
			hello = "world"
		}
	}

	/** more builder **/
	def bookList() {
		def results = Book.list()

		render(contentType: "text/json") {
			books = array {
				for (b in results) {
					book title: b.title
				}
			}
		}
	}

	// Direct Builder API
	def count() {
		JSONBuilder builder = new JSONBuilder()

		JSON json = builder.build {
			count = Book.count()
			foo(asdf: 'qwerty')
			stock {
				qty =  Book.list()*.qty.sum() ?: 0
			}
		}

		render json
	}

	//Condensed
	def count2() {
		render new JSONBuilder().build {
			count = Book.count()
			stock(qty: Book.list()*.qty.sum() ?: 0)
		}
	}








	/** Converters **/
	def bookList2() {
		render Book.list() as JSON
	}

	def bookList3() {
		render Book.list().encodeAsJSON()
	}





	/** Object Marshalling. **/
	def authors() {
		render Author.list() as JSON
	}


	/** Object Marshalling. **/
	def publicBooks() {
		JSON.use('public') {
			render Book.list() as JSON
		}
	}

	def privateBooks() {
		render Book.list() as JSON
	}





















	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [bookInstanceList: Book.list(params), bookInstanceTotal: Book.count()]
    }

    def create() {
        [bookInstance: new Book(params)]
    }

    def save() {
        def bookInstance = new Book(params)
        if (!bookInstance.save(flush: true)) {
            render(view: "create", model: [bookInstance: bookInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'book.label', default: 'Book'), bookInstance.id])
        redirect(action: "show", id: bookInstance.id)
    }

    def show(Long id) {
        def bookInstance = Book.get(id)
        if (!bookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "list")
            return
        }

        [bookInstance: bookInstance]
    }

    def edit(Long id) {
        def bookInstance = Book.get(id)
        if (!bookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "list")
            return
        }

        [bookInstance: bookInstance]
    }

    def update(Long id, Long version) {
        def bookInstance = Book.get(id)
        if (!bookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (bookInstance.version > version) {
                bookInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'book.label', default: 'Book')] as Object[],
                          "Another user has updated this Book while you were editing")
                render(view: "edit", model: [bookInstance: bookInstance])
                return
            }
        }

        bookInstance.properties = params

        if (!bookInstance.save(flush: true)) {
            render(view: "edit", model: [bookInstance: bookInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'book.label', default: 'Book'), bookInstance.id])
        redirect(action: "show", id: bookInstance.id)
    }

    def delete(Long id) {
        def bookInstance = Book.get(id)
        if (!bookInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "list")
            return
        }

        try {
            bookInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'book.label', default: 'Book'), id])
            redirect(action: "show", id: id)
        }
    }


}
