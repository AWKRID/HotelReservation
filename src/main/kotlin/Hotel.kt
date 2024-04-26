package org.example

class Hotel(private val name: String) {
    private var reservationList = mutableListOf<Reservation>()
    private var customerList = mutableListOf<Customer>()
    fun getName(): String {
        return name
    }
    fun getCustomer(name: String) : Customer{
        val filteredCustomerList = customerList.filter { it.getName() == name }
        if (filteredCustomerList.isNotEmpty()){
            return filteredCustomerList.first()
        }
        val newCustomer = Customer(name)
        customerList.add(newCustomer)
        return newCustomer
    }
    fun book(reservation: Reservation) {
        reservation.getCustomer().book(reservation.getDeposit())
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

    fun showReservationByName(name: String): List<Reservation> {
        val filteredReservationList = reservationList.filter { it.getCustomer().getName() == name }.toList()
        if (filteredReservationList.isEmpty()) throw Exception()
        println("$name 님이 예약한 목록입니다.")
        for (idx in filteredReservationList.indices) {
            print("${idx + 1}. ")
            filteredReservationList[idx].showInfo()
        }
        return filteredReservationList
    }
    fun getCustomerWithException(name : String) : Customer {
        if(isCustomer(name)) throw Exception()
        return getCustomer(name)
    }

    private fun isCustomer(name : String) : Boolean{
        return customerList.none {it.getName() == name}
    }


    fun isAvailable(roomNumber: Int, checkInDate: String, checkOutDate: String): Boolean {
        val filteredReservationList = reservationList.filter {
            it.getRoomNumber() == roomNumber && (Utils.isDateBetweenTwoDates(
                checkInDate,
                it.getCheckInDate(),
                it.getCheckOutDate()
            ) ||
                    Utils.isDateBetweenTwoDates(checkOutDate, it.getCheckInDate(), it.getCheckOutDate()))

        }
        return filteredReservationList.isEmpty()
    }

    fun cancelReservation(reservation: Reservation) {
        reservation.getCustomer().getRefund(reservation.getDeposit())
        reservationList.remove(reservation)
    }


}