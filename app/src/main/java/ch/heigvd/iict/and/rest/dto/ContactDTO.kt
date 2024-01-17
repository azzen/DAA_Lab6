package ch.heigvd.iict.and.rest.dto

data class ContactDTO(
    var id: Long,
    var name: String,
    var firstname: String?,
    var birthday: String?,
    var email: String?,
    var address: String?,
    var zip: String?,
    var city: String?,
    var type: String?,
    var phoneNumber: String?
)
