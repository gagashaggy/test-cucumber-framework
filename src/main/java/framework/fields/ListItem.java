package framework.fields;

import com.codeborne.selenide.SelenideElement;

public class ListItem extends AbstractField {

    private ElementsList belongsTo;

    public ListItem(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
    }

    public ElementsList getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(AbstractField belongsTo) {
        this.belongsTo = (ElementsList)belongsTo;
    }
}
