import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import io.mockk.verifyOrder
import io.mockk.verifySequence
import kata.Console
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AcceptanceTest {

    @MockK
    lateinit var console: Console

    @Test
    fun name() {
        val accountService = kata.AccountServiceImpl(console)

        every {
            console.printLine(any())
        } just Runs

        // Arrange
        accountService.deposit(1000)
        accountService.deposit(2000)
        accountService.withdraw(500)


        // Act
        accountService.printStatement()

        // Assert
        verifyOrder {
            console.printLine("Date || Amount || Balance")
            console.printLine("14/1/2012 || -500 || 2500")
        }




    }
}