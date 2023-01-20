package com.mihir.sysmatask.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mihir.sysmatask.model.ClassItem
import com.mihir.sysmatask.model.Database
import com.mihir.sysmatask.network.NetworkHelper
import com.mihir.sysmatask.network.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

class ViewModel(application: Application): AndroidViewModel(application) {

    var dao = Database.getDatabase(application).Dao()

    val readAllData: LiveData<List<ClassItem>> = dao.getItems()

    private val gson: Gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    private val service by lazy { retrofit.create(Service::class.java) }

    private val context = getApplication<Application>()

    var apiStatus = MutableLiveData<String>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (NetworkHelper(context).isNetworkConnected()){
                val response = service.getItemsFromNetwork()

                if (response.isSuccessful){
                    response.body()?.let { dao.addItems(it) }
                }else{
                    withContext(Dispatchers.Main) {
                        apiStatus.value = "Oops something went wrong!"
                    }
                }
            }else {
                withContext(Dispatchers.Main){
                    apiStatus.value =  "Please Check your network"
                }
            }
        }
    }
}