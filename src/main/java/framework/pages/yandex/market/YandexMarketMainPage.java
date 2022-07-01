package framework.pages.yandex.market;

import framework.factories.ElementTitle;
import framework.factories.PageEntry;
import framework.factories.PageLoadMark;
import framework.factories.SubelementOf;
import framework.fields.Button;
import framework.fields.ElementsList;
import framework.fields.ListItem;
import framework.fields.Text;
import framework.pages.AbstractPage;
import org.openqa.selenium.support.FindBy;

@PageEntry(title = "Главная страница Яндекс Маркета")
public class YandexMarketMainPage extends AbstractPage {

    @PageLoadMark
    @ElementTitle("Заголовок первой категории")
    @FindBy(xpath = "//h2")
    private Text logo;

    @ElementTitle("Каталог")
    @FindBy(xpath = "//span[text()='Каталог']")
    private Button catalogue;

    @ElementTitle("Меню категорий")
    @FindBy(xpath = "//div[@data-apiary-widget-name='@MarketNode/HeaderCatalog']//ul[1]")
    private ElementsList categoriesMenu;

    @ElementTitle(value = "Категория в меню")
    @SubelementOf("Меню категорий")
    @FindBy(xpath = "./li/a/span")
    private ListItem category;

    @ElementTitle("Заголовок категории Электроника")
    @FindBy(xpath = "//a[text()='Электроника']")
    private Text categoryElectronicsTitle;

    @ElementTitle("Заголовок категории Электроника")
    @FindBy(xpath = "//a[text()='Компьютеры']")
    private Text categoryComputersTitle;

    @ElementTitle("Видеокарты")
    @FindBy(xpath = "//a[text()='Видеокарты']")
    private Text videocards;
}
