package framework.factories;

import framework.exception.AutotestError;
import framework.pages.AbstractPage;
import framework.utils.TestHelper;
import org.openqa.selenium.TimeoutException;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PageFactory {

    private static AbstractPage currentPage;
    private static int currentTab = 0;
    private static final String PAGES_PACKAGE = "framework.pages";
    private static Reflections reflections = new Reflections(PAGES_PACKAGE);
    private static final Map<String, List<Class<? extends AbstractPage>>> ALL_PAGES = new HashMap<>();
    private static String currentPageName;

    public static void init() {
        createPagesList();
    }

    private static void set(AbstractPage page) {
        currentPage = page;
        TestHelper.log("Текущая страница %s", page.getTitle());
    }

    public static AbstractPage getPage(String name) throws TimeoutException {
        currentPageName = name;
        return get(name);
    }

    private static <T> T getPage(Class<T> clazz) throws TimeoutException {
        final T page = construct((Class<? extends AbstractPage>) clazz);
        set((AbstractPage) page);
        return page;
    }

    private static AbstractPage get(String name) throws TimeoutException {
        if (ALL_PAGES.containsKey(name)) {
            final Class<? extends AbstractPage> clazz = ALL_PAGES.get(name).get(0);
            return getPage(clazz);
        } else {
            throw new AutotestError(String.format("Страница [%s] не найдена. Убедитесь в наличии аннотации @PageEntry у этой страницы.", name));
        }
    }

    /**
     * Сбор всех классов, унаследованных от AbstractPage и имеющих аннотацию @PageEntry в коллекцию ALL_PAGES, где
     * key - название страницы, value - группа страниц вида "главная страница и, при наличии, ее аффилированные страницы"
     */
    private static void createPagesList() {
        Predicate<Class<? extends AbstractPage>> hasPageEntry = page -> page.isAnnotationPresent(PageEntry.class);

        Function<Class<? extends AbstractPage>, List<Class<? extends AbstractPage>>> createPagesList = page -> {
            List<Class<? extends AbstractPage>> linkedPages = new ArrayList<>();
            linkedPages.add(page);
            return linkedPages;
        };

        Consumer<List<Class<? extends AbstractPage>>> addToAll_Pages = pages ->
                ALL_PAGES.put(pages.get(0).getAnnotation(PageEntry.class).title(), pages);

        reflections.getSubTypesOf(AbstractPage.class).stream().filter(hasPageEntry).map(createPagesList).forEach(addToAll_Pages);
    }

    private static <T> T construct(Class<? extends AbstractPage> clazz) throws TimeoutException {
        try {
            return (T) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new AutotestError("При создании страницы произошла ошибка.", e);
        }
    }

    public static int getCurrentTab() {
        return currentTab;
    }

    public static void setCurrentTab(int currentTab) {
        PageFactory.currentTab = currentTab;
    }

    public AbstractPage getCurrentPage() {
        return currentPage;
    }
}
