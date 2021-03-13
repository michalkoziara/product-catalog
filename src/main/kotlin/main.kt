import androidx.compose.desktop.AppWindow
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.lightColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import javax.swing.SwingUtilities.invokeLater

fun main() = invokeLater {
    val window = AppWindow(title = "Integracja Systemów - Michał Koziara")
    window.makeFullscreen()
    window.show {
        val computerRepository: ComputerRepository = FileComputerRepository()

        val colors = lightColors(
            primary = Color(0xFF03A9F4),
            primaryVariant = Color(0xFF039BE5),
            secondary = Color(0xFFFFC107),
            secondaryVariant = Color(0xFFC107)
        )

        MaterialTheme(
            colors = colors,
        ) {
            Column {
                var computers: List<Computer> by remember { mutableStateOf(emptyList()) }

                Row {
                    Button(
                        modifier = Modifier.padding(10.dp),
                        onClick = {
                            computers = computerRepository.computers

                            val printer = Printer()
                            printer.printComputers(computers)
                            println()
                            printer.printManufacturerStatistics(computers)
                        }) {
                        Text(
                            text = "Wczytaj dane z pliku TXT"
                        )
                    }
                    Button(
                        modifier = Modifier.padding(10.dp),
                        onClick = {
                            computers = computerRepository.computers

                            val printer = Printer()
                            printer.printComputers(computers)
                            println()
                            printer.printManufacturerStatistics(computers)
                        }) {
                        Text(
                            text = "Zapisze dane z pliku TXT"
                        )
                    }
                    Spacer(modifier = Modifier)
                    Button(
                        modifier = Modifier.padding(10.dp),
                        onClick = { window.close() },
                    ) {
                        Text(
                            text = "X"
                        )
                    }
                }
                Box(modifier = Modifier.padding(all = 10.dp)) {
                    Table(computers = computers)
                }
            }
        }
    }
}
