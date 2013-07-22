package gr8

class BookOrder {
	boolean approved = false
	boolean fulfilled = false

	Date dateCreated
	Date lastUpdated

	static hasMany = [entries: OrderEntry]

	static constraints = {

	}
}
