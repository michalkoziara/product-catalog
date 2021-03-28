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
fun ProductCatalog() {
    val fileComputerRepository = FileComputerRepository()
    val xmlComputerRepository = XmlComputerRepository()

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
                    computers = fileComputerRepository.computers
                }) {
                Text(
                    text = Constants.IMPORT_TXT_BUTTON_LABEL
                )
            }
            Button(
                modifier = Modifier.padding(10.dp),
                enabled = isSavingAvailable,
                onClick = {
                    fileComputerRepository.saveComputers(computers)
                }) {
                Text(
                    text = Constants.EXPORT_TXT_BUTTON_LABEL
                )
            }
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    computers = xmlComputerRepository.computers
                }) {
                Text(
                    text = Constants.IMPORT_XML_BUTTON_LABEL
                )
            }
            Button(
                modifier = Modifier.padding(10.dp),
                enabled = isSavingAvailable,
                onClick = {
                    xmlComputerRepository.saveComputers(computers)
                }) {
                Text(
                    text = Constants.EXPORT_XML_BUTTON_LABEL
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
                    text = Constants.ADD_RECORD_BUTTON_LABEL
                )
            }
            Spacer(modifier = Modifier.weight(0.6f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.4f)
            ) {
                Text(text = Constants.STATISTICS_LABEL)
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