package ui;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GoogleSearchPage {

    public static final By SEARCH_FIELD = By.cssSelector("[type=\"text\"]");
    public static final By SUBMIT_BUTTON = By.cssSelector("[type=\"submit\"]");

    public void fillSearchField(String text) {
        SelenideElement searchField = $(SEARCH_FIELD);
        searchField.setValue(text);
    }

    public void clickSearchButton() {
        SelenideElement submitButton = $(SUBMIT_BUTTON);
        submitButton.click();
    }
}
