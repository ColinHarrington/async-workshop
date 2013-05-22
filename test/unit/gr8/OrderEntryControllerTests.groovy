package gr8



import org.junit.*
import grails.test.mixin.*

@TestFor(OrderEntryController)
@Mock(OrderEntry)
class OrderEntryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/orderEntry/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.orderEntryInstanceList.size() == 0
        assert model.orderEntryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.orderEntryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.orderEntryInstance != null
        assert view == '/orderEntry/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/orderEntry/show/1'
        assert controller.flash.message != null
        assert OrderEntry.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/orderEntry/list'

        populateValidParams(params)
        def orderEntry = new OrderEntry(params)

        assert orderEntry.save() != null

        params.id = orderEntry.id

        def model = controller.show()

        assert model.orderEntryInstance == orderEntry
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/orderEntry/list'

        populateValidParams(params)
        def orderEntry = new OrderEntry(params)

        assert orderEntry.save() != null

        params.id = orderEntry.id

        def model = controller.edit()

        assert model.orderEntryInstance == orderEntry
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/orderEntry/list'

        response.reset()

        populateValidParams(params)
        def orderEntry = new OrderEntry(params)

        assert orderEntry.save() != null

        // test invalid parameters in update
        params.id = orderEntry.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/orderEntry/edit"
        assert model.orderEntryInstance != null

        orderEntry.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/orderEntry/show/$orderEntry.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        orderEntry.clearErrors()

        populateValidParams(params)
        params.id = orderEntry.id
        params.version = -1
        controller.update()

        assert view == "/orderEntry/edit"
        assert model.orderEntryInstance != null
        assert model.orderEntryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/orderEntry/list'

        response.reset()

        populateValidParams(params)
        def orderEntry = new OrderEntry(params)

        assert orderEntry.save() != null
        assert OrderEntry.count() == 1

        params.id = orderEntry.id

        controller.delete()

        assert OrderEntry.count() == 0
        assert OrderEntry.get(orderEntry.id) == null
        assert response.redirectedUrl == '/orderEntry/list'
    }
}
