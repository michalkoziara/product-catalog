package ui

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
import data.Computer
import data.ComputerBuilder
import util.Constants
import util.Validator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComputerTable(
    computers: List<Computer> = emptyList(),
    onComputersChange: (List<Computer>) -> Unit,
    modifier: Modifier = Modifier
) {
    val validator = Validator()

    val lazyListState = rememberLazyListState()
    val averageItemSize: Float by derivedStateOf {
        val items = lazyListState.layoutInfo.visibleItemsInfo
        items.sumBy { it.size }.toFloat() / (items.size - 1)
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
                .border(width = 1.dp, color = Color(Constants.BLACK_85)),
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
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setManufacturerName(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = validator.validateManufacturerName(computer),
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.diagonalScreenSize,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setDiagonalScreenSize(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = validator.validateDiagonalScreenSize(computer),
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.screenResolution,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setScreenResolution(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = validator.validateScreenResolution(computer),
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.screenSurfaceType,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setScreenSurfaceType(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = true,
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.touchscreenFlag,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setTouchscreenFlag(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = validator.validateTouchScreenFlag(computer),
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.cpu,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setCpu(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = true,
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.numberOfCpuCores,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setNumberOfCpuCores(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = validator.validateNumberOfCpuCores(computer),
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.clockFrequency,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setClockFrequency(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = validator.validateClockFrequency(computer),
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.ram,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setRam(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = validator.validateRam(computer),
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.discSize,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setDiscSize(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = validator.validateDiscSize(computer),
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.discType,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setDiscType(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = true,
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.gpu,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setGpu(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = true,
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.gpuMemory,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setGpuMemory(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = validator.validateGpuMemory(computer),
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.operatingSystem,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setOperatingSystem(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = true,
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
                        modifier = Modifier.weight(1f),
                    )
                    EditableCell(
                        text = computer.physicalDriveType,
                        onTextChange = fun(text: String) {
                            val changedComputer = ComputerBuilder(computer)
                                .setPhysicalDriveType(text)
                                .setChanged(true)
                                .createComputer()

                            val updatedComputers = computers.toMutableList()
                            updatedComputers[index] = changedComputer

                            onComputersChange(updatedComputers)
                        },
                        backgroundColor = getCellBackgroundColor(
                            isValid = true,
                            isChanged = computer.isChanged,
                            isDuplicate = computer.isDuplicate,
                        ),
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
        Cell(modifier = Modifier.weight(0.5f), text = Constants.ORDINAL_NUMBER_LABEL)
        listOf(
            Constants.MANUFACTURER_NAME_LABEL,
            Constants.DIAGONAL_SCREEN_SIZE_LABEL,
            Constants.SCREEN_RESOLUTION_LABEL,
            Constants.SCREEN_SURFACE_TYPE_LABEL,
            Constants.TOUCHSCREEN_FLAG_LABEL,
            Constants.CPU_LABEL,
            Constants.NUMBER_OF_CPU_CORES_LABEL,
            Constants.CLOCK_FREQUENCY_LABEL,
            Constants.RAM_LABEL,
            Constants.DISC_SIZE_LABEL,
            Constants.DISC_TYPE_LABEL,
            Constants.GPU_LABEL,
            Constants.GPU_MEMORY_LABEL,
            Constants.OPERATING_SYSTEM_LABEL,
            Constants.PHYSICAL_DRIVE_TYPE_LABEL,
        ).forEach { label -> Cell(modifier = Modifier.weight(1f), text = label) }
    }
}

fun getCellBackgroundColor(
    isValid: Boolean,
    isDuplicate: Boolean,
    isChanged: Boolean,
): Color {
    var backgroundColor = Color(Constants.GREY)

    if (isDuplicate) {
        backgroundColor = Color(Constants.LIGHT_RED)
    }

    if (isChanged) {
        backgroundColor = Color.White
    }

    if (!isValid) {
        backgroundColor = Color(Constants.DARK_RED)
    }

    return backgroundColor
}