import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight")
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
    implementation("com.squareup.sqldelight:gradle-plugin:1.4.4")
    implementation("com.squareup.sqldelight:sqlite-driver:1.4.4")
    implementation("com.sun.xml.ws:jaxws-rt:3.0.0")
    implementation("jakarta.xml.ws:jakarta.xml.ws-api:3.0.0")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:3.0.0")
    implementation("jakarta.activation:jakarta.activation-api:2.0.0")

    jaxWsTools("com.sun.xml.ws:jaxws-tools:3.0.0")
}

val generateWsdl = task("generateWsdl") {
    group = BasePlugin.BUILD_GROUP
    doLast {
        ant.withGroovyBuilder {
            "taskdef"(
                "name" to "wsgen",
                "classname" to "com.sun.tools.ws.ant.WsGen",
                "classpath" to jaxWsTools.asPath
            )

            "wsgen"(
                "keep" to "true",
                "inlineSchemas" to "true",
                "encoding" to "UTF-8",
                "extension" to "true",
                "verbose" to "true",
                "classpath" to "$buildDir/classes/java/main",
                "sourcedestdir" to project.file("src"),
                "destdir" to project.file("src/main/resources/META-INF/wsdl"),
                "genwsdl" to "true",
                "sei" to "services.ProductSoapServiceImpl"
            )
        }
    }
}

sqldelight {
    database("ProductDatabase") {
        packageName = "database"
        sourceFolders = listOf("java")
    }
    linkSqlite = true
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "15"
}

compose.desktop {
    application {
        mainClass = "core.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.Deb)
            modules("java.management")
            modules("jdk.httpserver")
            modules("java.sql")

            windows {
                console = true
                iconFile.set(project.file("src/main/resources/icon.ico"))
            }
            packageName = "Product Catalog Admin"
        }
    }
}