import androidx.compose.desktop.Window
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

fun main() = Window {
    val computerRepository: ComputerRepository = FileComputerRepository()
    val computers = computerRepository.computers

    val printer = Printer()
    printer.printComputers(computers)
    println()
    printer.printManufacturerStatistics(computers)

    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(onClick = {
            text = computers[0].gpu
        }) {
            Text(text)
        }
    }
}