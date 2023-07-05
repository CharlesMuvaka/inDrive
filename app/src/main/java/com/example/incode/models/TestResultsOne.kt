package com.example.incode.models

import java.io.Serializable

data class TestResultsOne(
    val html_attributions: List<Any>,
    val next_page_token: String,
    val results: List<PlaceResult>,
    val status: String
): Serializable

data class PlaceResult(
    val business_status: String,
    val formatted_address: String,
    val geometry: Geometry,
    val icon: String,
    val icon_background_color: String,
    val icon_mask_base_uri: String,
    val name: String,
    val opening_hours: OpeningHours,
    val photos: List<Photo>,
    val place_id: String,
    val plus_code: PlusCode,
    val rating: Double,
    val reference: String,
    val types: List<String>,
    val user_ratings_total: Int
): Serializable

data class Geometry(
    val location: Location,
    val viewport: Viewport
): Serializable

data class OpeningHours(
    val open_now: Boolean
): Serializable

data class Photo(
    val height: Int,
    val html_attributions: List<String>,
    val photo_reference: String,
    val width: Int
): Serializable

data class PlusCode(
    val compound_code: String,
    val global_code: String
): Serializable

data class Location(
    val lat: Double,
    val lng: Double
): Serializable

data class Viewport(
    val northeast: Northeast,
    val southwest: Southwest
): Serializable

