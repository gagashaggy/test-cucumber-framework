package framework.fields;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framework.utils.ActionWait;
import framework.utils.TestHelper;

public abstract class AbstractField {

    private String className;
    protected SelenideElement selenideElement;
    protected final String name;
    protected String xpathPath;
    protected String xpath;

    public AbstractField(SelenideElement selenideElement, String name) {
        this.selenideElement = selenideElement;
        this.name = name;
        this.className = this.getClass().getSimpleName();
    }

    public String getXpath() {
        return xpath;
    }

    public AbstractField setXpath(String xpath) {
        this.xpath = xpath;
        return this;
    }

//    public void refreshElement() {
//        By.ByXPath by = xpath == null ? getPath() : new By.ByXPath(getXpath());
//        LOG.debug(String.format("Элемент потерян, пробую обнаружить снова по локатору %s", by.toString()));
//        selenideElement = $(by);
//    }

    public boolean isEnabled() {
        return selenideElement.is(Condition.enabled);
    }

    public void click() {
        TestHelper.log(String.format("Нажимаю на %s", toString()));
        selenideElement.hover();
        selenideElement.click();
    }

    public void rightClick() {
        TestHelper.log(String.format("Нажимаю на %s правой кнопкой мыши", toString()));
        selenideElement.hover();
        selenideElement.contextClick();
    }

    public void fillUp(String s) {
        throw new IllegalStateException("Данный тип поля не подразумевает заполнение. Метод должен быть переопределён.");
    }

    public ElementsCollection getOptions() {
        throw new IllegalStateException("Метод должен быть переопределён.");
    }

    public String getCharCounterValue() {
        throw new IllegalStateException("Метод должен быть переопределён.");
    }

    public void clear() {
        throw new IllegalStateException("Данный тип поля не подразумевает очистку. Метод должен быть переопределён.");
    }

    public String getText() {
        return selenideElement.shouldBe(Condition.visible).getText();
    }

    public String getValue() {
        isDisplayed();
        return selenideElement.getValue();
    }

    public String getAttribute(String attribute) {
        return selenideElement.getAttribute(attribute);
    }

    public SelenideElement getWrappedElement() {
        return selenideElement;
    }

    public void validate() {
        throw new IllegalStateException("Валидация данного поля не доступна");
    }

    public boolean isActive() {
        return false;
    }

    public boolean isDisplayed() {
        return selenideElement.isDisplayed();
    }

    public boolean isDisplayed(boolean isVisible) {
        ActionWait wait = new ActionWait();
        return wait.safeCall(o -> selenideElement.isDisplayed() == isVisible);
    }

    public String getCssValue(String value) {
        return selenideElement.getCssValue(value);
    }

    public void hover() {
        selenideElement.shouldBe(Condition.enabled).shouldBe(Condition.visible).hover();
    }

    @Override
    public final String toString() {
        return String.format("%s - '%s'", className, name);
    }
}
