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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        timeout=10000;
        holdBrowserOpen=false;
        reopenBrowserOnFail = true;
        fastSetValue=true;
        assertionMode= AssertionMode.SOFT;
        fileDownload= FileDownloadMode.HTTPGET;
        downloadsFolder="src/main/resources/images";

        screenshots=true;
        savePageSource = false;
        reportsFolder="src/main/resources/Reports";
    }

    @AfterTest
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
