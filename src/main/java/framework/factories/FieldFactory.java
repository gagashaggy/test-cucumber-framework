package framework.factories;

import com.codeborne.selenide.SelenideElement;
import framework.exception.AutotestError;
import framework.fields.AbstractField;
import framework.fields.ListItem;
import framework.pages.AbstractPage;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$x;

public class FieldFactory {

    /**
     * Получение со страницы элемента с аннотацией @PageLoadMark
     */
    public static AbstractField getPageLoadMark(Class<? extends AbstractPage> page) {
        Field fieldWithPageLoadMark = Arrays.stream(page.getDeclaredFields())
                .collect(Collectors.toList()).stream().filter(field -> field.isAnnotationPresent(PageLoadMark.class))
                .findFirst().orElseThrow(() -> new AutotestError(String.format("На странице [%s] отсутствует поле с аннотацией @PageLoadMark",
                        page.getAnnotation(PageEntry.class))));
        return fieldToAbstractField.apply(fieldWithPageLoadMark);
    }

    /**
     * Создание AbstractField через конструктор, используя SelenideElement и значение ElementTitle у Field
     */
    private static BiFunction<Field, SelenideElement, AbstractField> doAbstractField = (field, selenideElement) -> {
        try {
            return (AbstractField) field.getType().getConstructor(SelenideElement.class, String.class)
                    .newInstance(selenideElement, field.getAnnotation(ElementTitle.class).value());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new AutotestError("Ошибка создания объекта AbstractField", e);
        }
    };

    /**
     * Преобразование Field в AbstractField
     */
    private static Function<Field, AbstractField> fieldToAbstractField = field -> {
        String xpath = field.getAnnotation(FindBy.class).xpath();
        SelenideElement element = $x(xpath);
        return doAbstractField.apply(field, element).setXpath(xpath);
    };


    /**
     * Получение элемента на странице по его имени
     */
    public static AbstractField getField(String name, Class<? extends AbstractPage> page) {
        List<Field> fieldWithName = new ArrayList<>();
        Arrays.stream(page.getDeclaredFields())
                .collect(Collectors.toList()).stream()
                .filter(field -> field.isAnnotationPresent(ElementTitle.class))
                .forEach(fieldWithName::add);
        return fieldWithName.stream().filter(field -> field.getAnnotation(ElementTitle.class).value().equals(name))
                .map(fieldToAbstractField).findFirst().get();
    }

    /**
     * Получение всех элементов на странице по имени
     */
    public static List<AbstractField> getFields(String name, Class<? extends AbstractPage> page) {
        List<Field> fieldWithName = new ArrayList<>();
        Arrays.stream(page.getDeclaredFields())
                .collect(Collectors.toList()).stream()
                .filter(field -> field.isAnnotationPresent(ElementTitle.class))
                .filter(field -> field.getAnnotation(ElementTitle.class).value().equals(name))
                .forEach(fieldWithName::add);
        List<AbstractField> ret = fieldWithName.stream().map(fieldToAbstractField).collect(Collectors.toList());
        ret.forEach(field -> {
            if (field instanceof ListItem) {
                List<Field> fieldWithSubelements = new ArrayList<>();
                fieldWithName.stream().filter(f -> f.isAnnotationPresent(SubelementOf.class))
                        .forEach(fieldWithSubelements::add);
                ((ListItem) field).setBelongsTo(getField(fieldWithSubelements.get(0).getAnnotation(SubelementOf.class).value(), page));
            }
        });
        return ret;
    }
}
