package tests;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;
@Owner("EphimSh")
@Feature("browserstack: wikipedia title search")
@Tag("successful search")
public class SearchTest extends TestBase{
    @Test
    void successfulSearchTest() throws MalformedURLException, InterruptedException {
        step("type search", ()->{
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Appium");
        });
        step("verify content found", () ->{
            $$(AppiumBy.id("org.wikipedia.alpha:id/search_container"))
                    .shouldHave(sizeGreaterThan(0));
        });
    }

    @Test
    void successfulSearchTest1() throws MalformedURLException, InterruptedException {
        step("type search", ()->{
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Android");
        });
        step("verify content found", () ->{
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_container"))
                    .shouldHave(sizeGreaterThan(0));
        });
    }
}