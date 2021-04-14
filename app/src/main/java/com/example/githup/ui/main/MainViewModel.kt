package com.example.githup.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githup.api.ApiConfig
import com.example.githup.models.User
import com.example.githup.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

    private val listUsers = MutableLiveData<ArrayList<User>>()

    private val loadingStatus = MutableLiveData<Boolean>()

    private val errorMessege = MutableLiveData<String>()

    fun setListUsers(username: String) {
        loadingStatus.value = true

        val client = ApiConfig.getApiService().getSearchUsers(username)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.d(TAG, "onResponse: ")
                loadingStatus.value = false
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: isSuccessfull")
                    listUsers.postValue(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                loadingStatus.value = false
                errorMessege.value = t.message
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })

    }

    fun getListUsers(): LiveData<ArrayList<User>> {
        return listUsers
    }

    fun isLoading(): LiveData<Boolean> = loadingStatus

    fun getErrorMessage(): LiveData<String> = errorMessege

}