package com.colosoft.recomiendame.server.model

import java.io.Serializable

data class Opinion (
    var id: String? = null,
    var restaurant_id: String? = null,
    var restaurant_name: String? = null,
    var user_id: String? = null,
    var user_name: String? = null,
    var user_last_name: String? = null,
    var rating: Double? = null,
    var text: String? = null
):Serializable