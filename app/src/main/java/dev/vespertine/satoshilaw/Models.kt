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
    var duration = if  ((startTime + endTime) >= 10) (startTime + endTime).toString()
                            else "0" + (startTime + endTime).toString()
}