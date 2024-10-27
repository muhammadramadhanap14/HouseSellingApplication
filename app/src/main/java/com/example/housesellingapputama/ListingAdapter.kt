package com.example.housesellingapputama

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.Locale

class ListingAdapter(
    private val listings: List<Listing>,
    private val onItemClickListener: (Listing) -> Unit // Menggunakan lambda untuk item klik
) : RecyclerView.Adapter<ListingAdapter.ListingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_result, parent, false)
        return ListingViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        val listing = listings[position]
        holder.bind(listing)
    }

    override fun getItemCount() = listings.size

    class ListingViewHolder(
        view: View,
        private val onItemClickListener: (Listing) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val imgPhoto: ImageView = view.findViewById(R.id.imgPhoto)
        private val tvProjectName: TextView = view.findViewById(R.id.tvProjectName)
        private val tvAddress: TextView = view.findViewById(R.id.tvAddress)
        private val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        private val tvDetail: TextView = view.findViewById(R.id.tvDetails)
        private val tvFurnish: TextView = view.findViewById(R.id.tvFurnish)
        private val btn: CardView = view.findViewById(R.id.btn)

        fun bind(listing: Listing) {
            // Menggunakan Glide untuk menampilkan gambar atau placeholder jika null
            Glide.with(itemView.context)
                .load(listing.photo ?: "")
                .into(imgPhoto)

            // Menampilkan teks atau nilai default jika null
            tvProjectName.text = listing.project_name ?: "Unknown Project"
            val streetName = listing.address.street_name ?: "Unknown Street"
            val district = listing.address.district ?: "Unknown District"
            tvAddress.text = "$streetName . $district"

            val cat = listing.category ?: "Unknown Project"
            val com = listing.completed_at ?: "Unknown Project"
            val ten = (listing.tenure ?: 0).toString()
            tvDetail.text = "$cat . $com . $ten"

            // Memformat dan menampilkan furnitur dengan format koma untuk area
            val bed = listing.attributes.bedrooms ?: 0
            val bath = listing.attributes.bathrooms ?: 0
            val area = listing.attributes.area_size ?: 0
            val formattedArea = NumberFormat.getNumberInstance(Locale.US).format(area)

            tvFurnish.text = "$bed Beds . $bath Baths . $formattedArea sqft"

            val priced = listing.attributes.price ?: 0
            val formattedPrice = NumberFormat.getNumberInstance(Locale.US).format(priced)
            // Menampilkan harga atau nilai default jika null
            tvPrice.text = "$${formattedPrice}/mo"

            // Menangani klik item
            btn.setOnClickListener {
                onItemClickListener(listing)
            }
        }
    }
}
