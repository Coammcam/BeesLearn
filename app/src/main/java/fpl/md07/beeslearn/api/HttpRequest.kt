package fpl.md07.beeslearn.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object HttpRequest {
    private const val URL = "http://beelearns.duckdns.org:9000"
    fun getInstance(): ApiService {
        return Retrofit
            .Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}