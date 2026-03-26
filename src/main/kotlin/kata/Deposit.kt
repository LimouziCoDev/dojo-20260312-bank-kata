package kata

import java.time.LocalDate

data class Deposit(val amount: Int, val date: LocalDate) : Transaction
