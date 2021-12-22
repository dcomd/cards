package br.com.alexandre.card.domain.model

class CreditCard(
    val number : String,
    val enterprise: String,
    val ownerFullName: String,
    val ownerRegNumber: String = "",
    val expiryMonth: Int = 0,
    val expiryYear: Int = 0,
    val securityNumber: String = ""
){
    fun getNumberAsHidden()
            = String.format("**** **** **** %s", number.takeLast(4))

    fun getOwnerFullNameAsHidden() : String {
        val nameList = ownerFullName.split(" ")

        val firstName = nameList.first().substring(0, 2)
        val lastName = nameList.last()

        return String.format("%s... %s", firstName, lastName)
    }
}