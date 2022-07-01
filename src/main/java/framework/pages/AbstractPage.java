package framework.pages;

import framework.factories.FieldFactory;
import framework.factories.PageEntry;
import framework.fields.AbstractField;
import framework.utils.TestHelper;

public abstract class AbstractPage {

    public Boolean isOpened() {
        AbstractField mark = FieldFactory.getPageLoadMark(this.getClass());
        TestHelper.log("Загрузка страницы определяется полем %s", mark.toString());
        return mark.isEnabled() && mark.isDisplayed();
    }

    public AbstractField getField(String name) {
        return FieldFactory.getField(name, this.getClass());
    }

    public String getTitle() {
        return this.getClass().getAnnotation(PageEntry.class).title();
    }
}
