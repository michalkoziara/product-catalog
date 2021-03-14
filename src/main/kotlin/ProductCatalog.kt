import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun ProductCatalog(computerRepository: ComputerRepository) {
    val validator = Validator()

    var computers: List<Computer> by remember { mutableStateOf(emptyList()) }
    val numberOfComputersByManufacturer: Map<String, Int> =
        remember(computers) { computeManufacturerStatistics(computers) }
    val isSavingAvailable: Boolean = remember(computers) { validator.validateComputers(computers) }

    Column {
        Row {
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    computers = computerRepository.computers
                }) {
                Text(
                    text = "Importuj z pliku"
                )
            }
            Button(
                modifier = Modifier.padding(10.dp),
                enabled = isSavingAvailable,
                onClick = {
                    computerRepository.saveComputers(computers)
                }) {
                Text(
                    text = "Eksportuj do pliku"
                )
            }
        }
        ComputerTable(
            computers = computers,
            onComputersChange = { updatedComputers: List<Computer> ->
                computers = updatedComputers
            },
            modifier = Modifier
                .padding(all = 10.dp)
                .weight(1f),
        )
        Row(modifier = Modifier.weight(0.5f)) {
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    val changedComputers = computers.toMutableList()
                    changedComputers.add(ComputerBuilder().createComputer())
                    computers = changedComputers
                }) {
                Text(
                    text = "Dodaj nowy rekord"
                )
            }
            Spacer(modifier = Modifier.weight(0.6f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.4f)
            ) {
                Text(text = "Statystyki producentów")
                ManufacturerTable(
                    numberOfComputersByManufacturer = numberOfComputersByManufacturer,
                    modifier = Modifier
                        .padding(all = 10.dp)
                )
            }
        }
    }
}

fun computeManufacturerStatistics(computers: List<Computer>): Map<String, Int> {
    val numberOfComputersByManufacturer: MutableMap<String, Int> = TreeMap()
    for (computer in computers) {
        val manufacturerName = computer.manufacturerName
        if (!numberOfComputersByManufacturer.containsKey(manufacturerName)) {
            numberOfComputersByManufacturer[manufacturerName] = 1
        } else {
            numberOfComputersByManufacturer.replace(
                manufacturerName,
                numberOfComputersByManufacturer[manufacturerName]!! + 1
            )
        }
    }

    return numberOfComputersByManufacturer
}