package com.example.housesellingapputama

import ListingDetail
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.Locale

@AndroidEntryPoint
class ListingDetailActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var projectName: TextView
    private lateinit var projectmainLocation: TextView
    private lateinit var projectLocation: TextView
    private lateinit var projectCoordinates: TextView
    private lateinit var propertyPrice: TextView
    private lateinit var propertyAttributes: LinearLayout
    private lateinit var bed: TextView
    private lateinit var bath: TextView
    private lateinit var area: TextView
    private lateinit var propertyDetailPricePerSqft: TextView
    private lateinit var propertyDetailFloorLevel: TextView
    private lateinit var propertyDetailFurnishing: TextView
    private lateinit var propertyDetailFacing: TextView
//    private lateinit var propertyView: TextView
    private lateinit var propertyBuilt: TextView
    private lateinit var propertyTenure: TextView
    private lateinit var propertyType: TextView
    private lateinit var propertyLastUpdate: TextView
    private lateinit var propertyDescription: TextView
    private lateinit var backButton: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_detail)

        imageView = findViewById(R.id.propertyImage)
        projectName = findViewById(R.id.propertyName)
        projectmainLocation = findViewById(R.id.propertymainLocation)
        projectLocation = findViewById(R.id.propertyLocation)
        projectCoordinates = findViewById(R.id.propertyCoordinates)
        propertyPrice = findViewById(R.id.propertyPrice)
        propertyAttributes = findViewById(R.id.propertyAttributes)
        bed = findViewById(R.id.bed)
        bath = findViewById(R.id.bath)
        area = findViewById(R.id.area)
        propertyDetailPricePerSqft = findViewById(R.id.propertyDetailPricePerSqft)
        propertyDetailFloorLevel = findViewById(R.id.propertyDetailFloorLevel)
        propertyDetailFurnishing = findViewById(R.id.propertyDetailFurnishing)
        propertyDetailFacing = findViewById(R.id.propertyDetailFacing)
//        propertyView = findViewById(R.id.propertyView)
        propertyBuilt = findViewById(R.id.propertyBuilt)
        propertyTenure = findViewById(R.id.propertyTenure)
        propertyType = findViewById(R.id.propertyType)
        propertyLastUpdate = findViewById(R.id.propertyLastUpdate)
        propertyDescription = findViewById(R.id.propertyDescription)
        backButton = findViewById(R.id.backButton)

        val listingId = intent.getIntExtra("apartment_id", -1)

        if (listingId == -1) {
            Toast.makeText(this, "Invalid listing ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        fetchPropertyData(listingId)

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun fetchPropertyData(listingId: Int) {
        val call = apiService.getListingDetail(listingId)
        call.enqueue(object : Callback<ListingDetail> {
            override fun onResponse(call: Call<ListingDetail>, response: Response<ListingDetail>) {
                Log.d("API Response", response.raw().toString())
                if (response.isSuccessful) {
                    val propertyData = response.body()
                    Log.d("API Data", propertyData.toString())
                    propertyData?.let {
                        displayPropertyDetails(it)
                        Toast.makeText(this@ListingDetailActivity, "Data berhasil dimuat", Toast.LENGTH_SHORT).show()
                    } ?: run {
                        Log.e("API Error", "Response body is null")
                        Toast.makeText(this@ListingDetailActivity, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("API Error", "Response unsuccessful: ${response.message()}")
                    Toast.makeText(this@ListingDetailActivity, "Response unsuccessful", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ListingDetail>, t: Throwable) {
                Log.e("API Error", "Failed to fetch data: ${t.message}")
                Toast.makeText(this@ListingDetailActivity, "Gagal memuat data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayPropertyDetails(property: ListingDetail) {

        val priced = property.attributes.price ?: 0
        val formattedPrice = NumberFormat.getNumberInstance(Locale.US).format(priced)

        val areas = property.attributes.area_size ?: 0
        val formattedArea = NumberFormat.getNumberInstance(Locale.US).format(areas)

        val propertyCoordinates: TextView = findViewById(R.id.propertyCoordinates)
        propertyCoordinates.setOnClickListener {
            // Tindakan yang ingin dilakukan saat TextView diklik
            // Misalnya, Anda bisa membuka peta atau tampilan baru
            val lat = property.address.map_coordinates.lat
            val lng = property.address.map_coordinates.lng

            // Contoh: Menggunakan Intent untuk membuka peta
            val gmmIntentUri = Uri.parse("geo:$lat,$lng?q=$lat,$lng(Label)")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps") // Mengarahkan ke aplikasi Google Maps
            startActivity(mapIntent)
        }

        projectName.text = property.project_name
        projectmainLocation.text = property.address.title
        projectLocation.text = property.address.subtitle
        propertyPrice.text = "$${formattedPrice}"
        bed.text = "${property.attributes.bedrooms} Beds"
        bath.text = "${property.attributes.bathrooms} Baths"
        area.text = "${formattedArea} sqft"

        propertyDetailPricePerSqft.text = "Price/sqft                        ${property.property_details[0].text}"
        propertyDetailFloorLevel.text = "Floor Level                       ${property.property_details[1].text}"
        propertyDetailFurnishing.text = "Furnishing                        ${property.property_details[2].text}"
        propertyDetailFacing.text = "Facing                               ${property.property_details[3].text}"
        propertyBuilt.text = "Built year (TOP)               ${property.property_details[4].text}"
        propertyTenure.text = "Tenure                               ${property.property_details[5].text}"
        propertyType.text = "Property type                   ${property.property_details[6].text}"
        propertyLastUpdate.text = "Last Updated                   ${property.property_details[7].text}"


        propertyDescription.text = property.description

        Glide.with(this)
            .load(property.photo)
            .into(imageView)
    }
}