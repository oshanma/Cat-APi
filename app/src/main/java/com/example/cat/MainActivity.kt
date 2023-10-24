package com.example.cat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var catsList: MutableList<CatAdapter.CatItem>
    private lateinit var recyclerViewCats: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewCats = findViewById<RecyclerView>(R.id.cats_recycler_view)
        catsList = mutableListOf()

        getCatData()
    }

    private fun getCatData() {
        val client = AsyncHttpClient()

        client["https://api.thecatapi.com/v1/images/search?limit=20", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val catImageArray = json.jsonArray
                for (i in 0 until catImageArray.length()) {
                    val catObject = catImageArray.getJSONObject(i)
                    val imageUrl = catObject.getString("url")
                    val name = "Cat Name" // Replace with actual cat name data
                    val description = "Cat Description" // Replace with actual cat description data
                    val catItem = CatAdapter.CatItem(imageUrl, name, description)
                    catsList.add(catItem)
                }

                val catAdapter = CatAdapter(catsList)
                recyclerViewCats.adapter = catAdapter
                recyclerViewCats.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerViewCats.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Cat Error", errorResponse)
            }
        }]
    }
}
