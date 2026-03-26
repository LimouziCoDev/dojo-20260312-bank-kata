package kata

import java.time.LocalDate

class ClockImpl : Clock {
    override fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }

}