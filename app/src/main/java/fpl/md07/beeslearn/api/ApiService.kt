package fpl.md07.beeslearn.api

import fpl.md07.beeslearn.models.GrammarQuestionModel
import fpl.md07.beeslearn.models.Movie
import fpl.md07.beeslearn.models.TrueFalseQuestionModel_A
import fpl.md07.beeslearn.models.Podcast
import fpl.md07.beeslearn.requests.LoginRequest
import fpl.md07.beeslearn.requests.RegisterRequest
import fpl.md07.beeslearn.models.UserModel
import fpl.md07.beeslearn.models.Word
import fpl.md07.beeslearn.models.responseModel.QuestionResponseModel
import fpl.md07.beeslearn.requests.ChangePasswordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {

    @POST("/auth/login")
    suspend fun Login(@Body request: LoginRequest): Response<UserModel>

    @POST("/auth/register")
    suspend fun Register(@Body request: RegisterRequest): Response<UserModel>

    @PUT("/auth/change-password")
    suspend fun ChangePassword(@Body request: ChangePasswordRequest): Response<String>

    // get question by amount

    @GET("/grammarquestions/byamount")
    suspend fun getGrammarByAmount(@Query("amount") amount: Int): Response<List<GrammarQuestionModel>>

    @GET("/truefalse/byamount")
    suspend fun getTrueFalseByAmount(@Query("amount") amount: Int): Response<List<TrueFalseQuestionModel_A>>

    @GET("/words/byamount")
    suspend fun getWordByAmount(@Query("amount") amount: Int): Response<List<Word>>

    // get questions
    @GET("/questions")
    suspend fun getRandomQuestions(@Query("total") amount: Int): Response<QuestionResponseModel>

    @GET("/movies")
    suspend fun getMovies(): Response<List<Movie>>

    @GET("/podcast")
    suspend fun getPodcasts(): Response<List<Podcast>>



}