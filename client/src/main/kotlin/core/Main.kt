package core

import services.generated.ProductSoapServiceImplService
import ui.displayWindow
import java.net.HttpURLConnection
import java.net.URL
import javax.swing.SwingUtilities.invokeLater


fun main() = invokeLater {
    val webServiceClient = ProductSoapServiceImplService()
    val productService = webServiceClient.productSoapServiceImplPort

    var isServerActive = true
    try {
        val httpConnection: HttpURLConnection =
            URL("http://localhost:8080/ws/product-service?wsdl").openConnection() as HttpURLConnection
        val status: Int = httpConnection.responseCode
    } catch (e: Exception) {
        isServerActive = false
    }

    displayWindow(productService, isServerActive)
}
