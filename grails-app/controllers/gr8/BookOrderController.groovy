package gr8

import org.springframework.dao.DataIntegrityViolationException

class BookOrderController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

//	def summary() {
//		render
//	}
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [bookOrderInstanceList: BookOrder.list(params), bookOrderInstanceTotal: BookOrder.count()]
    }

    def create() {
        [bookOrderInstance: new BookOrder(params)]
    }

    def save() {
        def bookOrderInstance = new BookOrder(params)
        if (!bookOrderInstance.save(flush: true)) {
            render(view: "create", model: [bookOrderInstance: bookOrderInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'bookOrder.label', default: 'BookOrder'), bookOrderInstance.id])
        redirect(action: "show", id: bookOrderInstance.id)
    }

    def show(Long id) {
        def bookOrderInstance = BookOrder.get(id)
        if (!bookOrderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bookOrder.label', default: 'BookOrder'), id])
            redirect(action: "list")
            return
        }

        [bookOrderInstance: bookOrderInstance]
    }

    def edit(Long id) {
        def bookOrderInstance = BookOrder.get(id)
        if (!bookOrderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bookOrder.label', default: 'BookOrder'), id])
            redirect(action: "list")
            return
        }

        [bookOrderInstance: bookOrderInstance]
    }

    def update(Long id, Long version) {
        def bookOrderInstance = BookOrder.get(id)
        if (!bookOrderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bookOrder.label', default: 'BookOrder'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (bookOrderInstance.version > version) {
                bookOrderInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'bookOrder.label', default: 'BookOrder')] as Object[],
                          "Another user has updated this BookOrder while you were editing")
                render(view: "edit", model: [bookOrderInstance: bookOrderInstance])
                return
            }
        }

        bookOrderInstance.properties = params

        if (!bookOrderInstance.save(flush: true)) {
            render(view: "edit", model: [bookOrderInstance: bookOrderInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'bookOrder.label', default: 'BookOrder'), bookOrderInstance.id])
        redirect(action: "show", id: bookOrderInstance.id)
    }

    def delete(Long id) {
        def bookOrderInstance = BookOrder.get(id)
        if (!bookOrderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bookOrder.label', default: 'BookOrder'), id])
            redirect(action: "list")
            return
        }

        try {
            bookOrderInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'bookOrder.label', default: 'BookOrder'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'bookOrder.label', default: 'BookOrder'), id])
            redirect(action: "show", id: id)
        }
    }
}
