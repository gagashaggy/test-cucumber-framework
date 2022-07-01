package framework.pages.yandex.market;

import framework.factories.ElementTitle;
import framework.factories.PageEntry;
import framework.factories.PageLoadMark;
import framework.fields.Text;
import framework.pages.AbstractPage;
import org.openqa.selenium.support.FindBy;

@PageEntry(title = "Страница списка товаров")
public class GoodsPage extends AbstractPage {
    @PageLoadMark
    @ElementTitle("Заголовок страницы списка товаров")
    @FindBy(xpath = "//div[@data-grabber='SearchTitle']/span")
    private Text header;
}
