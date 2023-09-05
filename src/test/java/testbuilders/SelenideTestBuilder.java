package testbuilders;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;

import static com.codeborne.selenide.Configuration.*;

public class SelenideTestBuilder {
    public SelenideTestBuilder() {
        baseUrl = " http://the-internet.herokuapp.com";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        timeout=10000;
        holdBrowserOpen=false;
        screenshots=true;
        reopenBrowserOnFail = true;
        fastSetValue=true;
        assertionMode= AssertionMode.SOFT;
        fileDownload= FileDownloadMode.HTTPGET;
        reportsFolder="src/main/resources/failedScreens";
        downloadsFolder="src/main/resources/images";
    }

    @AfterTest
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
