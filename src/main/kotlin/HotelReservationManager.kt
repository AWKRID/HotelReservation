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
            "6" -> modifyReservationByName()
        }
    }
    private fun modifyReservationByName(){
        println("조회하실 사용자 이름을 입력하세요")
        val name = readln()
        val reservationList : List<Reservation>
        val reservationNumber : Int
        val selectedReservation : Reservation
        try{
            reservationList = hotel.showReservationByName(name)
        }catch(e:Exception){
            println("사용자 이름으로 예약된 내역을 찾을 수 없습니다.")
            return
        }
        println("변경을 원하시는 예약 번호를 입력하세요.")
        try{
            reservationNumber = readln().toInt()
            selectedReservation = reservationList[reservationNumber-1]
        }catch(e:Exception){
            println("잘못된 입력입니다. 초기화면으로 돌아갑니다.")
            return
        }
        println("해당 예약을 어떻게 하시겠어요? 1. 변경, 2. 취소 ")
        println("메인메뉴로 돌아가고 싶으면 이외의 키를 눌러주세요.")
        val userChoice = readln()
        when(userChoice) {
            "1" -> modifyReservation(name, selectedReservation)
            "2" -> cancelReservation(selectedReservation)
            else -> return
        }
    }
    private fun cancelReservation(reservation: Reservation){
        hotel.cancelReservation(reservation)
        println("예약이 취소되었습니다.")
    }
    private fun modifyReservation(name : String, reservation: Reservation){
        hotel.cancelReservation(reservation)
        while(true){
            val roomNumber = getRoomNumber()
            val checkInDate = getCheckInDate()
            val checkOutDate = getCheckOutDate(checkInDate)
            if(!hotel.isAvailable(roomNumber, checkInDate, checkOutDate)){
                printDateIsNotAvailable()
                continue
            }
            hotel.book(Reservation(name,roomNumber,checkInDate,checkOutDate))
            println("예약 변경이 완료되었습니다.")
            return
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
            if(!hotel.isAvailable(roomNumber, checkInDate, checkOutDate)){
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