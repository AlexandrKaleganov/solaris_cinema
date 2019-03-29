[![Build Status](https://travis-ci.org/AlexandrKaleganov/solaris_cinema.svg?branch=master)](https://travis-ci.org/AlexandrKaleganov/solaris_cinema)
[![codecov](https://codecov.io/gh/AlexandrKaleganov/solaris_cinema/branch/master/graph/badge.svg)](https://codecov.io/gh/AlexandrKaleganov/solaris_cinema)

#solaris_cinema

В этом задании вам нужно разработать простой веб сайт по покупки билетов в кинотеатр.
Начальная страница сайта index.html. 
payment.html  - ввод имени пользователя и номера телефона, форматный контроль производится с помощью 
регулярных выражений в javascript, скрипты лежат в папке js 
Сервлеты: тут всё просто HallServlet и его гет запрос отвечает за  получение списка занятых мест, и с 
шагомв 1 секунду информаци отрисовывается на главной странице, при выборе места место мы сохаяем в сессию
и переходим к странице для внесения ФИО и номера телефона. После ввода имени и номера отправляем запрос где идёт
 проверка есть ли место в сессии и если есть то из сессии берётся место и идёт попытка добавить в базу после успешного добавления 
 переход на новую страницу в случае ошибки получаем объект json формата   в виде уведомления что не получилось купить билет 
В курсе производилась работа с ветках git.


![Alt text](https://github.com/AlexandrKaleganov/solaris_cinema/blob/master/img/1.png "Optional Title")
![Alt text](https://github.com/AlexandrKaleganov/solaris_cinema/blob/master/img/2.png "Optional Title")
![Alt text](https://github.com/AlexandrKaleganov/solaris_cinema/blob/master/img/3.png "Optional Title")
![Alt text](https://github.com/AlexandrKaleganov/solaris_cinema/blob/master/img/4.png "Optional Title")