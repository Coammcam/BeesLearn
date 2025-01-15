package fpl.md07.beeslearn.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.api.HttpRequest
import fpl.md07.beeslearn.models.Level
import fpl.md07.beeslearn.models.PartOfLevel
import fpl.md07.beeslearn.models.responseModel.QuestionResponseModel
import kotlinx.coroutines.launch
import kotlin.math.floor

class QuestionViewModel : ViewModel() {

    private val apiService = HttpRequest.getInstance()

    private val _levels = MutableLiveData<ArrayList<Level>>()
    val levels: LiveData<ArrayList<Level>> = _levels

    private val _partOfLevel = MutableLiveData<ArrayList<PartOfLevel>>()
    val partOfLevel: LiveData<ArrayList<PartOfLevel>> = _partOfLevel

    fun getRandomQuestions(
        level: Int, total: Int, onSuccess: (QuestionResponseModel) -> Unit, onError: (String) -> Unit
    ) {
        val firstHalf = floor(total.toFloat() * 0.6f)
        val secondHalf = total.toFloat() - firstHalf
        val excess = total.toFloat() - (firstHalf + floor(secondHalf/2)*2)

        val grammarAmount = (firstHalf + excess).toInt()
        val wordAmount = floor(secondHalf/2).toInt()
        val trueFalseAmount = floor(secondHalf/2).toInt()

        viewModelScope.launch {
            try {
                val grammarQuestions = apiService.getGrammarByAmount(level, grammarAmount).body()
                val trueFalseQuestions = apiService.getTrueFalseByAmount(level, trueFalseAmount).body()
                val words = apiService.getWordByAmount(level, wordAmount*4).body()

                onSuccess(
                    QuestionResponseModel(
                        words = words,
                        trueFalseQuestions = trueFalseQuestions,
                        grammarQuestions = grammarQuestions
                    )
                )

            }catch (e: Exception){
                println(e)
                onError(e.toString())
            }
        }
    }

    fun getLevels(){
        viewModelScope.launch {
            try {
                val response = apiService.getLevels()
                if(response.code() == 200){
                    _levels.postValue(response.body())
                }else{
                    _levels.postValue(ArrayList<Level>())
                }
            }catch (e: Exception){
                println(e)
            }
        }
    }

    fun getPartsOfLevel(level: Int){
        viewModelScope.launch {
            try {
                val response = apiService.getPartOfLevel(level)
                if(response.code() == 200){
                    _partOfLevel.postValue(response.body())
                }else{
                    _partOfLevel.postValue(ArrayList<PartOfLevel>())
                }
            }catch (e: Exception){
                println(e)
            }
        }
    }

}