package org.example

class Hotel {
    private var reservationList = mutableListOf<Reservation>()

    private fun book() {
        val reservation = Reservation()
        reservation.makeReservation()
        reservationList.add(reservation)
        println("호텔 예약이 완료되었습니다.")
    }
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
    private fun printMenu() {
        println("호텔예약 프로그램입니다.")
        println("[메뉴]")
        println("1. 방 예약, 2. 예약 목록 출력, 3. 예약목록 정렬 출력, 4. 시스템 종료, 5. 금액 입출금 내역 확인, 6. 예약 변경/취소")
    }

    private fun showReservations() {
        for (idx in reservationList.indices) {
            print("${idx + 1}. ")
            reservationList[idx].showInfo()
        }
    }

    private fun showSortedReservations() {
        val sortedReservationList = reservationList
        sortedReservationList.sortBy { it.getCheckInDate() }
        for (idx in sortedReservationList.indices) {
            print("${idx + 1}. ")
            sortedReservationList[idx].showInfo()
        }
    }

    private fun getUserChoice() {
        val userChoice = readln()
        when (userChoice) {
            "1" -> book()
            "2" -> showReservations()
            "3" -> showSortedReservations()
            "4" -> throw Exception()
        }
    }
}