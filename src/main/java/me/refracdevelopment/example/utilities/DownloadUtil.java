package me.refracdevelopment.example.utilities;

import lombok.experimental.UtilityClass;
import me.refracdevelopment.example.ExamplePlugin;
import net.byteflux.libby.BukkitLibraryManager;
import net.byteflux.libby.Library;

@UtilityClass
public class DownloadUtil {

    public void downloadAndEnable() {
        BukkitLibraryManager libraryManager = new BukkitLibraryManager(ExamplePlugin.getInstance());
        Library lib = Library.builder()
                .groupId("org{}slf4j") // "{}" is replaced with ".", useful to avoid unwanted changes made by maven-shade-plugin
                .artifactId("slf4j-reload4j")
                .version("2.0.9")
                .build();
        Library lib2 = Library.builder()
                .groupId("org{}mariadb{}jdbc") // "{}" is replaced with ".", useful to avoid unwanted changes made by maven-shade-plugin
                .artifactId("mariadb-java-client")
                .version("3.2.0")
                .build();
        Library lib3 = Library.builder()
                .groupId("org{}xerial") // "{}" is replaced with ".", useful to avoid unwanted changes made by maven-shade-plugin
                .artifactId("sqlite-jdbc")
                .version("3.43.0.0")
                .build();
        Library lib4 = Library.builder()
                .groupId("com{}j256{}ormlite") // "{}" is replaced with ".", useful to avoid unwanted changes made by maven-shade-plugin
                .artifactId("ormlite-jdbc")
                .version("6.1")
                .build();

        libraryManager.addMavenCentral();
        libraryManager.loadLibrary(lib);
        libraryManager.loadLibrary(lib2);
        libraryManager.loadLibrary(lib3);
        libraryManager.loadLibrary(lib4);
    }
}