package ehb.group5.app.backend.utils;

import ehb.group5.app.Application;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import lombok.var;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@UtilityClass
public class Configurations {

    @SneakyThrows
    public static <T> T readOrCreateConfiguration(Class<T> clazz) {
        var configName = clazz.getSimpleName();
        if (!configName.endsWith(".yml"))
            configName = configName + ".yml";

        val path = Paths.get("config", configName);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);

            val template = getResourceFileAsString("/" + configName);
            if (template != null) {
                Files.write(path, template.getBytes(StandardCharsets.UTF_8));
            } else {
                Files.write(
                        path,
                        Serializations.serialize(clazz.newInstance()).getBytes(StandardCharsets.UTF_8)
                );
            }
        }

        return Serializations.deserialize(
                new String(Files.readAllBytes(path), StandardCharsets.UTF_8),
                clazz
        );
    }

    @SneakyThrows
    public <T> void writeConfiguration(T configuration) {
        Class<?> clazz = configuration.getClass();
        var configName = clazz.getSimpleName();
        if (!configName.endsWith(".yml"))
            configName = configName + ".yml";

        val pluginPath = Paths.get("config", configName);
        Files.write(
                pluginPath,
                Serializations.serialize(configuration).getBytes(StandardCharsets.UTF_8)
        );
    }

    @SneakyThrows
    private String getResourceFileAsString(String fileName) {
        @Cleanup InputStream is = Application.class.getResourceAsStream(fileName);
        if (is == null) return null;
        @Cleanup InputStreamReader isr = new InputStreamReader(is);
        @Cleanup BufferedReader reader = new BufferedReader(isr);
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
}

