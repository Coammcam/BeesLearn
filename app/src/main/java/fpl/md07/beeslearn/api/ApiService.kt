package fpl.md07.beeslearn.api

import fpl.md07.beeslearn.models.Movie
import fpl.md07.beeslearn.requests.LoginRequest
import fpl.md07.beeslearn.requests.RegisterRequest
import fpl.md07.beeslearn.models.UserModel
import fpl.md07.beeslearn.requests.ChangePasswordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    @POST("/auth/login")
    suspend fun Login(@Body request: LoginRequest): Response<UserModel>

    @POST("/auth/register")
    suspend fun Register(@Body request: RegisterRequest): Response<UserModel>

    @PUT("/auth/change-password")
    suspend fun ChangePassword(@Body request: ChangePasswordRequest): Response<String>

    @GET("/movies")
    suspend fun getMovies(): Response<List<Movie>>

}