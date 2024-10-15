package org.example.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import org.example.records.BrowserTab;

@Getter
public class WebBrowser {
    private final List<BrowserTab> tabs = new ArrayList<>();
    private final List<BrowserTab> privateTabs = new ArrayList<>();
    private final List<String> history = new ArrayList<>();

    public WebBrowser() {}

    public BrowserTab addTab(boolean isPrivate, int index) {
        BrowserTab tab = new BrowserTab("", isPrivate);
        List<BrowserTab> tabSet = isPrivate ? privateTabs : tabs;
        try {
            tabSet.add(index, tab);
        } catch (IndexOutOfBoundsException e) {
            tabSet.add(tab);
        }
        return tab;
    }

    public BrowserTab addTab(boolean isPrivate) {
        BrowserTab tab = new BrowserTab("", isPrivate);
        List<BrowserTab> tabSet = isPrivate ? privateTabs : tabs;
        tabSet.add(tab);
        return tab;
    }

    public void addToHistory(String urlString, boolean isPrivate) {
        if (!isPrivate) {
            history.add(urlString);
        }
    }

    public void displayPageOnStart(String urlString, boolean isPrivate) {
        BrowserTab tab = addTab(isPrivate, 0);
        tab.setUrlString(urlString);
        addToHistory(urlString, isPrivate);
    }

    public void displayPageOnEnd(String urlString, boolean isPrivate) {
        BrowserTab tab = addTab(isPrivate);
        tab.setUrlString(urlString);
        addToHistory(urlString, isPrivate);
    }

    public void displayPage(String urlString, boolean isPrivate, int index) {
        List<BrowserTab> tabSet = isPrivate ? privateTabs : tabs;

        try {
            BrowserTab tab = tabSet.get(index);
            tab.setUrlString(urlString);
            addToHistory(urlString, isPrivate);
        } catch (IndexOutOfBoundsException e) {
            displayPageOnEnd(urlString, isPrivate);
        }
    }

    public void updatePage(int index, boolean isPrivate) {
        BrowserTab tab = findTab(index, isPrivate);
        if (tab != null && !isPrivate) {
            history.add(tab.getUrlString());
        }
    }

    public BrowserTab closeTab(int index, boolean isPrivate) {
        BrowserTab tab = findTab(index, isPrivate);
        if (tab != null) {
            List<BrowserTab> tabSet = isPrivate ? privateTabs : tabs;
            tabSet.remove(index);
        }
        return tab;
    }

    private BrowserTab findTab(int index, boolean isPrivate) {
        try {
            List<BrowserTab> tabSet = isPrivate ? privateTabs : tabs;
            return tabSet.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No tab found at index " + index);
            return null;
        }
    }
}
