package com.colosoft.recomiendame.server.model

import java.io.Serializable

data class Mensaje (
    var id: String? = null,
    var clave: Int? = null,
    var mensaje_cifrado: String? = null,
    var user_id: String? = null,
    var user_name: String? = null,
    var user_last_name: String? = null
):Serializable