package framework.fields;

import com.codeborne.selenide.SelenideElement;

public class Text extends AbstractField {
    public Text(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
    }
}
