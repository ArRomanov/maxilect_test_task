# Автоматические тесты для веб-сервиса http://litecart.stqa.ru

## Инструкция по запуску

Перед запуском тестов необходимо установить [java 8](https://www.oracle.com/technetwork/java/javase/downloads/2133151) и [allure commandline](https://docs.qameta.io/allure/#_installing_a_commandline)

* Склонировать репозиторий `git clone https://github.com/ArRomanov/maxilect_test_task.git`
* Перейти в директорию с проектом
* Выполнить команду `./gradlew test` (`./gradlew.bat test`, если запускается из windows)
* Для генерации отчета выполнить команду `./gradlew allureReport`
* Отчет можно посмотреть в директории `build/reports/allure-report`  