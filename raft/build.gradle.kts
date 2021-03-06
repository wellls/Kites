import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL


plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.

    // Apply the application plugin to add support for building a CLI application.
    application
//    kotlin("jvm") version "1.4.0"
    kotlin("jvm") version "1.4.0"
    id("org.jetbrains.dokka") version ("1.4.0")
    id ("com.google.protobuf") version ("0.8.13")
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    mavenCentral()
    jcenter()
    maven("https://oss.jfrog.org/oss-snapshot-local")
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

    implementation("org.slf4j:slf4j-jdk14:1.7.30")

    implementation("com.google.guava:guava:29.0-jre")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // Use the kotlin rSocket integration
    implementation("io.rsocket.kotlin:rsocket-core:0.10.0-SNAPSHOT")
//    implementation("io.rsocket.kotlin:rsocket-transport-ktor:0.10.0-SNAPSHOT")

    implementation("io.netty:netty-all:4.1.53.Final")

    implementation("org.rocksdb:rocksdbjni:6.13.3")
}


val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            moduleDisplayName.set("Kites raft")
            includes.from("main.md")
            sourceLink {
                localDirectory.set(file("src/main/kotlin/"))
                remoteUrl.set(URL("https://github.com/Kotlin/kotlin-examples/tree/master/" +
                        "gradle/dokka/dokka-gradle-example/src/main/kotlin"
                ))
                remoteLineSuffix.set("#L")
            }
        }
    }
}