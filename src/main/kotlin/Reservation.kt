package org.example

class Reservation {
    private var name: String = ""
    private var roomNumber: Int = 0
    private var checkInDate: String = ""
    private var checkOutDate: String = ""

    fun makeReservation() {
        setName()
        setRoomNumber()
        setCheckInDate()
        setCheckOutDate()
    }

    private fun setName() {
        println("예약자 분의 성함을 입력해주세요")
        name = readln()
        return
    }

    private fun setRoomNumber() {
        while (true) {
            println("예약할 방 번호를 입력해주세요")
            try {
                val userInput = readln().toInt()
                if (userInput in 100..999) {
                    roomNumber = userInput
                    break
                }
            } catch (e: NumberFormatException) {
                continue
            }
        }
        return
    }

    private fun setCheckInDate() {
        println("체크인 날짜를 입력해주세요. (예시 : 20240423)")
        val userInput = readln()
        checkInDate = userInput
    }

    private fun setCheckOutDate() {
        println("체크아웃 날짜를 입력해주세요.")
        val userInput = readln()
        checkOutDate = userInput
    }
    fun showInfo(){
        println("사용자: $name, 방번호: $roomNumber, 체크인: $checkInDate, 체크아웃: $checkOutDate")
    }
    fun getCheckInDate(): String {
        return checkInDate
    }

}