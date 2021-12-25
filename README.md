# FeatureNBTDeadlockBeGone
Fixes deadlocks with generating nbt during worldgen

To use in dev, add this to the repositories block in build.gradle:

    maven {
        // Blame and Resourceful Bees
        url "https://nexus.resourcefulbees.com/repository/maven-public/"
    }

Then in dependencies block in build.gradle, add:

	runtimeOnly ("com.telepathicgrunt:FeatureNBTDeadlockBeGone-Fabric:1.0.0+1.18.1") { transitive = false }
    
Change 1.0.0+1.18.1 to the version you want.
