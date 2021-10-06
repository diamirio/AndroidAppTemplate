plugins {
    id("de.fayard.refreshVersions") version "0.23.0"
}

refreshVersions {
    enableBuildSrcLibs()
}

include(":app", ":core", ":base-ui")