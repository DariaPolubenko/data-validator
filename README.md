### Hexlet tests and linter status:
[![Actions Status](https://github.com/DariaPolubenko/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/DariaPolubenko/java-project-78/actions)


[![Test Coverage](https://api.codeclimate.com/v1/badges/7171d34baf2bd0f50816/test_coverage)](https://codeclimate.com/github/DariaPolubenko/java-project-78/test_coverage)
[![Maintainability](https://api.codeclimate.com/v1/badges/7171d34baf2bd0f50816/maintainability)](https://codeclimate.com/github/DariaPolubenko/java-project-78/maintainability)


## Описание
**Валидатор данных** - библиотека, которая проверяет корректность входящих данных. Поддерживает валидацию строковых и целочисленных значений, словарей Map.
Пользователь имеет возможность настраивать необходимую схему валидации самостоятельно.


## Подключение библиотеки
В консоль введите команду:
```bash
git clone git@github.com:DariaPolubenko/data-validator.git
cd data-validator
```
Затем введите следующую команду, чтобы создать JAR-файл:
```bash
./gradlew jar
```
Созданный файл находится в и дирректории _./build/libs_. Скопируйте его и положите себе в проект. Как правило, в корне проекта создается дирректория "libs", куда помещается скаченный jar-файл.

Далее необходимо подключить зависимость в конфигурационном файле.
1. Для **gradle-kotlin build system** добавьте в файл build.gradle.kts следующее:
```bash
dependencies {
   implementation(files("libs/имя_файла.jar"))
}
```
2. Для **gradle-groovy build system**, добавьте в файл build.gradle:
```bash
dependencies {
    implementation files('libs/имя_файла.jar')
}
```

## Использование
Создайте объект:
```bash
var v = new Validator();
```
Укажите тип проверяемых данных:
- _v.string() - для работы со строками;_
- _v.number() - для работы с числами;_
- _v.map() - для работы с объектами типа Map_

И составьте схему валидации:
```bash
var schema = v.string().required().minLength(5).contains("hex");
```
**СХЕМЫ ВАЛИДАЦИИ**
_(схемы проверок накапливаются на объектах)_

**1. Для строковыч значений:**
   - _required(): проверка на null или пустую строку_
   - _minLength(int n): проверка минимальной длины, принимает размер строки_
   - _contains(String str): проверка на содержимое указанных символов, принимает строку_
  
**2. Для целочисленныч значений:**
   - _required(): проверка на null,_
   - _positive(): проверка на знак числа_
   - _range(Integer min, Integer max): проверка допустимого диапазона значений, принимает два числа: минимально допустимое и максимально допустимое_

**3. Для словарей Map:**
   - _required(): проверка объекта на null_
   - _sizeof(Integer size): проверка размера Map, принимает размер Map_
   - _shape(Map<тип_проверяемых_данных, BaseSchema<String>>): метод проверки для вложенных значений в Maр. Проверяет соответсвует ли каждое значение Map переданным схемам
        Принимает Map с заданными схемами проверки: каждому свойству проверяемого объекта Map привязывается свой набор ограничений (своя схема), см. "пример валидации вложенных данных"_

Отправьте схему и данные на проверку, ипользуя метод isValid(объект_валидации):
```bash
schema.isValid("hexlet"); // true
```

## Пример валидации вложенных данных:
```bash
var v = new Validator(); //создаем объект
var schema = v.map(); // указываем тип проверяемых данных

Map<String, BaseSchema<String>> schemas = new HashMap<>(); //создаем объект Map
schemas.put("firstName", v.string().required()); // для каждого значения добавляем свои схемы для проверки
schemas.put("lastName", v.string().required().minLength(2));

schema.shape(schemas); //отправлем созданные схемы проверок

Map<String, String> human1 = new HashMap<>(); //создаем объект Map, который будем проверять на валидность
human2.put("firstName", "John");
human2.put("lastName", null);
schema.isValid(human2); // проверяем данные на проверку, результат работы метода в данном примере 'false'

Map<String, String> human3 = new HashMap<>();
human3.put("firstName", "Anna");
human3.put("lastName", "B");
schema.isValid(human3); // 'false'
```
