package com.colosoft.recomiendame.server.model

import java.io.Serializable

data class Restaurant(
    var id: String? = null,
    var name: String? = null,
    var restaurantLocation: String? = null,
    var rating: Double? = null,
    var urlPhoto: String? = null
):Serializable
