package com.example.userinterface

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
        var email: String,
        var firstName: String,
        var lastName: String,

): Parcelable