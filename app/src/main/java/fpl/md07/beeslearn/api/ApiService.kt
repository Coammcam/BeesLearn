package fpl.md07.beeslearn.api

import com.google.gson.JsonObject
import fpl.md07.beeslearn.models.CurrencyModel
import fpl.md07.beeslearn.models.GrammarQuestionModel
import fpl.md07.beeslearn.models.Level
import fpl.md07.beeslearn.models.Movie
import fpl.md07.beeslearn.models.Music
import fpl.md07.beeslearn.models.PartOfLevel
import fpl.md07.beeslearn.models.TrueFalseQuestionModel
import fpl.md07.beeslearn.models.Podcast
import fpl.md07.beeslearn.requests.LoginRequest
import fpl.md07.beeslearn.requests.RegisterRequest
import fpl.md07.beeslearn.models.UserModel
import fpl.md07.beeslearn.models.Word
import fpl.md07.beeslearn.models.responseModel.QuestionResponseModel
import fpl.md07.beeslearn.requests.ChangePasswordRequest
import fpl.md07.beeslearn.requests.UpdateUserRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("/auth/login")
    suspend fun Login(@Body request: LoginRequest): Response<UserModel>

    @POST("/auth/register")
    suspend fun Register(@Body request: RegisterRequest): Response<UserModel>

    @PUT("/auth/change-password")
    suspend fun ChangePassword(@Body request: ChangePasswordRequest): Response<JsonObject>

    @GET("/level")
    suspend fun getLevels(): Response<ArrayList<Level>>

    @GET("/part_of_level/{level}")
    suspend fun getPartOfLevel(@Path("level") level: Int): Response<ArrayList<PartOfLevel>>

    // get question by amount
    @GET("/grammarquestions/{level}")
    suspend fun getGrammarByAmount(@Path("level") level: Int, @Query("amount") amount: Int): Response<List<GrammarQuestionModel>>

    @GET("/truefalse/{level}")
    suspend fun getTrueFalseByAmount(@Path("level") level: Int, @Query("amount") amount: Int): Response<List<TrueFalseQuestionModel>>

    @GET("/words/{level}")
    suspend fun getWordByAmount(@Path("level") level: Int, @Query("amount") amount: Int): Response<List<Word>>

    // get questions
    @GET("/questions")
    suspend fun getRandomQuestions(@Query("total") amount: Int): Response<QuestionResponseModel>

    @GET("/movies")
    suspend fun getMovies(): Response<List<Movie>>

    @GET("/podcast")
    suspend fun getPodcasts(): Response<List<Podcast>>

    @PUT("user")
    suspend fun EditProfile(@Body request: UpdateUserRequest): Response<UserModel>

    @GET("/music")
    suspend fun getMusicList(): Response<List<Music>>

    @GET("/user/data/{email}")
    suspend fun getCurrency(@Path("email") email: String): Response<CurrencyModel>

    @PUT("/user/data/{email}")
    suspend fun updateCurrency(@Path("email") email: String, @Body currencyData: CurrencyModel): Response<CurrencyModel>

    @Multipart
    @PUT("/user")
    suspend fun EditProfile(
        @Part("email") email: RequestBody,
        @Part("username") username: RequestBody,
        @Part("phone_number") phoneNumber: RequestBody?,
        @Part("date_of_birth") dateOfBirth: RequestBody?,
        @Part file: MultipartBody.Part?
    ): Response<UserModel>

}





