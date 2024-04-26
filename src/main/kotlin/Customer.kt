package org.example

class Customer(
    private val name: String,
    private var deposit : Int = 1000000,
    private var depositRecord : MutableList<Pair<String,Int>> = mutableListOf(Pair("초기 금액", 1000000))
) {
    fun getName() : String{
        return name
    }
    fun showDepositRecord() {
        depositRecord.forEach(){println("${it.first} : ${it.second} 원")}
        println("남은 금액 : $deposit")
    }
    fun getRefund(amount:Int){
        depositRecord.add(Pair("환불", amount))
        plusDeposit(amount)
    }
    fun book(amount: Int){
        depositRecord.add(Pair("예약금", amount))
        minusDeposit(amount)
    }
    private fun plusDeposit(amount : Int){
        deposit+=amount
    }
    private fun minusDeposit(amount : Int){
        deposit-=amount
    }

}