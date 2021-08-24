package com.example.demoapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class navDemo(var age: Int, var surname: String) : Parcelable
