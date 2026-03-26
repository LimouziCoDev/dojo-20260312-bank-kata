import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import io.mockk.verifyOrder
import io.mockk.verifySequence
import kata.AccountService
import kata.Console
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AcceptanceTest {

    @MockK(relaxed = true)
    lateinit var console: Console

    @Test
    fun `should print statement with correct transactions`() {
        val accountService: AccountService = kata.AccountServiceImpl()

        // Arrange
        accountService.deposit(1000)
        accountService.deposit(2000)
        accountService.withdraw(500)


        // Act
        accountService.printStatement()

        // Assert
        verifyOrder {
            console.printLine("Date || Amount || Balance")
            console.printLine("14/01/2012 || -500 || 2500")
            console.printLine("13/01/2012 || 2000   || 3000")
            console.printLine("10/01/2012 || 1000   || 1000")
        }


    }
}