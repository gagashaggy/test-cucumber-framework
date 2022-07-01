package framework.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public class ActionWait extends WebDriverWait {

    public ActionWait() {
        super(WebDriverRunner.getWebDriver(), TestHelper.TIMEOUT);
    }

    /**
     * Вызывает функцию, если время вышло возвращает false
     *
     * @param function
     * @return result у функции, если время вышло, то - false
     */
    public Boolean safeCall(Function<Object, Boolean> function) {
        try {
            return until(function);
        } catch (TimeoutException ignore) {
            return false;
        }
    }
}
