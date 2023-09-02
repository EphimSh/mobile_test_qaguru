package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.WebConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;


public class AndroidLocalDriver implements WebDriverProvider {
    static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setPlatformVersion("14.0")
                .setDeviceName("Pixel_3a_API_34_extension_level_7_x86_64")
                .setApp(getAppPath())
                .setAppPackage("org.wikipedia.alpha")
                .setAppActivity("org.wikipedia.main.MainActivity");

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAppPath() {

        String appUrl = "https://download.apkcombo.com/org.wikipedia.alpha/Wikipedia%20Alpha_2.7.50447-alpha-2023-07-14_apkcombo.com.apk?" +
                "ecp=b3JnLndpa2lwZWRpYS5hbHBoYS8yLjcuNTA0NDctYWxwaGEtMjAyMy0wNy0xNC81MDQ0Ny40ZjgzOWVkZjkxMmM2NWE5YzEwY2Y1OWY2OTVmZjU5M2ZmOTdlYjQzLmFwaw==&" +
                "iat=1693656833&sig=f2050a5f81f45c98e29fe97e69cc4459&size=17316713&from=cf&" +
                "version=old&" +
                "lang=ru&" +
                "fp=0edd4a5e65605dcebe2e557d70a7d93c&" +
                "ip=95.55.146.132";

        //path to application file
        String appPath = "src/test/resources/apps/Wikipedia Alpha_2.7.50447-alpha-2023-07-14_apkcombo.com.apk";

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }
}
