import com.squareup.sqldelight.db.SqlDriver
import database.ProductDatabase

object ProductDatabaseSingleton {
    private var driverRef: SqlDriver? = null
    private var databaseRef: ProductDatabase? = null

    @JvmStatic
    val instance: ProductDatabase
        get() = databaseRef!!

    @JvmStatic
    fun dbSetup() {
        val driver = ProductDatabaseDriver()
        val database = ProductDatabase(driver)
        driverRef = driver
        databaseRef = database
    }

    @JvmStatic
    val ready: Boolean
        get() = driverRef != null

    internal fun dbClear() {
        driverRef!!.close()
        databaseRef = null
        driverRef = null
    }
}