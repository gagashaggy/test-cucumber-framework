package framework.pages.yandex;

import framework.factories.ElementTitle;
import framework.factories.PageEntry;
import framework.factories.PageLoadMark;
import framework.fields.Button;
import framework.fields.Picture;
import framework.pages.AbstractPage;
import org.openqa.selenium.support.FindBy;

@PageEntry(title = "Главная страница Яндекса")
public class YandexMainPage extends AbstractPage {

    @PageLoadMark
    @ElementTitle("Логотип Яндекса")
    @FindBy(xpath = "//*[@class='home-logo__default']")
    private Picture logo;

    @ElementTitle("Маркет")
    @FindBy(xpath = "//*[@class='services-new__icon services-new__icon_market']")
    private Button market;
}
