package kata

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate
import kotlin.test.assertContains

@ExtendWith(MockKExtension::class)
class InMemoryTransactionRepositoryTest {

    @MockK
    lateinit var clock : Clock
    lateinit var repository: TransactionRepository

    @BeforeEach
    fun setUp() {
        repository = InMemoryTransactionRepository(clock)
    }

    @Test
    fun `add deposit`() {
        val amount = 1345
        val date = LocalDate.of(2021, 1, 1)

        every { clock.getCurrentDate() } returns date

        repository.addDeposit(amount)

        assertContains(repository.getTransactions(), Deposit(amount, date))
    }

    @Test
    fun `add withdrawal`() {
        val amount = 1345
        val date = LocalDate.of(2021, 1, 1)

        every { clock.getCurrentDate() } returns date

        repository.addWithdrawal(amount)

        assertContains(repository.getTransactions(), Withdrawal(amount, date))
    }
}