import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
    id("org.jetbrains.dokka") version "1.4.20"
    `java-library`
    signing
    `maven-publish`
}

group = "nl.stanroelofs"
version = "1.0.1-SNAPSHOT"
val isReleaseVersion = !version.toString().endsWith("SNAPSHOT")
val javadocDestination = file("docs")

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

tasks.javadoc {
    dependsOn("dokkaJavadoc")
    setDestinationDir(javadocDestination)
}

// Set dokka output directory
tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    outputDirectory.set(javadocDestination)
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
            setUrl(if (isReleaseVersion) releaseRepo else snapshotRepo)

            val nexusUsername: String by project
            val nexusPassword: String by project
            credentials {
                username = nexusUsername
                password = nexusPassword
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
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
                    connection.set("scm:git:git@github.com/stan-roelofs/minilog.git")
                    developerConnection.set("scm:git:git@github.com/stan-roelofs/minilog.git")
                    url.set("https://github.com/stan-roelofs/minilog/")
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

tasks.withType<Sign>().configureEach {
    onlyIf {
        isReleaseVersion
    }
}

// Signing artifacts
signing {
    sign(publishing.publications["mavenJava"])
}