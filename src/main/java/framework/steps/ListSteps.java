package framework.steps;

import framework.factories.FieldFactory;
import framework.fields.AbstractField;
import framework.fields.ElementsList;
import framework.fields.ListItem;
import framework.utils.TestHelper;
import io.cucumber.java.ru.И;

import java.util.List;

public class ListSteps {

    @И("^в списке \"([^\"]*)\" курсор наводится на элемент \"(.*)\" с текстом \"(.*)\"$")
    public void hoverItemByExactText(String listName, String itemName, String text) {
        ElementsList list = (ElementsList)FieldFactory.getField(listName, TestHelper.getCurrentPage().getClass());
        List<AbstractField> items = FieldFactory.getFields(itemName, TestHelper.getCurrentPage().getClass());
        String xpath = String.format("%s[text()='%s']", items.get(0).getXpath(), text);
        AbstractField item = new ListItem(list.getWrappedElement().$x(xpath), itemName);
        TestHelper.log("Навожу курсор на элемент списка %s '%s' с xpath = %s", listName, itemName, xpath);
        item.hover();
    }
}
