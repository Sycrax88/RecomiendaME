package com.colosoft.recomiendame.server.model

import java.io.Serializable

data class Restaurant(
    var id: String? = null,
    var name: String? = null,
    var restaurantLocation: String? = null,
    var rating: Double? = null,
    var numRating: Int? = null,
    var urlMenu: String? = null,
    var urlPhoto: String? = null,
    var urlMap: String? = null
):Serializable
