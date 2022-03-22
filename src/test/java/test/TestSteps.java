package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestSteps {

    private static final String repository = "Docent321/qa_guru_2";

    @BeforeAll
    static void preconditionAll() {
        Configuration.browserSize = "1920x1280";
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @Test
    void entryPoint() {
        openMainPage();
        searchForRepository(repository);
        checkRepository(repository);
        openRepository(repository);
        checkIssueTab();
    }

    @Step("Open home page")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Looking for a repository {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
    }

    @Step("Check if the repository is found {repo}")
    public void checkRepository(String repo) {
        $(".repo-list").should(text("Docent321/qa_guru_2"));
    }

    @Step("Opening the repository {repo}")
    public void openRepository(String repo) {
        $(By.linkText(repo)).click();
    }

    @Step("Checking for tabs Issue")
    public void checkIssueTab() {
        $("#issues-tab").should(text("issues"));
    }
}
