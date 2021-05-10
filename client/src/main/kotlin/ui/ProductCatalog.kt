package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import services.generated.Computer
import services.generated.ComputerArray
import services.generated.ProductSoapService
import util.Constants

@Composable
fun ProductCatalog(productService: ProductSoapService) {
    var computers: List<Computer> by remember { mutableStateOf(emptyList()) }

    var selectedManufacturerName by remember { mutableStateOf("") }
    var selectedScreenSurfaceType by remember { mutableStateOf("") }
    var selectedDisplayAspectRatio by remember { mutableStateOf("") }

    var numberOfComputersFromSelectedManufacturer by remember { mutableStateOf(0) }
    var numberOfComputersWithSelectedDisplayAspectRatio by remember { mutableStateOf(0) }

    Column {
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(220.dp)
                    .height(35.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = Constants.SELECT_MANUFACTURER_NAME_LABEL)
            }
            Dropdown(
                items = productService.manufacturerNames.item,
                onChange = { itemLabel -> selectedManufacturerName = itemLabel },
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(180.dp),
            )
            Button(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(250.dp),
                onClick = {
                    if (selectedManufacturerName.isNotBlank()) {
                        numberOfComputersFromSelectedManufacturer =
                            productService.getNumberOfComputersByManufacturerName(selectedManufacturerName).toInt()
                    }
                }) {
                Text(
                    text = Constants.SHOW_COMPUTERS_COUNT_BUTTON_LABEL,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Text(
                text = Constants.NUMBER_OF_COMPUTERS_LABEL.format(numberOfComputersFromSelectedManufacturer),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(220.dp)
                    .height(35.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = Constants.SELECT_SCREEN_SURFACE_TYPE_LABEL)
            }
            Dropdown(
                items = productService.screenSurfaceTypes.item,
                onChange = { itemLabel -> selectedScreenSurfaceType = itemLabel },
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(180.dp),
            )
            Button(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(250.dp),
                onClick = {
                    if (selectedScreenSurfaceType.isNotBlank()) {
                        val computerArray: ComputerArray =
                            productService.getComputersByScreenSurfaceType(selectedScreenSurfaceType)
                        computers = computerArray.item
                    }
                }) {
                Text(
                    text = Constants.SHOW_COMPUTERS_LIST_BUTTON_LABEL,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
        }
        Row(modifier = Modifier.padding(all = 10.dp)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(220.dp)
                    .height(35.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = Constants.SELECT_DISPLAY_ASPECT_RATIO_LABEL)
            }
            Dropdown(
                items = listOf("4:3", "16:9", "16:10"),
                onChange = { itemLabel -> selectedDisplayAspectRatio = itemLabel },
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(180.dp),
            )
            Button(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(250.dp),
                onClick = {
                    if (selectedDisplayAspectRatio.isNotBlank()) {
                        numberOfComputersWithSelectedDisplayAspectRatio =
                            productService.getNumberOfComputersByDisplayAspectRatio(selectedDisplayAspectRatio).toInt()
                    }
                }) {
                Text(
                    text = Constants.SHOW_COMPUTERS_COUNT_BUTTON_LABEL,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Text(
                text = Constants.NUMBER_OF_COMPUTERS_LABEL.format(numberOfComputersWithSelectedDisplayAspectRatio),
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
        ComputerTable(
            computers = computers,
            modifier = Modifier
                .padding(all = 10.dp)
                .weight(1f),
        )
    }
}