package ehb.group5.app.backend.utils;

import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.net.URI;
import java.net.URISyntaxException;

@UtilityClass
public class VaadinUtils {

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
