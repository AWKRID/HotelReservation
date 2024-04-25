package org.example

fun main() {
    val firstHotel = Hotel("Minsu")
    val firstHotelManager = HotelReservationManager(firstHotel)
    firstHotelManager.runProgram()
}
