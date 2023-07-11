plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:31.1-jre")

    val msSqlJDBCDriverVersion = "12.2.0"
    implementation("com.microsoft.sqlserver:mssql-jdbc:$msSqlJDBCDriverVersion.jre11")
    implementation("com.microsoft.sqlserver:mssql-jdbc_auth:$msSqlJDBCDriverVersion.x64")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

application {
    // Define the main class for the application.
    mainClass.set("energister.jdbc.mssql.App")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
