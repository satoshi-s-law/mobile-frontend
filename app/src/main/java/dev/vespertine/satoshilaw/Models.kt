package dev.vespertine.satoshilaw


data class Project(
    val id: Int,
    val projectName: String,
    val clientName: String,
    val startTime : Int,
    val endTime : Int,
    val rate : Double,
    val tasks : MutableList<String> = mutableListOf()
) {
    val duration = startTime + endTime
}