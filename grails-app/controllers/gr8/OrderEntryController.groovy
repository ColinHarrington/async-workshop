package gr8

import org.springframework.dao.DataIntegrityViolationException

class OrderEntryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [orderEntryInstanceList: OrderEntry.list(params), orderEntryInstanceTotal: OrderEntry.count()]
    }

    def create() {
        [orderEntryInstance: new OrderEntry(params)]
    }

    def save() {
        def orderEntryInstance = new OrderEntry(params)
        if (!orderEntryInstance.save(flush: true)) {
            render(view: "create", model: [orderEntryInstance: orderEntryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'orderEntry.label', default: 'OrderEntry'), orderEntryInstance.id])
        redirect(action: "show", id: orderEntryInstance.id)
    }

    def show(Long id) {
        def orderEntryInstance = OrderEntry.get(id)
        if (!orderEntryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'orderEntry.label', default: 'OrderEntry'), id])
            redirect(action: "list")
            return
        }

        [orderEntryInstance: orderEntryInstance]
    }

    def edit(Long id) {
        def orderEntryInstance = OrderEntry.get(id)
        if (!orderEntryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'orderEntry.label', default: 'OrderEntry'), id])
            redirect(action: "list")
            return
        }

        [orderEntryInstance: orderEntryInstance]
    }

    def update(Long id, Long version) {
        def orderEntryInstance = OrderEntry.get(id)
        if (!orderEntryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'orderEntry.label', default: 'OrderEntry'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (orderEntryInstance.version > version) {
                orderEntryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'orderEntry.label', default: 'OrderEntry')] as Object[],
                          "Another user has updated this OrderEntry while you were editing")
                render(view: "edit", model: [orderEntryInstance: orderEntryInstance])
                return
            }
        }

        orderEntryInstance.properties = params

        if (!orderEntryInstance.save(flush: true)) {
            render(view: "edit", model: [orderEntryInstance: orderEntryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'orderEntry.label', default: 'OrderEntry'), orderEntryInstance.id])
        redirect(action: "show", id: orderEntryInstance.id)
    }

    def delete(Long id) {
        def orderEntryInstance = OrderEntry.get(id)
        if (!orderEntryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'orderEntry.label', default: 'OrderEntry'), id])
            redirect(action: "list")
            return
        }

        try {
            orderEntryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'orderEntry.label', default: 'OrderEntry'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'orderEntry.label', default: 'OrderEntry'), id])
            redirect(action: "show", id: id)
        }
    }
}
