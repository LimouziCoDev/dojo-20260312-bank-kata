package kata

interface TransactionRepository {
    fun addDeposit(amount: Int)
    fun addWithdrawal(amount: Int)
    fun getTransactions() : List<Transaction>

}
