plugins {
    id("java")
    id ("java-library")
    id("io.freefair.lombok") version "8.6"
}

group = "org.example"

repositories {
    mavenCentral()
}
tasks.register("prepareKotlinBuildScriptModel"){}

dependencies {
    implementation("com.opencsv:opencsv:5.8")
    implementation("org.projectlombok:lombok:1.18.28")
    implementation("org.projectlombok:lombok:1.18.28")
    implementation(project(":data"))

}

tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = "Main"
    }
    val dependencies = configurations
            .runtimeClasspath
            .get()
            .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    dependsOn(":data:jar")

}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(arrayOf("--release", "17"))
}

tasks.test {
    useJUnitPlatform()
}
tasks.compileJava{
    options.encoding = "UTF-8"
}

tasks.javadoc {
    options.encoding = "UTF-8"
}
