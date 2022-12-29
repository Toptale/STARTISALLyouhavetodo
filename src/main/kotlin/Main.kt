
import data.models.Block
import data.models.Ledger
import data.models.Transaction
import data.models.Wallet

fun main(args: Array<String>) {
    println("+-------------------------------------------------------------------------------------------------------------------------------------------------------+")
    println("|\t########################################################## Launching N-Blockchain App... ##########################################################\t|")

    println("|\tCreating the ledger...\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
    val ledger = Ledger()
    println("|\tLedger created :D\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
    println("|\tGenesis block is :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
    println(Block.GENESIS.toString())

    println("|\tCreating wallets :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|")
    val wallets: ArrayList<Wallet> = arrayListOf()