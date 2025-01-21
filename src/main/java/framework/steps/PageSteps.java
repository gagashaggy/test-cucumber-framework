package framework.steps;

import com.codeborne.selenide.WebDriverRunner;
import framework.pages.AbstractPage;
import framework.utils.TestHelper;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class PageSteps {

    @Тогда("^открывается \"([^\"]*)\"$")
    public void checkPageIsOpened(String pageName) {
        AbstractPage page = TestHelper.getPageFactory().getPage(pageName);
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(TestHelper.TIMEOUT));
        wait.until(webDriver -> page.isOpened());
    }
}
