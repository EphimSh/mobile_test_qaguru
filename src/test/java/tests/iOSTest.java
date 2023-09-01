package tests;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
@Owner("EphimSh")
@Feature("browserstack: ios testing")
@Story("ios")
@Tag("ios")
public class iOSTest extends TestBase {

    @Test
    void sampleTest() {
        step("Переход на форму ввода текста", () ->
                $(AppiumBy.accessibilityId("Text Button")).click());
        step("Ввод текста", () ->
                $(AppiumBy.accessibilityId("Text Input")).sendKeys("ayo"));
        $(AppiumBy.accessibilityId("Text Input")).pressEnter();
        step("Проверка введенного ранее текста", () ->
                $(AppiumBy.accessibilityId("Text Output")).shouldHave(text("ayo")));
    }
}
