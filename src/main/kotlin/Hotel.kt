package org.example

import java.time.LocalDate

class Hotel(private val name: String) {
    private var reservationList = mutableListOf<Reservation>()
    fun getName(): String {
        return name
    }

    fun book(reservation: Reservation) {
        reservationList.add(reservation)
    }

    fun showReservations() {
        for (idx in reservationList.indices) {
            print("${idx + 1}. ")
            reservationList[idx].showInfo()
        }
    }

    fun showSortedReservations() {
        val sortedReservationList = reservationList
        sortedReservationList.sortBy { it.getCheckInDate() }
        for (idx in sortedReservationList.indices) {
            print("${idx + 1}. ")
            sortedReservationList[idx].showInfo()
        }
    }


    fun isAvailabile(roomNumber: Int, checkInDate: String, checkOutDate: String): Boolean {
        val filteredReservationList = reservationList.filter {
            it.getRoomNumber() == roomNumber && (isDateBetweenTwoDates(checkInDate, it.getCheckInDate(),it.getCheckOutDate()) ||
                    isDateBetweenTwoDates(checkOutDate, it.getCheckInDate(),it.getCheckOutDate()))

        }
        return filteredReservationList.isEmpty()
    }

    private fun isDateBetweenTwoDates(date : String, startDate: String, endDate: String): Boolean {
        return LocalDate.parse(date).isAfter(LocalDate.parse(startDate).minusDays(1)) &&
                LocalDate.parse(date).isBefore(LocalDate.parse(endDate))
    }

}