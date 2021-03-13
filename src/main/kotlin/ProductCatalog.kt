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
    var computers: List<Computer> by remember { mutableStateOf(emptyList()) }
    val numberOfComputersByManufacturer: Map<String, Int> =
        remember(computers) { computeManufacturerStatistics(computers) }
    var isSavingAvailable: Boolean by remember { mutableStateOf(true) }

    Column {
        Row {
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    computers = computerRepository.computers
                }) {
                Text(
                    text = "Wczytaj dane z pliku TXT"
                )
            }
            Button(
                modifier = Modifier.padding(10.dp),
                enabled = isSavingAvailable,
                onClick = {
                    computers = computerRepository.computers
                }) {
                Text(
                    text = "Zapisz dane do pliku TXT"
                )
            }
        }
        ComputerTable(
            computers = computers,
            onComputersChange = { updatedComputers: List<Computer>, isDataValid: Boolean ->
                computers = updatedComputers
                isSavingAvailable = isDataValid
            },
            modifier = Modifier
                .padding(all = 10.dp)
                .weight(1f),
        )
        Row(modifier = Modifier.weight(0.5f)) {
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
            Spacer(modifier = Modifier.weight(0.6f))
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