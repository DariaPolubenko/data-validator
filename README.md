### Hexlet tests and linter status:
[![Actions Status](https://github.com/DariaPolubenko/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/DariaPolubenko/java-project-78/actions)


[![Test Coverage](https://api.codeclimate.com/v1/badges/7171d34baf2bd0f50816/test_coverage)](https://codeclimate.com/github/DariaPolubenko/java-project-78/test_coverage)
[![Maintainability](https://api.codeclimate.com/v1/badges/7171d34baf2bd0f50816/maintainability)](https://codeclimate.com/github/DariaPolubenko/java-project-78/maintainability)
[![Action Test](https://github.com/DariaPolubenko/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/DariaPolubenko/java-project-78/actions)


## Описание
**Валидатор данных** - библиотека, которая проверяет корректность входящих данных. Поддерживает валидацию строковых и целочисленных значений, словарей Map.
Пользователь имеет возможность настраивать необходимую схему валидации.

### Схемы валидации

**Строковые значения:**
   - Required: проверка на null или пустую строку
   - MinLength: проверка минимальной длины, принимает размер строки
   - Contains: проверка на содержимое указанных символов, принимает строку
  
**Целочисленные значения:**
   - Required: проверка на null,
   - Positive: проверка на знак числа
   - Range: проверка допустимого диапазона значений, принимает диапозон чисел

**Словари Map:**
   - Required: проверка объекта на null
   - Sizeof: проверка размера Map, принимает размер Map
   
**Проверка вложенных данных в Map:**
   - Shape: проверяет соответсвуют ли значения Map заданным схемам, принимает Map для проверки валидности ее значений
     Каждому свойству объекта Map привязывается свой набор ограничений (своя схема), что позволяет более точно контролировать данные


## Установка
В консоль введите команду:
```bash
git clone git@github.com:DariaPolubenko/java-project-78.git
```


## Использование
Создайте объект Валидатора:
```bash
var v = new Validator();
```
Укажите тип проверяемых данных и составьте схему валидации
```bash
var schema = v.v.string().required().minLength(5).contains("hex");
```
```bash
Отправьте схему и данные на проверку, ипользуя метод isValid:
schema.isValid("hexlet"); // true
```

## Пример валидации вложенных данных:
```bash
var v = new Validator();
var schema = v.map();

Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));

schema.shape(schemas);

Map<String, String> human1 = new HashMap<>();
human2.put("firstName", "John");
human2.put("lastName", null);
schema.isValid(human2); // false

Map<String, String> human3 = new HashMap<>();
human3.put("firstName", "Anna");
human3.put("lastName", "B");
schema.isValid(human3); // false
```
