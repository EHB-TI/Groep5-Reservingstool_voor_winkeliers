package ehb.group5.app.backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;

/***
 * Code komt van een oude project die Ketsu_Sama maakte
 * @author Ketsu_Sama
 */
@UtilityClass
public class Serializations {

    private final String YAML_WEIRDO_CONSTANT = "---\n";

    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory())
            .registerModule(new ParameterNamesModule())
            .registerModule(new JavaTimeModule());

    /**
     * @param data L'objet qui va être serializé
     * @return Un version serializé de l'objet indiqué
     */
    @SneakyThrows
    public String serialize(Object data) {
        val serialized = mapper.writeValueAsString(data);
        if(serialized.startsWith(YAML_WEIRDO_CONSTANT))
            return serialized.substring(YAML_WEIRDO_CONSTANT.length(), serialized.length() - 1);

        return serialized;
    }

    /**
     * @param serialized L'objet serializé dans sa forme de texte
     * @param type Le type d'objet retourné
     * @param <T> Le type
     * @return Une nouvelle instance déserialisée
     */
    @SneakyThrows
    public <T> T deserialize(String serialized, Class<T> type) {
        return mapper.readValue(serialized, type);
    }

}
