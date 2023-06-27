package com.exam.dispositivosmoviles.data.entities.marvel

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)