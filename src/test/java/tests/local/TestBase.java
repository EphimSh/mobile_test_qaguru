package tests.local;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.AndroidLocalDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {




    @BeforeAll
    static void driverSetUp(){
        Configuration.browser = AndroidLocalDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 5000;
    }

    //костыль selenide
    //ни в коем случае не использовать в вэбавтоматизации
    //для мобильной автоматизации пойдет
    @BeforeEach
    void addListener(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach(){
        String sessionId = sessionId().toString();
        //todo add screenshot method
        Attach.pageSource();
        closeWebDriver();
//        Attach.addVideo(sessionId);
    }
}
