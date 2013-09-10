import gr8.Author
import gr8.Book
import grails.converters.JSON

class BootStrap {

	def init = { servletContext ->
		createBooks()

//		JSON.registerObjectMarshaller(Author) { Author author ->
//			[name: author.name, id: author.id]
//		}
//
//		JSON.createNamedConfig("public") {cfg ->
//			cfg.registerObjectMarshaller(Book) { Book b ->
//				[id: b.id, title: b.title, authors: b.authors, inStock: b.qty > 0]
//			}
//		}
	}
	def destroy = {
	}

	def createBooks() {
		createBook("The Definitive Guide to Grails 2", ['Jeff Scott Brown', 'Graeme Rocher'], "978-1430243779")
		createBook("Programming Grails", ['Burt Beckwith'], "978-1449323936")
		createBook("Grails Persistence with GORM and GSQL", ['Robert Fischer'], "978-1430219262")
		createBook("Grails in Action", ['Glenn Smith', 'Peter Ledbrook'], "978-1933988931")
		createBook("Groovy in Action", ['Dierk Koenig', 'Andrew Glover', 'Paul King', 'Guillaume Laforge', 'Jon Skeet'], "978-1932394849")
		createBook("The Definitive Guide to Grails", ['Jeff Scott Brown', 'Graeme Rocher'], "978-1590599952")
	}

	def createBook(String title, List authors, String isbn) {
		Book book = new Book(
				title: title,
				authors: createAuthors(authors),
				isbn: isbn,
				qty: 10 * Math.random()
		)
		book.save(failOnError: true)
	}

	List<Author> createAuthors(List names) {
		List<Author> authors = names.collect { name ->
			return Author.findOrCreateWhere(name: name)
		}
		return authors.findAll()
	}
}