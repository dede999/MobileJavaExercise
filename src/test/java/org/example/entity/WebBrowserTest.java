package org.example.entity;

import com.github.javafaker.Faker;
import org.example.records.BrowserTab;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WebBrowserTest {
    private WebBrowser browser;
    private Faker faker;
    private String publicUrl;
    private String privateUrl;

    public String createUrl() {
        return faker.internet().url();
    }

    @BeforeEach
    void setUp() {
        browser = new WebBrowser();
        faker = new Faker(new Locale("pt-BR"));
        publicUrl = createUrl();
        privateUrl = createUrl();
        browser.displayPageOnStart(createUrl(), false);
        browser.displayPageOnEnd(privateUrl, true);
        browser.displayPage(publicUrl, false, 1);
    }

    @Test
    @DisplayName("Expects a new tab to be added")
    void addNewTab() {
        BrowserTab commonTab = browser.addTab(false, 1);
        BrowserTab privateTab = browser.addTab(true, 6);

        assertEquals(commonTab, browser.getTabs().get(1));
        assertEquals(privateTab, browser.getPrivateTabs().getLast());
    }

    @Test
    @DisplayName("Expects a page to be displayed on the first tab")
    void displayPageOnStart() {
        String url = createUrl();
        browser.displayPageOnStart(url, false);
        BrowserTab tab = browser.getTabs().getFirst();

        assertEquals(url, tab.getUrlString());
    }

    @Test
    @DisplayName("Expects a page to be displayed on the last tab")
    void displayPageOnEnd() {
        String url = createUrl();
        browser.displayPageOnEnd(url, false);
        BrowserTab tab = browser.getTabs().getLast();

        assertEquals(url, tab.getUrlString());
    }

    @Test
    @DisplayName("Expects a page to be displayed on the specified tab")
    void displayPage() {
        String url = createUrl();
        browser.displayPage(url, false, 1);
        BrowserTab tab = browser.getTabs().get(1);

        assertEquals(url, tab.getUrlString());
    }

    @Test
    @DisplayName("Expects a private page to be opened")
    void displayPrivatePage() {
        String url = createUrl();
        int historySize = browser.getHistory().size();

        browser.displayPage(url, true, 0);
        BrowserTab tab = browser.getPrivateTabs().getFirst();

        assertEquals(url, tab.getUrlString());
        assertEquals(historySize, browser.getHistory().size());
    }

    @Test
    @DisplayName("Expects a public page update to increment history size")
    void updatePage() {
        int historySize = browser.getHistory().size();
        browser.updatePage(1, false);

        assertEquals(historySize + 1, browser.getHistory().size());
    }

    @Test
    @DisplayName("Expects a private page update to not increment history size")
    void updatePrivatePage() {
        int historySize = browser.getHistory().size();
        browser.updatePage(0, true);

        assertEquals(historySize, browser.getHistory().size());
    }

    @Test
    @DisplayName("Expects a tab to be closed")
    void closeTab() {
        BrowserTab tab = browser.closeTab(1, false);

        assertEquals(1, browser.getTabs().size());
        assertEquals(publicUrl, tab.getUrlString());
    }

    @Test
    @DisplayName("Expects a private tab to be closed")
    void closePrivateTab() {
        BrowserTab tab = browser.closeTab(0, true);

        assertEquals(0, browser.getPrivateTabs().size());
        assertEquals(privateUrl, tab.getUrlString());
    }

    @Test
    @DisplayName("Expects a non-existent tab to not be updated")
    void updateNonExistentTab() {
        int historySize = browser.getHistory().size();
        browser.updatePage(2, false);

        assertEquals(historySize, browser.getHistory().size());
    }

    @Test
    @DisplayName("Expects a non-existent tab to not be closed")
    void closeNonExistentTab() {
        int currentTabs = browser.getHistory().size();
        BrowserTab tab = browser.closeTab(2, false);

        assertEquals(currentTabs, browser.getTabs().size());
        assertNull(tab);
    }
}
