## VOgorode

### Использование

* Приложения запускаются на портах `8080`, `8081` и `8082`. Для смены порта необходимо в файле `application.yml` изменить номер порта.
* сгенерировать gRPC сервисы с помощью команды `/.gradlew generateProto`
* Запустить желаемый сервис `cd $SERVICE && ./gradlew bootRun`, где `SERVICE` это либо handyman, либо rancher, либо landscape.

### Ссылки
* [/docs](/docs)
* [/запуск](/dev/readme.md)
