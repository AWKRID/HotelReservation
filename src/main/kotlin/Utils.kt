package org.example

import java.time.LocalDate
import java.time.format.DateTimeParseException

class Utils {
    companion object {
        fun isValidDateFormat(dateStr: String) : Boolean{
            return try{
                LocalDate.parse(dateStr)
                true
            }catch (e: DateTimeParseException){
                 false
            }
        }
        fun isValidDate(dateStr : String) : Boolean{
            return LocalDate.parse(dateStr).isAfter(LocalDate.now().minusDays(1))
        }
        fun isValidCheckInOutDates(checkInDateStr : String, checkOutDateStr : String) : Boolean{
            return LocalDate.parse(checkOutDateStr).isAfter(LocalDate.parse(checkInDateStr))
        }
        fun isDateBetweenTwoDates(date : String, startDate: String, endDate: String): Boolean {
            return LocalDate.parse(date).isAfter(LocalDate.parse(startDate).minusDays(1)) &&
                    LocalDate.parse(date).isBefore(LocalDate.parse(endDate))
        }
    }

}