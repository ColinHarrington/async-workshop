package gr8

class Book {
	String title
	String isbn
	List<Author> authors
	Integer qty = 0

	static hasMany = [authors: Author]


	static constraints = {
	}
}
