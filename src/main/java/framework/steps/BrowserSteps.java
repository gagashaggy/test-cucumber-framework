package framework.steps;

import com.codeborne.selenide.Selenide;
import framework.factories.PageFactory;
import io.cucumber.java.ru.И;

public class BrowserSteps {
    @И("^пользователь переходит на открывшуюся вкладку$")
    public void switchOnNew_Tab() {
        int newTabIndex = PageFactory.getCurrentTab() + 1;
        Selenide.switchTo().window(newTabIndex);
        PageFactory.setCurrentTab(newTabIndex);
    }
}
