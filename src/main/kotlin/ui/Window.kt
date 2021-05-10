package ui

import androidx.compose.desktop.AppWindow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.intellij.util.ui.ImageUtil.createImage
import util.Constants
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.SwingUtilities.invokeLater

fun displayWindow() = invokeLater {
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
            ProductCatalog()
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
        image = createImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }

    return image
}