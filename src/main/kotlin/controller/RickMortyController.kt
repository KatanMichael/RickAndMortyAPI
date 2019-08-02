package controller

import interfaces.RequestListener
import interfaces.RetrickController
import models.Character
import models.Location
import models.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

object RickMortyController
{

    private val retroRick = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val retroClient = retroRick.create(RetrickController::class.java)

    fun getAllCharacters(requestListener: RequestListener)
    {
        retroClient.getAllCharacters().enqueue(object : Callback<Response>
        {
            override fun onFailure(call: Call<Response>, t: Throwable)
            {
                requestListener.onError(t?.message.toString())
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>)
            {
                val body = response.body()

                println(body)

                if(body != null)
                {
                    requestListener.onComplete(body)
                }

            }

        })
    }

    fun getCharacterById(id: Int, requestListener: RequestListener)
    {
        retroClient.getCharacterById(id).enqueue(object : Callback<Character>
        {
            override fun onFailure(call: Call<Character>, t: Throwable)
            {
                requestListener.onError(t?.message.toString())
            }

            override fun onResponse(call: Call<Character>, response: retrofit2.Response<Character>)
            {
                val body = response.body()
                if(body != null)
                {
                    requestListener.onComplete(body)
                }
            }

        })
    }

    fun getCharactersByIds(ids: Array<Int>, requestListener: RequestListener)
    {
        var s = ""

        for(id in ids)
        {
            s+="$id,"
        }

        retroClient.getCharactersByIds(s)
            .enqueue(object : Callback<List<Character>>
            {
                override fun onFailure(call: Call<List<Character>>
                                       , t: Throwable)
                {

                    requestListener.onError(t?.message.toString())

                }

                override fun onResponse(call: Call<List<Character>>
                                        , response: retrofit2.Response<List<Character>>)
                {

                    val body = response.body()

                    if(body != null)
                    {
                        requestListener.onComplete(body)
                    }

                }


            })


    }


    fun getCharactersByFilters(name: String = "",
    status: String = "", species: String = "", type: String = "",
                              gender: String = "", requestListener: RequestListener)
    {

        retroClient.getCharactersByFilter(name,status,species,type,gender)
            .enqueue(object : Callback<Response>
            {
                override fun onFailure(call: Call<Response>, t: Throwable)
                {
                    println(t?.message.toString())
                }

                override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>)
                {
                    val body = response.body()

                    if(body != null)
                    {
                        requestListener.onComplete(body)
                    }
                }


            })

    }

    fun getAllLocation(requestListener: RequestListener)
    {
        retroClient.getAllLocation().enqueue(object : Callback<Response>
        {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                requestListener.onError(t?.message.toString())
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>)
            {
                val body = response.body()

                if(body != null)
                {
                    requestListener.onComplete(body)
                }

            }

        })
    }

    fun getLocationsById(ids: Array<Int>, requestListener: RequestListener)
    {
        var s = ""

        for(id in ids)
        {
            s+="$id,"
        }

        retroClient.getLocationsByIds(s).enqueue(object : Callback<List<Location>>
        {
            override fun onFailure(call: Call<List<Location>>, t: Throwable)
            {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<Location>>, response: retrofit2.Response<List<Location>>)
            {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }
}

