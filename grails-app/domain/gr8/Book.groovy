package gr8

class Book {
	String title
	String isbn
	String description
	List<Author> authors

	static hasMany = [authors:Author]


    static constraints = {
    }
}
