package interfaces

import models.Character
import models.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface RetrickController
{

    @GET("character/")
    fun getAllCharacters(): Call<Response>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int) :Call<Character>

    @GET("character/{ids}")
    fun getCharactersByIds(@Path("ids") id: String) :Call<List<Character>>

    @GET("character/")
    fun getCharactersByFilter(@Query("name")name: String, @Query("status")
    status: String, @Query("species") species: String,
                              @Query("type") type: String,
                              @Query("gender") gender: String) :Call<Response>

}