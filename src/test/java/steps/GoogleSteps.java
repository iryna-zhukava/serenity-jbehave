package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import ui.GoogleSearchPage;

import static com.codeborne.selenide.Selenide.open;

public class GoogleSteps extends Steps {

    public static final String GOOGLE_URL = "https://www.google.com";

    @Given("google is opened")
    public void openGoogle() {
        open(GOOGLE_URL);
    }

    @When("User searches for $text")
    public void searchGoogle(String text) {
        GoogleSearchPage page = new GoogleSearchPage();
        page.fillSearchField(text);
    }

    @When("User clicks search button")
    public void clickSearchButton() {
        GoogleSearchPage page = new GoogleSearchPage();
        page.clickSearchButton();
    }
}
