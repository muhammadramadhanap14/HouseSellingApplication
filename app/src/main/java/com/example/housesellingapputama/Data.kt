package com.example.housesellingapputama

data class Listing(
    val id: Int,
    val project_name: String,
    val address: Address,
    val attributes: AttributesUtama,
    val photo: String,
    val category: String,
    val tenure: Int,
    val completed_at: String
)

data class Address(
    val district: String,
    val street_name: String
)

data class AttributesUtama(
    val area_size: Int,
    val bathrooms: Int,
    val bedrooms: Int,
    val price: Int,
)