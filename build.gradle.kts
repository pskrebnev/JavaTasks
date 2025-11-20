plugins {
    id("java")
    kotlin("jvm")
}

java {
    val toolchain1 = toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.openpnp:opencv:4.7.0-0")
    implementation("com.google.code.gson:gson:2.10.1")

    // https://mvnrepository.com/artifact/com.google.guava/guava
    implementation("com.google.guava:guava:33.2.0-jre")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.11.0")

    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation("org.apache.commons:commons-lang3:3.18.0")

    // https://mvnrepository.com/artifact/commons-io/commons-io
    implementation("commons-io:commons-io:2.16.1")

    // https://mvnrepository.com/artifact/org.apache.commons/commons-csv
    implementation("org.apache.commons:commons-csv:1.11.0")

    // https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc
    implementation("org.xerial:sqlite-jdbc:3.46.0.0")
    implementation(kotlin("stdlib-jdk8"))

    // Spring Framework dependencies
    implementation("org.springframework:spring-core:6.1.5")
    implementation("org.springframework:spring-context:6.2.7")
}



java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}

tasks.test {
    useJUnitPlatform()
}

// Additional task for running with specific JVM arguments
tasks.register<JavaExec>("runDetector") {
    group = "application"
    description = "Run the Sequin Panel Detector with optimized JVM settings"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("SequinPanelDetector")
    jvmArgs = listOf(
        "-Xmx2g",
        "-XX:+UseG1GC",
        "-XX:G1HeapRegionSize=16m"
    )
}




