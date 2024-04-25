package org.example

class Reservation(
    private var name: String,
    private var roomNumber: Int,
    private var checkInDate: String,
    private var checkOutDate: String
) {
    fun showInfo() {
        println("사용자: $name, 방번호: $roomNumber, 체크인: $checkInDate, 체크아웃: $checkOutDate")
    }

    fun getCheckInDate(): String {
        return checkInDate
    }
    fun getCheckOutDate(): String {
        return checkOutDate
    }

    fun getRoomNumber(): Int {
        return roomNumber
    }
}