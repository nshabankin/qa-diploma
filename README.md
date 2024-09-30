# Автоматизация тестирования приложения `aqa-shop.jar`
Инструкция по запуску
---
## 1. ReportPortal Docker-контейнер

---

Подробные инструкции по интеграции ReportPortal в проект можно найти [здесь](https://github.com/nshabankin/qaa-hw-09-02/blob/main/README.md).

Из папки, содержащей `docker-compose.yml` с конфигурацией ReportPortal, необходимо запустить команду ```docker-compose up```. После загрузки контейнера, интерфейс ReportPortal будет доступен локально по адресу `localhost:8080` по умолчанию.

Для связи API ReportPortal с проектом необходимо будет сгенерировать [API Key](http://localhost:8080/ui/#userProfile/apiKeys) и интегрировать его в файл конфигурации [`reportportal.properties`](https://github.com/nshabankin/qa-diploma/blob/main/src/test/resources/reportportal.properties).

Во избежание конфликтов портов ReportPortal и портов приложения, внесены изменения в файл конфигурации приложения [`application.properties`](https://github.com/nshabankin/qa-diploma/blob/83f8f42c296b4a01a1a2cd7771faf6b2aa02946a/application.properties#L6) с указанием порта приложения `8082`.

После этого, при запуске автотестов или сборке проекта отчёты будут логироваться в [ReportPortal](http://localhost:8080/ui/#default_personal/launches/all).

## 2. Docker-контейнеры приложения

---

Для запуска контейнеров приложения необходимо перейти в папку с проектом, содержащую [`docker-compose.yml`](https://github.com/nshabankin/qa-diploma/blob/main/docker-compose.yml) с конфигурацией контейнеров и запустить команду ```docker-compose up```.

Командой будут созданы:
1. Окружение: `qa-diploma_app-network`
2. Каталоги: `qa-diploma_postgres_data` и `qa-diploma_mysql_data`
3. Контейнер Postgres БД: `postgres`
4. Контейнер MySQL БД: `mysql`
5. Контейнер симулятора банковских транзакций node.js: `gate-simulator`

Об успешности запуска контейнеров будут свидетельствовать записи в логах Docker-контейнеров:
1. `MySQL DB`: 
```
<дата> <время> 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '9.0.1'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server - GPL.
```

2. `Postgres DB`: 
```
<дата> <время> LOG:  database system is ready to accept connections
```
3. `Gate-simulator`:
```
<дата> <время> [nodemon] starting `node app.js`
<дата> <время> [
<дата> <время>   { number: '4444 4444 4444 4441', status: 'APPROVED' },
<дата> <время>   { number: '4444 4444 4444 4442', status: 'DECLINED' }
<дата> <время> ] 
```

## 3. Приложение `aqa-shop.jar`

---

Для запуска приложения покупки тура `aqa-shop.jar` необходимо запустить из папки проекта команду ```java -jar ./aqa-shop.jar```.

Об успешности запуска приложения будет свидетельствовать запись в логе:
```
<дата> <время>  INFO 53348 --- [           main] ru.netology.shop.ShopApplication         : Started ShopApplication in 11.893 seconds (JVM running for 15.152)
```

После этого приложение будет доступно локально по адресу `localhost:8082`.

## 4. Сборка проекта и отчёт в Gradle

---

Для чистого запуска сборки необходимо запустить из папки с проектом команду ```./gradlew clean build --refresh-dependencies```.

После обновления зависимостей, прохождения тестов и сборки проекта (успешной или неуспешной) будет сформирован отчет в Gradle в проекте по адресу `./build/reports/tests/test/index.html`

## 5. Отчёт в Allure

---

Для формирования отчёта в Allure, после попытки сборки в Gradle, необходимо запустить команду `./gradlew allureReport` из папки с проектом.

После выполнения команды отчёт будет доступен по адресу `./build/reports/allure-report/allureReport/index.html`.

## 6. Отчёт в ReportPortal

---

При запущенном контейнере ReportPortal и связи проекта с API ReportPortal при помощи API-ключа после каждого из запусков сборки формируется отчет по адресу `http://localhost:8080/ui/#default_personal/launches/all`.
