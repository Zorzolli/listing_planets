package com.example.listing_planets_compose.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Planets(val Name: String, val Description: String, val Image: Int) : Parcelable {
}