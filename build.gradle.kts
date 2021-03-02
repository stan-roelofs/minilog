import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
    id("org.jetbrains.dokka") version "1.4.20"
    `java-library`
    signing
    `maven-publish`
}

group = "nl.stanroelofs"
version = "1.0.0-SNAPSHOT"
extra["isReleaseVersion"] = !version.toString().endsWith("SNAPSHOT")

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation(kotlin("test-junit"))
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

// Set dokka output directory
tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    outputDirectory.set(file("$buildDir/docs"))
    doFirst {
        file(outputDirectory).deleteRecursively()
    }
}

tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
                         "Implementation-Version" to project.version))
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}

// Configure publishing and signing
publishing {
    repositories {
        maven {
            val releaseRepo = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotRepo = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            setUrl(if (extra["isReleaseVersion"] as Boolean) releaseRepo else snapshotRepo)

            val nexusUsername: String by project
            val nexusPassword: String by project
            credentials {
                username = nexusUsername
                password = nexusPassword
            }
        }
    }

    publications {
        create<MavenPublication>("minilog") {
            pom {
                name.set("minilog")
                description.set("A minimalistic logging library")
                url.set("https://github.com/stan-roelofs/minilog/")
                from(components["java"])

                licenses {
                    license {
                        name.set("GPL-v3.0")
                        url.set("http://www.gnu.org/licenses/gpl-3.0.txt")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com/stan-roelofs/Kotlin-Gameboy-Emulator.git")
                    developerConnection.set("scm:git:git@github.com/stan-roelofs/Kotlin-Gameboy-Emulator.git")
                    url.set("https://github.com/stan-roelofs/Kotlin-Gameboy-Emulator/")
                }
                developers {
                    developer {
                        id.set("stan-roelofs")
                        name.set("Stan Roelofs")
                        email.set("stan@stanroelofs.nl")
                    }
                }
            }
        }
    }
}

// Signing artifacts
signing {
    sign(publishing.publications["mavenJava"])
}

tasks.withType<Sign>().configureEach {
    onlyIf {
        extra["isReleaseVersion"] as Boolean
    }
}