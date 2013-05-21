package gr8

class Order {

	Date dateCreated
	Date lastUpdated

	static hasMany = [entries: OrderEntry]

	static constraints = {
	}
}
