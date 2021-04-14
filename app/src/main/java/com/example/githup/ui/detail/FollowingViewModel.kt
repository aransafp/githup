package com.example.githup.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githup.api.ApiConfig
import com.example.githup.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    companion object {
        private val TAG = FollowingViewModel::class.java.simpleName
    }

    private val listFollowing = MutableLiveData<ArrayList<User>>()

    private val loadingStatus = MutableLiveData<Boolean>()

    fun setListFollowing(username: String) {
        val client = ApiConfig.getApiService().getFollowing(username)

        client.enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                loadingStatus.value = false
                if (response.isSuccessful) {
                    listFollowing.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                loadingStatus.value = false
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getListFollowing(): LiveData<ArrayList<User>> {
        return listFollowing
    }

    fun isLoading(): LiveData<Boolean> = loadingStatus

}