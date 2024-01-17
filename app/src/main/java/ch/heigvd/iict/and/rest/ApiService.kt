package ch.heigvd.iict.and.rest

import ch.heigvd.iict.and.rest.dto.ContactDTO
import ch.heigvd.iict.and.rest.models.Contact
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("enroll")
    suspend fun enroll(): String

    @GET("contacts")
    suspend fun getAllContacts(@Header("X-UUID") uuid: String): List<Contact>

    @GET("contacts/{id}")
    suspend fun getContact(@Header("X-UUID") uuid: String, @Path("id") id: Long): Contact

    @POST("contacts/")
    suspend fun createContact(@Header("X-UUID") uuid: String, @Body contact: Contact): Contact

    @PUT("contacts/{id}")
    suspend fun updateContact(
        @Header("X-UUID") uuid: String,
        @Path("id") id: Long,
        @Body contact: ContactDTO
    ): Contact

    @DELETE("contacts/{id}")
    suspend fun deleteContact(@Header("X-UUID") uuid: String, @Path("id") id: Long): Unit
}