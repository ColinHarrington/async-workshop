package gr8

class OrderEntry {
	Book book
	int quantity = 0

	static belongsTo = [order: Order]

	static constraints = {
		book nullable: false
	}
}
