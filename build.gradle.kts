plugins {
    id("java")
    application
}

val javaVersion = JavaVersion.VERSION_17

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}
group = "edu.crud"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.10.1")
    // https://mvnrepository.com/artifact/com.google.code.findbugs/jsr305
    implementation("com.google.code.findbugs:jsr305:3.0.2")
}

application {
    mainClass.set("edu.crud.Main")
}

tasks.run<JavaExec>() {
    standardInput = System.`in`
}
tasks.test {
    useJUnitPlatform()
}
