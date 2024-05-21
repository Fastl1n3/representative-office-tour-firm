# Информационная система представительства туристической фирмы в зарубежной стране
Тур. фирма в России формирует **группу туристов** и данные на каждого туриста отправляют в представительство. Представительство на основе этих данных заполняет на каждого пакет документов, готовит списки расселения по разным гостиницам и **бронирует номера** в этих гостиницах.

Представительство предлагает **расписание экскурсий** и производит запись на определенные экскурсии. Составляется список: кто, на какие экскурсии едет и передается в агентство организации экскурсий. 
Туристическая группа делится на туристов, которые едут **отдохнуть** *(они больше интересуются экскурсиями и не интересуются складом)*, на туристов, которые едут **за грузом** *(они интересуются складом и не будут интересоваться экскурсиями)* и их детей.

*В функциональные обязанности представительства входит также:*  

**Хранение и отправка груза туристов.** На складе заводится на каждого туриста весовая ведомость, проводится взвешивание, упаковка груза. Для отправки груза составляется ведомость на каждого туриста, в ней указывается: количество мест, вес, стоимость упаковки, страховки, итоговая сумма.    

Предоставление полного **финансового отчета** в головную фирму. Все статьи расхода и дохода переносятся в финансовый отчет.

**Виды запросов в информационной системе:**
1. Сформировать список туристов для таможни в целом и по указанной категории.
2. Сформировать списки на расселение по указанным гостиницам в целом и указанной категории.
3. Получить количество туристов, побывавших в стране за определенный период в целом и по определенной категории.
4. Получить сведения о конкретном туристе: сколько раз был в стране, даты прилета/отлета, в каких гостиницах останавливался, какие экскурсии и в каких агентствах заказывал, какой груз сдавал.
5. Получить список гостиниц, в которых производится расселение туристов, с указанием количества занимаемых номеров и проживавших в них человек за определенный период.
6. Получить общее количество туристов, заказавших экскурсии за определенный период.
7. Выбрать самые популярные экскурсии и самые качественные экскурсионные агентства.
8. Получить данные о загрузке указанного рейса самолета на определенную дату: количество мест, вес груза, объемный вес.
9. Получить статистику о грузообороте склада: количество мест и вес груза, сданного за определенный период, количество самолетов, вывозивших этот груз, сколько из них грузовых, а сколько грузо-пассажирских.
10. Получить полный финансовый отчет по указанной группе в целом и для определенной категории туриcтов.
11. Получить данные о расходах и доходах за определенный период: обслуживание самолета, гостиница, экскурсии, визы, расходы представительства и т.п.
12. Получить статистику по видам отправляемого груза и удельную долю каждого вида в общем грузопотоке.
13. Вычислить рентабельность представительства (соотношение доходов и расходов).
14. Определить процентное отношение отдыхающих туристов к туристам shop-туров в целом и за указанный период (например, в зависимости от времени года).
15. Получить сведения о туристах указанного рейса: список группы, гостиницы, груз, бирки, маркировка.

# Клиентское веб-приложение
## Стек:
* Java
* Spring Boot
* Spring Data JDBC
* Spring Web
* Thymeleaf
* PostgreSQL