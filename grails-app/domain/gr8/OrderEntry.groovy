package gr8

class OrderEntry {
	Book book
	int quantity = 0

	static belongsTo = [order: BookOrder]

	static constraints = {
		book nullable: false
	}
}
