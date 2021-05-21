package ehb.group5.app.backend.utils;

import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Arnaud Faille
 */
@UtilityClass
public class VaadinUtils {

    /**
     * Source from https://github.com/vaadin/flow/issues/1897#issuecomment-473293065
     */
    public static URI getLocation() {
        VaadinServletRequest request = (VaadinServletRequest) VaadinService.getCurrentRequest();
        StringBuffer uriString = request.getRequestURL();
        try {
            return new URI(uriString.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isAtLocation(String location) {
        val currentLocation = getLocation().getRawPath();
        return currentLocation.equals(location) || currentLocation.equals(location + "/");
    }
}
