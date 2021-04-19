import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import database.ProductDatabase

import java.io.File

fun ProductDatabaseDriver(): SqlDriver {
    val databasePath = File(System.getProperty("user.dir"), "ProductDatabase.db")

    return JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}").also { driver ->
        ProductDatabase.Schema.create(driver)
    }
}
