package hexlet.code.schemas;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.function.Predicate;

public class BaseSchema<T>  {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean isNotNull;

    protected void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public boolean isValid(T value) {
        if (value == null & isNotNull) {
            return false;
        }
        return checks.values().stream()
                    .allMatch(fn -> fn.test(value));
    }
}
/*
Вновь здравствуй, Дорогой наставник!
    <Минутка нытья>
        Честно говоря, этот проект тоже был не прост.
        Если бы не база знаний, что накопилась в "Mattermost" - решила бы по-другому данный проект:
        без использования дженериков, и mapы, а с ипользованием переменных в каждом классе
        (это более неудобный вариант, было бы слишком много запутанного кода и доп. методов,
        и isValid бы в таком случае не реализовался в родительском классе).

        Как грустно, когда не приходишь к верному варианту с первого раза! :D
        А так хотелось!

        Также, если бы не подсказки в чате - думаю, что не реализовала бы 5ый шаг.
        Чувствую, что еще плаваю в абстрактном программировании.
     </Минутка нытья закончилась>

     Ладно, головой я понимаю, что это неотъемлемый процесс разработки:
     Учусь с этим справляться и не перекладывать все на свое слабоумие :D

     В который раз спасибо за наставничество!
     После ревью замечаю, как качество кода в последующих проектах улучшается!
     Спасибо тебе за этот вклад!
 */
