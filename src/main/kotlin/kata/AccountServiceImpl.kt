package kata


class AccountServiceImpl(private val transactionRepository: TransactionRepository, private val statementPrinter: StatementPrinter) : AccountService {
    override fun deposit(amount: Int) {
        transactionRepository.addDeposit(amount)
    }

    override fun withdraw(amount: Int) {
        transactionRepository.addWithdrawal(amount)
    }

    override fun printStatement() {
        val transactions = transactionRepository.getTransactions()
        statementPrinter.print(transactions)
    }

}
