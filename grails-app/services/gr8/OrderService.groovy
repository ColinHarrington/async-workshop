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
		return order
	}
}
