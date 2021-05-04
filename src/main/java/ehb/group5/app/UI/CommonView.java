package ehb.group5.app.UI;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ehb.group5.app.backend.utils.VaadinUtils;
import lombok.Getter;
import lombok.val;

import java.time.LocalDate;

@StyleSheet("https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css")
@JavaScript("https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js")
@CssImport("./styles/commonStyle.css")
public abstract class CommonView extends VerticalLayout {

    @Getter
    private Div container;

    public CommonView() {
        setWidth("100%");
        getStyle().set("padding", "0");

        /**
         * NAVBAR
         */
        val nav = new Nav();
        nav.addClassNames("navbar", "navbar-expand-lg", "navbar-light", "bg-light");
        nav.setWidth("100%");
        add(nav);

        // Nav conatainer
        val navContainer = new Div();
        navContainer.addClassNames("container-fluid");
        nav.add(navContainer);

        // Website title
        val navTitle = new Anchor();
        navTitle.setHref("#");
        navTitle.setText("Logo");
        navContainer.add(navTitle);

        // Toggle button for smaller screens
        val togglerSpan = new Span();
        togglerSpan.addClassNames("navbar-toggler-icon");

        val togglerButton = new Button(togglerSpan);
        togglerButton.getElement().getClassList().add("navbar-toggler");
        togglerButton.getElement().setAttribute("data-bs-toggle", "collapse")
                .setAttribute("data-bs-target", "#navbarSupportedContent")
                .setAttribute("aria-controls", "navbarSupportedContent")
                .setAttribute("aria-expanded", "false")
                .setAttribute("aria-label", "Toggle navigation");
        navContainer.add(togglerButton);

        // Items div
        val collapseDiv = new Div();
        collapseDiv.addClassNames("collapse", "navbar-collapse", "justify-content-center");
        collapseDiv.setId("navbarSupportedContent");
        navContainer.add(collapseDiv);

        /**
         * Middle item list
         */
        val ulNav = new UnorderedList();
        ulNav.addClassNames("navbar-nav", "me-auto", "mb-2", "mb-lg-0");
        collapseDiv.add(ulNav);

        val homeLi = new ListItem();
        val homeAnchor = new Anchor("#", "Dashboard");
        homeLi.addClassNames("nav-item");
        homeAnchor.addClassNames("nav-link");
        if (VaadinUtils.isAtLocation(""))
            homeAnchor.addClassNames("active");
        homeLi.add(homeAnchor);
        ulNav.add(homeLi);

        val storeLi = new ListItem();
        val storeAnchor = new Anchor("#", "Winkel");
        storeLi.addClassNames("nav-item");
        storeAnchor.addClassNames("nav-link");
        if (VaadinUtils.isAtLocation("/store"))
            storeAnchor.addClassNames("active");
        storeLi.add(storeAnchor);
        ulNav.add(storeLi);

        val statsLi = new ListItem();
        val statsAnchor = new Anchor("#", "Statistieken");
        statsLi.addClassNames("nav-item");
        statsAnchor.addClassNames("nav-link");
        if (VaadinUtils.isAtLocation("/stats"))
            statsAnchor.addClassNames("active");
        statsLi.add(statsAnchor);
        ulNav.add(statsLi);

        val supportLi = new ListItem();
        val supportAnchor = new Anchor("#", "Support");
        supportLi.addClassNames("nav-item");
        supportAnchor.addClassNames("nav-link");
        if (VaadinUtils.isAtLocation("/support"))
            supportAnchor.addClassNames("active");
        supportLi.add(supportAnchor);
        ulNav.add(supportLi);

        /**
         * Right Item list
         */
        val rightUl = new UnorderedList();
        rightUl.addClassNames("navbar-nav");
        collapseDiv.add(rightUl);

        val profileLi = new ListItem();
        profileLi.addClassNames("nav-item");
        val profileAnchor = new Anchor("#", "Profiel");
        profileAnchor.addClassNames("nav-link");
        if (VaadinUtils.isAtLocation("/profile"))
            profileAnchor.addClassNames("active");
        profileLi.add(profileAnchor);
        rightUl.add(profileLi);

        val disconnectLi = new ListItem();
        disconnectLi.addClassNames("nav-item");
        val disconnectAnchor = new Anchor("#", "Uitloggen");
        disconnectAnchor.addClassNames("nav-link");
        disconnectLi.add(disconnectAnchor);
        rightUl.add(disconnectLi);

        /**
         * Main Container
         */
        container = new Div();
        container.addClassNames("main-container");
        add(container);
    }

    /**
     * Footer
     */
    public void placeFooter() {
        val footer = new Footer();
        footer.addClassNames("bg-light", "text-center", "text-lg-start");
        footer.setWidth("100%");
        val div = new Div();
        div.addClassNames("text-center", "p-3");
        div.setText("© " + LocalDate.now().getYear() + " Copyright Groep 5");
        footer.add(div);
        add(footer);
    }
}
