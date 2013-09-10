package gr8

class CartController {
	CartService cartService
	OrderService orderService

	static defaultAction = "list"

	def list() {
		[books: cartService.booksInCart()]
	}

	def add(Long id) {
		Book book = Book.get(id)
		if (book) {
			cartService.addBook(book)
			flash.message = "Book successfully added to cart: $book"
			redirect(controller: 'book', action: 'list')
		} else {
			flash.message = "No book found"
			redirect(controller: 'book', action: 'list')
		}
	}

	def remove(Long id) {
		Book book = Book.get(id)
		cartService.remove(book)
		flash.message = "book removed"
		redirect(action: 'list')
	}

	def checkout() {
		try {
			BookOrder order = orderService.newOrder(cartService.booksInCart())
			cartService.empty()
			redirect(controller: 'bookOrder', action: 'show', id: order.id)
			return
		} catch (Throwable th) {
			log.error("Error checking out", th)
			flash.error = "Error checking out"
			redirect(action: 'list')
		}
	}

	def empty() {
		cartService.empty()
		flash.message = "Cart was emptied"
		redirect( action: 'list')
	}

	def apiStatus() {
		render(view: 'json{author:list}', model: [foo: 'bar'])
	}
}
