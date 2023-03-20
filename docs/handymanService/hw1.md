## HandymanService


### Проделанная работа в рамках hw1
* Реализован endpoint `/readiness`, который при вызове возвращает имя сервиса и статус `OK` (`{"handymanServie": "OK"}`)
* Реализован endpoint `/liveness`, который при вызове возвращает `HTTP.OK`.
* Добавлена поддержка метрик по адресу `/actuator/metrics`, которая выводит метрики в формате `prometheus`.
* Добавлен endpoint `/actuator/info`, который выводит информацию о проекте.
   ```json
   {
       "build":{
           "artifact":"demo",
           "name":"demo",
           "time":"2022-09-29T12:39:54.402Z",
           "version":"0.0.42-SNAPSHOT",
           "group":"com.example"
       }
   }
   ``` 