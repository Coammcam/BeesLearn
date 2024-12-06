package fpl.md07.beeslearn.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.GlobalVariable.UserSession
import fpl.md07.beeslearn.api.ApiService
import fpl.md07.beeslearn.api.HttpRequest
import fpl.md07.beeslearn.models.CurrencyModel
import kotlinx.coroutines.launch

class UserDataViewModel: ViewModel() {

    private val api = HttpRequest.getInstance()

    private val _currencyData = MutableLiveData<CurrencyModel?>()
    val currencyData: LiveData<CurrencyModel?> = _currencyData

    fun getCurrencyData(){
        viewModelScope.launch {
            try {
                val currencyData = api.getCurrency(UserSession.currentUser!!.email).body()
                _currencyData.postValue(currencyData)
            }catch (e: Exception){
                println(e)
            }
        }
    }

    fun updateCurrencyData(newData: CurrencyModel){
        viewModelScope.launch {
            try {
                val newCurrencyData = api.updateCurrency(UserSession.currentUser!!.email, newData).body()
                _currencyData.postValue(newCurrencyData)
            }catch (e: Exception){
                println(e)
            }
        }
    }

}