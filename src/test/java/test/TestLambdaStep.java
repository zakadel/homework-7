package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TestLambdaStep {

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
        step("Open home page", () -> {
            open("https://github.com/");
        });
        step("Looking for a repository" + repository, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(repository);
            $(".header-search-input").submit();
        });
        step("Check if the repository is found " + repository, () -> {
            $(".repo-list").should(text("Docent321/qa_guru_2"));
        });
        step("Opening the repository " + repository, () -> {
            $(By.linkText("Docent321/qa_guru_2")).click();
        });
        step("Checking for tabs Issue", () -> {
            $("#issues-tab").should(text("issues"));
        });
    }
}
