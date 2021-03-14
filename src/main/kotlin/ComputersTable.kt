import androidx.compose.desktop.DesktopTheme
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComputerTable(
    computers: List<Computer> = emptyList(),
    onComputersChange: (List<Computer>, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    val averageItemSize: Float by derivedStateOf {
        val items = lazyListState.layoutInfo.visibleItemsInfo
        items.sumBy { it.size }.toFloat() / items.size
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        DesktopTheme(
            ScrollbarStyle(
                minimalHeight = 16.dp,
                thickness = 8.dp,
                shape = MaterialTheme.shapes.small,
                hoverDurationMillis = 300,
                unhoverColor = MaterialTheme.colors.onSurface.copy(alpha = 0.32f),
                hoverColor = MaterialTheme.colors.onSurface.copy(alpha = 0.50f)
            )
        ) {
            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                adapter = rememberScrollbarAdapter(
                    scrollState = lazyListState,
                    itemCount = computers.size,
                    averageItemSize =
                    if (averageItemSize.isFinite() && averageItemSize > 0)
                        averageItemSize.dp
                    else
                        1.dp,
                )
            )
        }
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize()
                .padding(end = 10.dp)
                .border(width = 1.dp, color = Color(0xDD000000)),
        ) {
            stickyHeader {
                Header()
            }
            itemsIndexed(computers) { index, computer ->
                Row {
                    Cell(
                        text = (index + 1).toString(),
                        modifier = Modifier.weight(0.5f).height(60.dp),
                    )
                    EditableCell(
                        text = computer.manufacturerName,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setManufacturerName(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers, true)
                            return true
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.diagonalScreenSize,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setDiagonalScreenSize(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            var isTouchScreenFlagValid = false
                            if (text.isEmpty() || text.matches(Regex("^[1-9][0-9]*\"$"))) {
                                isTouchScreenFlagValid = true
                            }

                            onComputersChange(updatedComputers, isTouchScreenFlagValid)
                            return isTouchScreenFlagValid
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.screenResolution,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setScreenResolution(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            var isTouchScreenFlagValid = false
                            if (text.isEmpty() || text.matches(Regex("^[1-9][0-9]*x[1-9][0-9]*$"))) {
                                isTouchScreenFlagValid = true
                            }

                            onComputersChange(updatedComputers, isTouchScreenFlagValid)
                            return isTouchScreenFlagValid
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.screenSurfaceType,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setScreenSurfaceType(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers, true)
                            return true
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.touchscreenFlag,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setTouchscreenFlag(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            var isTouchScreenFlagValid = false
                            if (text == "tak" || text == "nie" || text == "") {
                                isTouchScreenFlagValid = true
                            }

                            onComputersChange(updatedComputers, isTouchScreenFlagValid)
                            return isTouchScreenFlagValid
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.cpu,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setCpu(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers, true)
                            return true
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.numberOfCpuCores,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setNumberOfCpuCores(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            var isTouchScreenFlagValid = false
                            if (text.isEmpty() || text.matches(Regex("^[1-9][0-9]*$"))) {
                                isTouchScreenFlagValid = true
                            }

                            onComputersChange(updatedComputers, isTouchScreenFlagValid)
                            return isTouchScreenFlagValid
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.clockFrequency,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setClockFrequency(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            var isTouchScreenFlagValid = false
                            if (text.isEmpty() || text.matches(Regex("^[1-9][0-9]*$"))) {
                                isTouchScreenFlagValid = true
                            }

                            onComputersChange(updatedComputers, isTouchScreenFlagValid)
                            return isTouchScreenFlagValid
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.ram,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setRam(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            var isTouchScreenFlagValid = false
                            if (text.isEmpty() || text.matches(Regex("^[1-9][0-9]*GB$"))) {
                                isTouchScreenFlagValid = true
                            }

                            onComputersChange(updatedComputers, isTouchScreenFlagValid)
                            return isTouchScreenFlagValid
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.discSize,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setDiscSize(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            var isTouchScreenFlagValid = false
                            if (text.isEmpty() || text.matches(Regex("^[1-9][0-9]*GB$"))) {
                                isTouchScreenFlagValid = true
                            }

                            onComputersChange(updatedComputers, isTouchScreenFlagValid)
                            return isTouchScreenFlagValid
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.discType,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setDiscType(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers, true)
                            return true
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.gpu,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setGpu(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers, true)
                            return true
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.gpuMemory,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setGpuMemory(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            var isTouchScreenFlagValid = false
                            if (text.isEmpty() || text.matches(Regex("^[1-9][0-9]*GB$"))) {
                                isTouchScreenFlagValid = true
                            }

                            onComputersChange(updatedComputers, isTouchScreenFlagValid)
                            return isTouchScreenFlagValid
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.operatingSystem,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setOperatingSystem(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers, true)
                            return true
                        },
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.physicalDriveType,
                        onTextChange = fun(text: String): Boolean {
                            val changedComputer = ComputerBuilder(computer)
                                .setPhysicalDriveType(text)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers, true)
                            return true
                        },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Cell(modifier = Modifier.weight(0.5f), text = "LP.")
        listOf(
            "Nazwa producenta",
            "Przekątna ekranu",
            "Rozdzielczość ekranu",
            "Rodzaj powierzchni ekranu",
            "Czy ekran jest dotykowy",
            "Nazwa procesora",
            "Liczba rdzeni fizycznych",
            "Prędkość taktowania MHz",
            "Wielkość pamięci RAM",
            "Pojemność dysku",
            "Rodzaj dysku",
            "Nazwa układu graficznego",
            "Pamięć układu graficznego",
            "Nazwa systemu operacyjnego",
            "Rodzaj napędu fizycznego w komputerze "
        ).forEach { label -> Cell(modifier = Modifier.weight(1f), text = label) }
    }
}
