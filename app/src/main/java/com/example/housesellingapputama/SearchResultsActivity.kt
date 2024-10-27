package com.example.housesellingapputama

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@AndroidEntryPoint
class SearchResultsActivity : AppCompatActivity() {

    private val propertyList: MutableList<Listing> = mutableListOf()
    private lateinit var adapter: ListingAdapter

    // Inject ApiService instance using Dagger Hilt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results) // Menggunakan layout tanpa binding

        // Set up RecyclerView
        setupRecyclerView()

        // Fetch property data
        fetchPropertyData()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvListings) // Akses langsung RecyclerView
        adapter = ListingAdapter(propertyList) { listing ->
            val intent = Intent(this, ListingDetailActivity::class.java)
            intent.putExtra("apartment_id", listing.id)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun fetchPropertyData() {
        val call = apiService.getSearchResults() // Memanggil API
        call.enqueue(object : Callback<List<Listing>> {
            override fun onResponse(call: Call<List<Listing>>, response: Response<List<Listing>>) {
                if (response.isSuccessful) {
                    val propertyData = response.body()
                    propertyData?.let {
                        // Update property list with new data
                        propertyList.clear()
                        propertyList.addAll(it)
                        adapter.notifyDataSetChanged()

                        // Display success message
                        Toast.makeText(this@SearchResultsActivity, "Data berhasil ditambahkan: ${propertyList.size}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("API Error", "Response unsuccessful")
                    Toast.makeText(this@SearchResultsActivity, "Response unsuccessful", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Listing>>, t: Throwable) {
                Log.e("API Error", "Failed to fetch data: ${t.message}")
                Toast.makeText(this@SearchResultsActivity, "Gagal memuat data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
