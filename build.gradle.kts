import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    id("org.jetbrains.compose") version "0.4.0-build180"
    id("com.squareup.sqldelight") version "1.4.4"
}

group = "com.kozmapps"
version = "1.0.0"

sqldelight {
    database("ProductDatabase") {
        packageName = "database"
        sourceFolders = listOf("")
    }
    linkSqlite = true
}

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("com.squareup.sqldelight:gradle-plugin:1.4.4")
    implementation("com.squareup.sqldelight:sqlite-driver:1.4.4")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "15"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.Deb)
            modules("java.sql")

            windows {
                iconFile.set(project.file("src/main/resources/icon.ico"))
            }
            packageName = "Product Catalog"
        }
    }
}