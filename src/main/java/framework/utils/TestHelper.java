package framework.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import framework.factories.PageFactory;
import framework.pages.AbstractPage;

public class TestHelper {
    // таймаут для ожидания
    public static final int TIMEOUT = 30;
    private static PageFactory pageFactory;

    public static void openBrowser(){
        PageFactory.init();
        Selenide.open("http://yandex.ru");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    public static void closeBrowser(){
        Selenide.closeWebDriver();
    }

    public static PageFactory getPageFactory() {
        if (null == pageFactory) {
            pageFactory = new PageFactory();
        }
        return pageFactory;
    }

    public static void log(String s, String... params) {
        System.out.println(String.format(s, params));
    }

    public static AbstractPage getCurrentPage() {
        return pageFactory.getCurrentPage();
    }
}
