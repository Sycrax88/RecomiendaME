package com.colosoft.recomiendame.server.model

import java.io.Serializable

data class User(
    var uid: String?= null,
    var name: String?= null,
    var lastName: String?= null,
    var phone: String?= null,
    var email: String?= null
): Serializable
