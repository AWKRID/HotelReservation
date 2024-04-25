package org.example

import org.example.Utils.Companion.isValidCheckInOutDates
import org.example.Utils.Companion.isValidDate

class HotelReservationManager(private val hotel: Hotel) {
    fun runProgram(){
        while(true){
            printMenu()
            try{
                getUserChoice()
            }catch(e:Exception){
                break
            }
        }
    }

    private fun getUserChoice() {
        val userChoice = readln()
        when (userChoice) {
            "1" -> makeReservation()
            "2" -> hotel.showReservations()
            "3" -> hotel.showSortedReservations()
            "4" -> throw Exception()
        }
    }


    private fun makeReservation() {
        val reservation = getReservationInfo()
        hotel.book(reservation)
        println("${hotel.getName()}호텔 예약이 완료되었습니다.")
    }

    private fun printMenu() {
        println("${hotel.getName()}호텔예약 프로그램입니다.")
        println("[메뉴]")
        println("1. 방 예약, 2. 예약 목록 출력, 3. 예약목록 정렬 출력, 4. 시스템 종료, 5. 금액 입출금 내역 확인, 6. 예약 변경/취소")
    }


    private fun getReservationInfo() :Reservation{
        val name = getName()

        while(true){
            val roomNumber = getRoomNumber()
            val checkInDate = getCheckInDate()
            val checkOutDate = getCheckOutDate(checkInDate)
            if(!hotel.isAvailabile(roomNumber, checkInDate, checkOutDate)){
                printDateIsNotAvailable()
                continue
            }
            return Reservation(name,roomNumber,checkInDate,checkOutDate)
        }
    }

    private fun getName(): String {
        println("예약자 분의 성함을 입력해주세요")
        return readln()
    }

    private fun getRoomNumber(): Int {
        while (true) {
            println("예약할 방 번호를 입력해주세요")
            try {
                val userInput = readln().toInt()
                if (userInput in 100..999) {
                    return userInput
                }
            } catch (e: NumberFormatException) {
                continue
            }
        }
    }

    private fun getCheckInDate(): String {
        while (true) {
            println("체크인 날짜를 입력해주세요. (예시 : 2024-04-23)")
            val userInput = readln()
            if (!Utils.isValidDateFormat(userInput)) {
                printWrongDateFormatMessage()
                continue
            }
            if (!isValidDate(userInput)) {
                printWrongCheckInDateMessage()
                continue
            }
            return userInput
        }
    }

    private fun getCheckOutDate(checkInDate: String): String {
        while (true) {
            println("체크아웃 날짜를 입력해주세요. (예시 : 2024-04-23)")
            val userInput = readln()
            if (!Utils.isValidDateFormat(userInput)) {
                printWrongDateFormatMessage()
                continue
            }

            if (!isValidCheckInOutDates(checkInDate, userInput)) {
                printWrongCheckOutDateMessage()
                continue
            }

            return userInput
        }
    }

    private fun printDateIsNotAvailable(){
        println("해당 날짜는 이미 예약이 되어있습니다.")
    }
    private fun printWrongDateFormatMessage() {
        println("yyyy-mm-dd 형태로 입력해주세요")
    }

    private fun printWrongCheckInDateMessage() {
        println("체크인은 지난 날짜를 선택할 수 없습니다.")
    }

    private fun printWrongCheckOutDateMessage() {
        println("체크인 날짜보다 이전이거나 같을 수 없습니다.")
    }
}