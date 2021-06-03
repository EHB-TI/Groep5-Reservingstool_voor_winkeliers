package ehb.group5.app.UI.layouts;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.views.*;
import ehb.group5.app.backend.listeners.UIServiceInitListener;
import ehb.group5.app.backend.utils.VaadinUtils;
import lombok.Getter;
import lombok.val;

import java.time.LocalDate;

/**
 * Maakt gebruik van Bootstrap 5 om responsive componenten te hebben
 * @author Arnaud Faille
 */
@StyleSheet("https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css")
@JavaScript("https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js")
@CssImport("./styles/commonStyle.css")
public abstract class CommonLayout extends Div {

    @Getter
    private Div container;

    public CommonLayout() {
        addClassName("common_layout");

        val account = VaadinSession.getCurrent().getAttribute("account");

        Div top = new Div();
        top.addClassNames("common_layout_top");
        add(top);

        /**
         * NAVBAR
         */
        val nav = new Nav();
        nav.addClassNames("navbar", "navbar-expand-lg", "navbar-light", "bg-light", "m-0");
        nav.setWidth("100%");
        top.add(nav);

        // Nav conatainer
        val navContainer = new Div();
        navContainer.addClassNames("container-fluid");
        nav.add(navContainer);

        // Website title
        val navLogo = new Image("https://imgur.com/download/BjBA0KY/", "Website logo");
        navLogo.setWidth("80px");
        navLogo.setHeight("45px");
        navContainer.add(navLogo);

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

        if (UIServiceInitListener.hasAcces(account.getClass(), DashboardView.class)) {
            val homeLi = new ListItem();
            val homeAnchor = new RouterLink("Dashboard", DashboardView.class);
            homeLi.addClassNames("nav-item");
            homeAnchor.addClassNames("nav-link");
            if (VaadinUtils.isAtLocation(""))
                homeAnchor.addClassNames("active");
            homeLi.add(homeAnchor);
            ulNav.add(homeLi);
        }

        if (UIServiceInitListener.hasAcces(account.getClass(), EditView.class)) {
            val storeLi = new ListItem();
            val storeAnchor = new RouterLink("Winkel", EditView.class);
            storeLi.addClassNames("nav-item");
            storeAnchor.addClassNames("nav-link");
            if (VaadinUtils.isAtLocation("/store"))
                storeAnchor.addClassNames("active");
            storeLi.add(storeAnchor);
            ulNav.add(storeLi);
        }

        if (UIServiceInitListener.hasAcces(account.getClass(), CalendarView.class)) {
            val statsLi = new ListItem();
            val statsAnchor = new RouterLink("Calender", CalendarView.class);
            statsLi.addClassNames("nav-item");
            statsAnchor.addClassNames("nav-link");
            if (VaadinUtils.isAtLocation("/stats"))
                statsAnchor.addClassNames("active");
            statsLi.add(statsAnchor);
            ulNav.add(statsLi);
        }

        if (UIServiceInitListener.hasAcces(account.getClass(), SupportView.class)) {
            val supportLi = new ListItem();
            val supportAnchor = new RouterLink("Support", SupportView.class);
            supportLi.addClassNames("nav-item");
            supportAnchor.addClassNames("nav-link");
            if (VaadinUtils.isAtLocation("/support"))
                supportAnchor.addClassNames("active");
            supportLi.add(supportAnchor);
            ulNav.add(supportLi);
        }

        if (UIServiceInitListener.hasAcces(account.getClass(), SupportAdminView.class)) {
            val ticketsLi = new ListItem();
            val ticketsAnchor = new RouterLink("Tickets", SupportAdminView.class);
            ticketsLi.addClassNames("nav-item");
            ticketsAnchor.addClassNames("nav-link");
            if (VaadinUtils.isAtLocation("/support/admin"))
                ticketsAnchor.addClassNames("active");
            ticketsLi.add(ticketsAnchor);
            ulNav.add(ticketsLi);
        }

        /**
         * Right Item list
         */
        val rightUl = new UnorderedList();
        rightUl.addClassNames("navbar-nav");
        collapseDiv.add(rightUl);

        if (UIServiceInitListener.hasAcces(account.getClass(), ProfielView.class)) {
            val profileLi = new ListItem();
            profileLi.addClassNames("nav-item");
            val profileAnchor = new RouterLink("Profiel", ProfielView.class);
            profileAnchor.addClassNames("nav-link");
            if (VaadinUtils.isAtLocation("/profile"))
                profileAnchor.addClassNames("active");
            profileLi.add(profileAnchor);
            rightUl.add(profileLi);
        }

        val disconnectLi = new ListItem();
        disconnectLi.addClassNames("nav-item");
        val disconnectAnchor = new RouterLink("Uitloggen", LogoutView.class);
        disconnectAnchor.addClassNames("nav-link");
        disconnectLi.add(disconnectAnchor);
        rightUl.add(disconnectLi);

        /**
         * Main Container
         */
        container = new Div();
        container.addClassNames("main-container");

        top.add(getContainer());

        /**
         * Footer
         */
        val footer = new Footer();
        footer.addClassNames("bg-light", "text-center", "text-lg-start", "common_layout_footer");
        footer.setWidth("100%");
        val div = new Div();
        div.addClassNames("text-center", "p-3");
        div.setText("© " + LocalDate.now().getYear() + " Copyright Groep 5");
        footer.add(div);
        add(footer);
    }
}
