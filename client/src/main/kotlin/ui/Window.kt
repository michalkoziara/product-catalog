package ui

import androidx.compose.desktop.AppWindow
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.lightColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import services.generated.ProductSoapService
import util.Constants
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.SwingUtilities.invokeLater

fun displayWindow(productService: ProductSoapService, isServerActive: Boolean) = invokeLater {
    val window = AppWindow(
        title = Constants.WINDOW_TITLE,
        icon = getWindowIcon()
    )
    window.maximize()
    window.show {
        val colors = lightColors(
            primary = Color(Constants.LIGHT_BLUE),
            primaryVariant = Color(Constants.DARKER_BLUE),
            secondary = Color(Constants.YELLOW),
            secondaryVariant = Color(Constants.YELLOW),
            onSurface = Color(Constants.LIGHT_BLUE),
        )

        MaterialTheme(colors = colors) {
            if (isServerActive) {
                ProductCatalog(productService)
            } else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = Constants.ERROR_MESSAGE,
                        style = TextStyle(
                            fontSize = 36.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

fun getWindowIcon(): BufferedImage {
    var image: BufferedImage? = null
    try {
        image = ImageIO.read(File(Constants.ICON_PATH))
    } catch (e: Exception) {
        print(e.message)
    }

    if (image == null) {
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }

    return image
}