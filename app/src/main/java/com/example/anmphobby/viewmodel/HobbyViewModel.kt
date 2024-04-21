package com.example.anmphobby.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmphobby.model.Model
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HobbyViewModel (application: Application): AndroidViewModel(application) {
    val modelLD = MutableLiveData<ArrayList<Model>>()
    val modelLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        modelLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/Komik.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Model>>() { }.type
                val result = Gson().fromJson<List<Model>>(it, sType)
                modelLD.value = result as ArrayList<Model>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                modelLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}