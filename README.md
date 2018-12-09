# 1819_4bhif_nvs_assignment06_jpa
Übungsbeispiel mit individueller Aufgabenstellung zum Thema JPA


# Aufgabenstellung

- Jakarta EE Applikation
- Erstellen Sie (oder erweitern Sie) Ihr individuelles Datenmodell mit mindestens zwei abgeleiteten Klassen und insgesamt ca. fünf Tabellen.
- Erstellen Sie eine CRUD-Applikation (restful services) für die vererbten Klassen. Weiters ausgehend von Ihrem individuellem Thema ev. noch weitere notwendige Endpunkte.
- Ein Datum muss in Ihrem REST-Endpoint
- Verwenden Sie eine DerbyDb sowie eine H2
- Erstellen Systemtests auf Basis JavaSE.

# Projektbeschreibung (in Englisch)

This is about a company that stores cars for customers. The company takes care of the
cars for the customer. There are Supercars, sportscars, Suvs and utility Vehicles. A car can
only be stored in a room with the same type of cars in it. A warehouse contains different
spaces for the different types of cars. The company has Warehouses located in different
cities throughout the country. Each Warehouse is managed by it's own manager.
If in one city, such as London, there are different warehouses, the manager can take care
of more than 1 warehouse. A customer Sometimes wants to store their cars in different
locations, so a customer always comunicates with the company, to get to the manager
of 1 location.

# Funktioniert nicht.

Die Tabelle Car enthält immer 2 foreign keys auf die Tabelle Customer. Warum weiß icht nicht.
