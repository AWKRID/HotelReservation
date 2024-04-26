package org.example

class Reservation(
    private var customer: Customer,
    private var roomNumber: Int,
    private var checkInDate: String,
    private var checkOutDate: String,
    private var deposit : Int,
) {
    fun showInfo() {
        println("사용자: ${customer.getName()}, 방번호: $roomNumber, 체크인: $checkInDate, 체크아웃: $checkOutDate")
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

    fun getCustomer(): Customer {
        return customer
    }
    fun getDeposit(): Int {
        return deposit
    }
}