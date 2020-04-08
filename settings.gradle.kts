rootProject.name = "load-balancer"
include("distribution")
include("domain")

pluginManagement {
  repositories {
    gradlePluginPortal()
    jcenter()
  }
}