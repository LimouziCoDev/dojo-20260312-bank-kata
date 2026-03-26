package kata

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate


@ExtendWith(MockKExtension::class)
class AccountServiceImplTest {

    @MockK(relaxed = true)
    lateinit var  statementPrinter: StatementPrinter

    @MockK(relaxed = true)
    lateinit var transactionRepository: TransactionRepository
    lateinit var accountService: AccountService

    @BeforeEach
    fun setUp() {
        accountService = AccountServiceImpl(transactionRepository, statementPrinter)
    }

    @Test
    fun `add deposit to transaction repository`() {
        val amount = 1345

        accountService.deposit(amount)

        verify {
            transactionRepository.addDeposit(amount)
        }
    }

    @Test
    fun `add withdrawal to transaction repository`() {
        val amount = 345

        accountService.withdraw(amount)

        verify {
            transactionRepository.addWithdrawal(amount)
        }
    }

    @Test
    fun `print statement`() {
        val transactions = listOf<Transaction>(
            Deposit(234, LocalDate.of(2019, 12, 30)),
        )

         every { transactionRepository.getTransactions() } returns transactions

        accountService.printStatement()

        verify {
            statementPrinter.print(transactions)
        }
    }
}
