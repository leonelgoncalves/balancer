import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.2.5.RELEASE" apply false
  id("io.spring.dependency-management") version "1.0.9.RELEASE"
  kotlin("jvm") version "1.3.70"
  kotlin("plugin.spring") version "1.3.61"
}
val jacocoVersion: String by project
val kotlintestVersion: String by project
val mockkVersion: String by project

java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
  apply(plugin =  "jacoco")
  apply(plugin =  "org.jetbrains.kotlin.jvm")

  repositories {
    jcenter()
    mavenCentral()
  }

  configure<JacocoPluginExtension> {
    toolVersion = jacocoVersion
    reportsDir = file("$buildDir/reports/jacoco")
  }
}

java.sourceCompatibility = JavaVersion.VERSION_11

subprojects {
  apply(plugin = "java")
  apply(plugin = "kotlin")
  apply(plugin = "io.spring.dependency-management")
  group = "com.loadBalancer"


  repositories {
    jcenter()
    mavenCentral()
  }

  dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.mockito:mockito-core:2.23.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testImplementation("io.mockk:mockk:$mockkVersion")
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "11"
    }
  }

}


