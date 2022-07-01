package framework.steps;

import framework.exception.AutotestError;
import framework.utils.TestHelper;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;

import static framework.utils.TestHelper.getCurrentPage;
import static java.lang.String.format;

public class FieldSteps {

    @И("^нажимается (?:кнопка|поле|элемент) \"([^\"]*)\"$")
    public void clickField(String fieldTitle) {
        getCurrentPage().getField(fieldTitle).click();
    }

    @И("^(не |)отображается (кнопка|поле|элемент) \"([^\"]*)\"$")
    public void fieldIsDisplayed(String visible, String elementType, String fieldTitle) {
        boolean isVisible = visible.isEmpty();
        boolean checkedProperty = getCurrentPage().getField(fieldTitle).isDisplayed(isVisible);
        if (checkedProperty != isVisible)
            throw new AutotestError(format("%s %s %s отображается на странице", elementType, fieldTitle, isVisible ? " не" : ""));
    }

    @Тогда("значение поля \"([^\"]*)\" равно \"(.*)\"$")
    public void fieldValueEqualsTo(String fieldTitle, String value) {
        String fieldValue = getCurrentPage().getField(fieldTitle).getValue();
        if (fieldValue == null) {
            throw new AutotestError(String.format("У поля [%s] отсутствует значение. \nУбедитесь, что Вы проверяете значение, а не текст. \nДля проверки текста поля воспользуйтесь шагом \"и текст поля <> равен <>\".", fieldTitle));
        }
        TestHelper.log("Проверям, что значение поля %s равно %s", fieldTitle, value);
        Assert.assertEquals(String.format("Ожидаемое значение [%s] отличается от действительного [%s]", value, fieldValue),
                value, fieldValue);
    }

    @Тогда("текст поля \"([^\"]*)\" равен \"(.*)\"$")
    public void fieldTextEqualsTo(String fieldTitle, String value) {
        String textField = getCurrentPage().getField(fieldTitle).getText();
        Assert.assertEquals(String.format("Текст поля %s не равен ожидаемому тексту [%s]. Фактический текст в поле: [%s]",
                fieldTitle, value, textField), textField, value);
    }
}
