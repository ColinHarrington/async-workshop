package gr8

class OrderService {

	BookOrder newOrder(List<Book> books) {
		println books.dump()
		BookOrder order = new BookOrder()
		order.save()

		books.unique().each { book ->
			OrderEntry entry = new OrderEntry(
					book: book,
					quantity: books.findAll { it == book }.size(),
					order: order
			)
			entry.save()
			order.addToEntries(
					entry
			)
		}

		order.save()

		runAsync {
			sleep(10 * 1000)
			approveOrder(order)
		}

		return order
	}

	void approveOrder(BookOrder order) {
		order.approved = true
		order.save(flush:true)

		Map data = [ message: "Order #${order.id} Approved..."]
		event(topic: 'globalBannerMessageUpdated', for: 'browser', data: data)
	}
}
