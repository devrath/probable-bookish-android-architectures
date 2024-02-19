package com.istudio.catquote.model

import com.google.gson.annotations.SerializedName

data class AnimalResponse (
    @SerializedName("status"    ) var status    : Status?  = Status(),
    @SerializedName("_id"       ) var Id        : String?  = null,
    @SerializedName("user"      ) var user      : String?  = null,
    @SerializedName("text"      ) var text      : String?  = null,
    @SerializedName("__v"       ) var _v        : Int?     = null,
    @SerializedName("source"    ) var source    : String?  = null,
    @SerializedName("updatedAt" ) var updatedAt : String?  = null,
    @SerializedName("type"      ) var type      : String?  = null,
    @SerializedName("createdAt" ) var createdAt : String?  = null,
    @SerializedName("deleted"   ) var deleted   : Boolean? = null,
    @SerializedName("used"      ) var used      : Boolean? = null
)