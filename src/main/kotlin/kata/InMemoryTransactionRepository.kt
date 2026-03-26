package kata

class InMemoryTransactionRepository(private val clock: Clock) : TransactionRepository {

    private val transactions = mutableListOf<Transaction>()

    override fun addDeposit(amount: Int) {
       transactions.add(Deposit(amount, clock.getCurrentDate()))
    }

    override fun addWithdrawal(amount: Int) {
        transactions.add(Withdrawal(amount, clock.getCurrentDate()))
    }

    override fun getTransactions(): List<Transaction> {
        return transactions
    }

}
