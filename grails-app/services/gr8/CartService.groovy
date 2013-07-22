package gr8

class CartService {
	static scope = 'session'

	List<Long> bookIds = []

	def addBook(Book book) {
		bookIds << book.id
	}

	def empty() {
		bookIds = []
	}

	def remove(Book book) {
		bookIds.remove(book?.id)
	}

	List<Book> booksInCart() {
		return bookIds.collect { id -> Book.get(id) }
	}

}
