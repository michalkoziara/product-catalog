import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.kozmapps"
version = "1.0.0"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

val jaxWsTools: Configuration by configurations.creating

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("com.sun.xml.ws:jaxws-rt:3.0.0")
    implementation("jakarta.xml.ws:jakarta.xml.ws-api:3.0.0")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:3.0.0")
    implementation("jakarta.activation:jakarta.activation-api:2.0.0")

    jaxWsTools("com.sun.xml.ws:jaxws-tools:3.0.0")
}

val generateFromWsdl = task("generateFromWsdl") {
    group = BasePlugin.BUILD_GROUP
    val sourceDestDir = file("$projectDir/src/main/java")
    sourceDestDir.mkdirs()
    doLast {
        ant.withGroovyBuilder {
            "taskdef"(
                "name" to "wsimport",
                "classname" to "com.sun.tools.ws.ant.WsImport",
                "classpath" to jaxWsTools.asPath
            )
            "wsimport"(
                "keep" to true,
                "sourcedestdir" to sourceDestDir,
                "package" to "services.generated",
                "xdebug" to "true",
                "extension" to "true",
                "verbose" to "true",
                "quiet" to "false",
                "xnocompile" to "true",
                "wsdl" to project.file("src/main/resources/META-INF/wsdl/product-service.wsdl"),
                "wsdlLocation" to "http://localhost:8080/ws/product-service?wsdl"
            ) {
                "xjcarg"("value" to "-XautoNameResolution")
            }
        }
    }
}

tasks.withType<KotlinCompile>() {
    dependsOn(generateFromWsdl)
    kotlinOptions.jvmTarget = "15"
}

compose.desktop {
    application {
        mainClass = "core.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.Deb)
            modules("java.management")

            windows {
                console = true
                iconFile.set(project.file("src/main/resources/icon.ico"))
            }
            packageName = "Product Catalog Client"
        }
    }
}