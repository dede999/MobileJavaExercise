package org.example.records;
import lombok.Data;

@Data
public class BrowserTab {
    private String urlString;
    private boolean isPrivate;

    public BrowserTab(String urlString, boolean isPrivate) {
        this.urlString = urlString;
        this.isPrivate = isPrivate;
    }
}
