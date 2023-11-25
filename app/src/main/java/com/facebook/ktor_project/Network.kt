package com.facebook.ktor_project

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class Network {
    private val getRepository=GetRepository()
    suspend fun fetchData(): Post? {
        return try {
            val responseBody =  getRepository.getFromServer()
            Log.e("Network", responseBody.toString())
            val jsonParser = JsonParser()
            val jsonObject = jsonParser.parse(responseBody) as JsonObject
            val resultsArray = jsonObject.getAsJsonArray("results")
            // Assuming you want the first item from the results array
            val firstResult = resultsArray.get(0).asJsonObject

            val post = Gson().fromJson(firstResult, Post::class.java)
            post
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Network", "Exception: ${e.message}")
            null
        }
    }


}
