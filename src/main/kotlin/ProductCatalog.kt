import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun ProductCatalog() {
    val fileComputerRepository = FileComputerRepository()
    val xmlComputerRepository = XmlComputerRepository()
    val databaseComputerRepository = DatabaseComputerRepository()

    var dataSource: DataSource by remember { mutableStateOf(DataSource.NULL) }
    val dataSourceLabel: String = remember(dataSource) { getDataSourceLabel(dataSource) }

    val validator = Validator()

    var computers: List<Computer> by remember { mutableStateOf(emptyList()) }
    val numberOfComputersByManufacturer: Map<String, Int> =
        remember(computers) { computeManufacturerStatistics(computers) }
    val isSavingAvailable: Boolean = remember(computers) { validator.validateComputers(computers) }

    var numberOfNewComputers: Int by remember { mutableStateOf(0) }
    var numberOfDuplicates: Int by remember { mutableStateOf(0) }

    Column {
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Button(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f),
                onClick = {
                    val newComputers = fileComputerRepository.computers
                    numberOfDuplicates = 0
                    numberOfNewComputers = 0

                    newComputers.forEach { newComputer ->
                        if (computers.contains(newComputer)) {
                            newComputer.isDuplicate = true
                            numberOfDuplicates++
                        } else {
                            numberOfNewComputers++
                        }
                    }
                    computers = emptyList()
                    computers = newComputers

                    dataSource = DataSource.TXT
                }) {
                Text(
                    text = Constants.IMPORT_TXT_BUTTON_LABEL
                )
            }
            Button(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f),
                enabled = isSavingAvailable,
                onClick = {
                    fileComputerRepository.saveComputers(computers)
                }) {
                Text(
                    text = Constants.EXPORT_TXT_BUTTON_LABEL
                )
            }
            Button(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f),
                onClick = {
                    val newComputers = xmlComputerRepository.computers
                    numberOfDuplicates = 0
                    numberOfNewComputers = 0

                    newComputers.forEach { newComputer ->
                        if (computers.contains(newComputer)) {
                            newComputer.isDuplicate = true
                            numberOfDuplicates++
                        } else {
                            numberOfNewComputers++
                        }
                    }
                    computers = emptyList()
                    computers = newComputers

                    dataSource = DataSource.XML
                }) {
                Text(
                    text = Constants.IMPORT_XML_BUTTON_LABEL
                )
            }
            Button(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f),
                enabled = isSavingAvailable,
                onClick = {
                    xmlComputerRepository.saveComputers(computers)
                }) {
                Text(
                    text = Constants.EXPORT_XML_BUTTON_LABEL
                )
            }
            Button(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f),
                onClick = {
                    val newComputers = databaseComputerRepository.computers
                    numberOfDuplicates = 0
                    numberOfNewComputers = 0

                    newComputers.forEach { newComputer ->
                        if (computers.contains(newComputer)) {
                            newComputer.isDuplicate = true
                            numberOfDuplicates++
                        } else {
                            numberOfNewComputers++
                        }
                    }
                    computers = emptyList()
                    computers = newComputers

                    dataSource = DataSource.DATABASE
                }) {
                Text(
                    text = Constants.IMPORT_DATABASE_BUTTON_LABEL
                )
            }
            Button(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f),
                enabled = isSavingAvailable,
                onClick = {
                    databaseComputerRepository.saveComputers(computers)
                }) {
                Text(
                    text = Constants.EXPORT_DATABASE_BUTTON_LABEL
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
            Spacer(modifier = Modifier.weight(0.05f))
            if (dataSource != DataSource.NULL) {
                Card(
                    modifier = Modifier
                        .weight(0.4f)
                        .height(height = 120.dp),
                    elevation = 4.dp,
                ) {
                    Column(
                        modifier = Modifier.padding(all = 10.dp),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier.padding(all = 5.dp).align(alignment = Alignment.CenterHorizontally),
                            fontWeight = FontWeight.Bold,
                            text = Constants.DATA_IMPORT_SOURCE_LABEL.format(dataSourceLabel),
                        )
                        Text(
                            modifier = Modifier.padding(all = 5.dp),
                            text = Constants.NUMBER_OF_NEW_RECORDS_LABEL.format(numberOfNewComputers)
                        )
                        Text(
                            modifier = Modifier.padding(all = 5.dp),
                            text = Constants.NUMBER_OF_DUPLICATED_RECORDS_LABEL.format(numberOfDuplicates)
                        )
                    }
                }
            } else {
                Spacer(modifier = Modifier.weight(0.4f))
            }
            Spacer(modifier = Modifier.weight(0.05f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.4f)
            ) {
                Text(text = Constants.STATISTICS_LABEL)
                ManufacturerTable(
                    numberOfComputersByManufacturer = numberOfComputersByManufacturer,
                    modifier = Modifier.padding(all = 10.dp)
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

fun getDataSourceLabel(dataSource: DataSource): String {
    return when (dataSource) {
        DataSource.TXT -> Constants.TXT_SOURCE_LABEL
        DataSource.XML -> Constants.XML_SOURCE_LABEL
        DataSource.DATABASE -> Constants.DATABASE_SOURCE_LABEL
        else -> ""
    }
}