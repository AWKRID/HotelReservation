package org.example

import org.example.Utils.Companion.isValidCheckInOutDates
import org.example.Utils.Companion.isValidDate

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
        while (true) {
            println("체크인 날짜를 입력해주세요. (예시 : 2024-04-23)")
            val userInput = readln()
            if(!Utils.isValidDateFormat(userInput)){
                printWrongDateFormatMessage()
                continue
            }
            if(!isValidDate(userInput)){
                printWrongCheckInDateMessage()
                continue
            }
            checkInDate = userInput
            break
        }
    }

    private fun setCheckOutDate() {
        while (true) {
            println("체크아웃 날짜를 입력해주세요. (예시 : 2024-04-23)")
            val userInput = readln()
            if(!Utils.isValidDateFormat(userInput)){
                printWrongDateFormatMessage()
                continue
            }

            if(!isValidCheckInOutDates(checkInDate, userInput)){
                printWrongCheckOutDateMessage()
                continue
            }

            checkOutDate = userInput
            break
        }
    }
//    private fun isValidCheckInDate(): Boolean {
//        val currentDate =
//        return currentDate <= checkInDate
//    }
//    private fun isValidCheckOutDate(): Boolean {
//        return checkOutDate > checkInDate
//    }
    private fun printWrongDateFormatMessage(){
        println("2024-04-23 형태로 입력해주세요")
    }
    private fun printWrongCheckInDateMessage(){
        println("체크인은 지난 날짜를 선택할 수 없습니다.")
    }
    private fun printWrongCheckOutDateMessage(){
        println("체크인 날짜보다 이전이거나 같을 수 없습니다.")
    }
    fun showInfo() {
        println("사용자: $name, 방번호: $roomNumber, 체크인: $checkInDate, 체크아웃: $checkOutDate")
    }

    fun getCheckInDate(): String {
        return checkInDate
    }

}