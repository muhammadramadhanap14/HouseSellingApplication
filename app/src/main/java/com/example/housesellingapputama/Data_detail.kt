data class Attributes(
    val area_size: Int,
    val bathrooms: Int,
    val bedrooms: Int,
    val price: Int
)

data class PropertyDetail(
    val label: String,
    val text: String
//    val pricePerSqft: String,
//    val location: String,
//    val floorLevel: String,
//    val furnishing: String,
//    val facing: String,
//    val view: String,
//    val built: String,
//    val tenure: String,
//    val type: String,
//    val lastUpdate: String
)

data class ListingDetail(
    val id: Int,
    val project_name: String,
    val address: AddressDetail,
    val attributes: Attributes,
    val description: String,
    val photo: String,
    val property_details: List<PropertyDetail>
)

data class AddressDetail(
    val title: String,
    val subtitle: String,
    val map_coordinates: MapCoordinates
)

data class MapCoordinates(
    val lat: Double,
    val lng: Double
) {
    // Tambahkan fungsi untuk mengembalikan koordinat dalam format string
    override fun toString(): String {
        return "Latitude: $lat, Longitude: $lng"
    }
}