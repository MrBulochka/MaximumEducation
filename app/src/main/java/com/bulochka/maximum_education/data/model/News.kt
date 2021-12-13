package com.bulochka.maximum_education.data.model

import java.io.Serializable

data class News(
    val title: String,
    val description: String,
    val image: String
): Serializable