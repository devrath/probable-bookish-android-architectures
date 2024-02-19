package com.istudio.catquote.model

import com.google.gson.annotations.SerializedName

data class Status (
    @SerializedName("verified"  ) var verified  : Boolean? = null,
    @SerializedName("sentCount" ) var sentCount : Int?     = null
)