import androidx.compose.desktop.AppWindow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.SwingUtilities.invokeLater

fun main() = invokeLater {
    val window = AppWindow(
        title = "Integracja Systemów - Michał Koziara",
        icon = getWindowIcon()
    )
    window.maximize()
    window.show {
        val colors = lightColors(
            primary = Color(0xFF03A9F4),
            primaryVariant = Color(0xFF039BE5),
            secondary = Color(0xFFFFC107),
            secondaryVariant = Color(0xFFC107),
            onSurface = Color(0xFF03A9F4),
        )

        MaterialTheme(
            colors = colors,
        ) {
            ProductCatalog(computerRepository = FileComputerRepository())
        }
    }
}

fun getWindowIcon(): BufferedImage {
    var image: BufferedImage? = null
    try {
        image = ImageIO.read(File("./icons/icon.png"))
    } catch (e: Exception) {
        print(e.message)
    }

    if (image == null) {
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }

    return image
}