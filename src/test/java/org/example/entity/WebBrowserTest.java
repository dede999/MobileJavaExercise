package org.example.entity;

import org.example.records.BrowserTab;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WebBrowserTest {
    private WebBrowser browser;

    @BeforeEach
    void setUp() {
        browser = new WebBrowser();
        browser.displayPageOnStart("https://www.example.com", false);
        browser.displayPageOnEnd("https://www.example.com", true);
        browser.displayPage("https://www.example.com", false, 1);
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
        browser.displayPageOnStart("https://www.example.com", false);
        BrowserTab tab = browser.getTabs().getFirst();

        assertEquals("https://www.example.com", tab.getUrlString());
    }

    @Test
    @DisplayName("Expects a page to be displayed on the last tab")
    void displayPageOnEnd() {
        browser.displayPageOnEnd("https://www.example.com", false);
        BrowserTab tab = browser.getTabs().getLast();

        assertEquals("https://www.example.com", tab.getUrlString());
    }

    @Test
    @DisplayName("Expects a page to be displayed on the specified tab")
    void displayPage() {
        browser.displayPage("https://www.example.com", false, 1);
        BrowserTab tab = browser.getTabs().get(1);

        assertEquals("https://www.example.com", tab.getUrlString());
    }

    @Test
    @DisplayName("Expects a private page to be opened")
    void displayPrivatePage() {
        int historySize = browser.getHistory().size();

        browser.displayPage("https://www.example.com", true, 0);
        BrowserTab tab = browser.getPrivateTabs().getFirst();

        assertEquals("https://www.example.com", tab.getUrlString());
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
        assertEquals("https://www.example.com", tab.getUrlString());
    }

    @Test
    @DisplayName("Expects a private tab to be closed")
    void closePrivateTab() {
        BrowserTab tab = browser.closeTab(0, true);

        assertEquals(0, browser.getPrivateTabs().size());
        assertEquals("https://www.example.com", tab.getUrlString());
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
