## Cервис почтовых уведомлений

Рассылать почтовые уведомления представителям клиентов заказчика, подписанным на определенные этапы обработки пользовательских данных.

## Зависимости

* [Scala 2.13.1](https://scala-lang.org)
* [Akka 2.6.14](https://akka.io)
* [Apache Commons Email 1.5](https://commons.apache.org/proper/commons-email/)
* [HOCON 1.4.1](https://github.com/lightbend/config)
* [ScalaTest 3.1.0](https://www.scalatest.org/)

## Реализация

Точка входа находится в объекте `org.dedkot.Main`.

Список акторов:
* `Guard` - главный актор, порождающий `Listener` и `Mailer`, выполняющий их конфигурацию.
* `Listener` - актор, выполняющий чтение событий в системе (в текущей версии выполняется случайная генерация событий раз в 15 секунд).
* `Mailer` - актор, принимающий сообщения от `Listener`, отправляет почту по имеющимся email адресам в соответствии с их принадлежностью к клиентам и наличию подписки на определенные события.

Конфигурация Email задаётся в файле `application.conf`.
Список клиентов читается из конфигурации `clients.conf`.

## Запуск

Требуется `sbt 1.4.7`.

В папке с проектом запустить команду
```
sbt run
```

Пример вывода
```
...
[2021-04-23 12:45:46,502] [INFO] [org.dedkot.Listener$] [akka://Guard/user/listener] - Received event: 0003-00001 STAGE4
[2021-04-23 12:45:46,502] [INFO] [org.dedkot.Mailer] [akka://Guard/user/mailer] - Received event: 0003-00001 STAGE4
[2021-04-23 12:45:52,908] [INFO] [org.dedkot.Mailer] [akka://Guard/user/mailer] - Sent email to test@test.test with event: 0003-00001 STAGE4
[2021-04-23 12:46:01,501] [INFO] [org.dedkot.Listener$] [akka://Guard/user/listener] - Received event: 0003-00009 STAGE2
[2021-04-23 12:46:01,501] [INFO] [org.dedkot.Mailer] [akka://Guard/user/mailer] - Received event: 0003-00009 STAGE2
[2021-04-23 12:46:16,510] [INFO] [org.dedkot.Listener$] [akka://Guard/user/listener] - Received event: 0004-00006 STAGE1
...
```
