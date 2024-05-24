plugins {
    id("java")
    id ("java-library")
    id("io.freefair.lombok") version "8.6"
}
group = "org.example"

repositories {
    mavenCentral()
}

dependencies {
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
tasks.register("prepareKotlinBuildScriptModel"){}

tasks.test {
    useJUnitPlatform()
}
tasks.compileJava{
    options.encoding = "UTF-8"
}

tasks.javadoc {
    options.encoding = "UTF-8"
}
