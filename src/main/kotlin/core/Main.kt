package core

import jakarta.xml.ws.Endpoint
import services.ProductSoapServiceImpl
import ui.displayWindow
import util.Constants
import javax.swing.SwingUtilities.invokeLater

fun main() = invokeLater {
    Endpoint.publish(
        "${Constants.PROTOCOL}://${Constants.HOST}:${Constants.PORT}/${Constants.ENDPOINT}",
        ProductSoapServiceImpl()
    )
    displayWindow()
}
