package schemas;

import java.util.Map;
import java.util.LinkedHashMap;

import java.util.function.Predicate;

public abstract class BaseSchema<T>  {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public BaseSchema<T> required() {
        Predicate<T> fn = value -> {
            if (value instanceof String) {
                if (value.equals("")) {
                    return false;
                }
            }
            if (value == null) {
                return false;
            }
            return true;
        };
        addCheck("required", fn);
        return this;
    }

    public boolean isValid(T text) {
        return checks.values().stream()
                .allMatch(value -> value.test(text));
    }

    public abstract BaseSchema<T> minLength(int length);
    public abstract BaseSchema<T> contains(String characters);
}

/*
Вновь здравствуй, Дорогой наставник!
    <Минутка нытья>
        Честно говоря, этот проект тоже был не прост.
        Если бы не база знаний, что накопилась в "Mattermost" - этот проект я бы решила по-другому:
        без использования дженериков, и mapы, а с ипользованием переменных в каждом классе
        (это более неудобный вариант, и isValid бы в таком случае не реализовала в родительском классе).
        Как грустно, когда не приходишь к верному варианту с первого раза! :D
        А так хотелось бы!
     </Минутка нытья закончилась>

     Ладно, головой я понимаю, что это неотъемлемый процесс разработки:
     Когда делаешь и под конец выясняется, что нужно все переделать.
     Учусь с этим справляться и не перекладывать на свое слабоумие :D

     В который раз спасибо за наставничество!
     После твоих ревью замечаю, как качество кода в последующих проектах улучшается!
     Спасибо тебе за этот вклад!
 */

