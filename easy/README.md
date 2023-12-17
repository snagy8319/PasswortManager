# Passwort Manager

Projektname

EasyPass

![Diagramm](./doc/architektur.drawio.png)

## Anforderungen

### Anforderungsliste für einen Password Manager

> Hier werden die Anforderungen aufgenommen. Als Beispiele können die Ergebnisse aus dem Internet dienen,
Siehe unten [link zu](#allgemeine-anforderungen)

# TODO: @Dominic, Ihsan

* Aufgabenaufteilung:
  * @Sebastian:
    * WEB API in C#
  * @Dominic
    * ?
  * @Ihsan
    * ?

### Required

* Allgemeine Anforderungen
  * Benutzeroberfläche, die einfach zu bedienen ist.
  * Daten Speichern und Abfragen über eine Web API
  * Login für Benutzer mit Tokengenerierung für die Abfrage von Daten von API
  * Erstellen von Benutzer
* Passwortverwaltung
  * Komplexe Passwordgenerierung
  * Speichern von Passwörter
  * Ändern von Passwörter
  * Anzeigen History Passwörter
* Benutzerfreundlichkeit
  * Loging / Logout User
  * User erstellen
  * User löschen
  * Kommanodzeile
* Zusätzliche Funktionen
* Datenschutz und Compliance

## Architektur

> #### Client --> Der Client beinhaltet den Quellcode für den Passwort-Manager

> #### Server --> Der Server kümmert sich um die Datenhaltung sowie das Speichern und Ausgeben der Daten

##### UML für Client

```mermaid

classDiagram

    PasswordManager --> User : use User
    PasswordManager --> Users : has Users
    PasswordManager --> Passwords : has Passwords
    Passwords "1" --> "0..*"  Password : has Password
    Passwords --> PasswordGenerator : uses

    Users "1" --> "0..*" User : has User


    class User{
     + UID id
     + String username
     + String loginPassword
    }

    class Users {
      + List<User> Users
      + User getUser(UID id)
      + User addUser(User user)
      + User deleteUser(UID id)
      + User updateUser(UID id, User updatedUser)
    }

    class Password{
      + UID id
      + String website
      + String username
      + String password
      + String notes
      + String getWebsite()
      + String getUsername()
      + String getPassword()
      + String getNotes()
    }

    class Passwords {
      + List<Password> passwords
      + List<Password> getAllPasswords()
      + List<Password> deletePassword(UID id)
      + List<Password> addPassword(Password password)
      + List<Password> updatePassword(UID id, Password updatedPassword)
    }

    class PasswordGenerator{
      + String ZEICHEN
      + String generierePin()
    }

    class PasswordManager{
      + List<User> users
      + List<Password> passwords
      + User currentUser
      + void logoutUser()
      + boolean loginUser(User user)
      + boolean registerUser(User user)
      + User getCurrentUser()
      + void changeUserPassword(User user, String newPassword)
    }  

````

===================================================================================================

## Ergebnisse aus dem Internet

## Allgemeine Anforderungen

* Der Password Manager muss sicher und verschlüsselt sein, um die gespeicherten Passwörter zu schützen.

* Die Möglichkeit, Passwörter manuell einzugeben oder automatisch zu generieren.
* Möglichkeit zur Sicherung und Wiederherstellung der Passwortdaten.

## Passwortverwaltung

* Fähigkeit, Passwörter in Kategorien organisieren.

* Passwortgenerator zur Erstellung starker Passwörter.
* Möglichkeit, Notizen und zusätzliche Informationen zu Passwörtern hinzuzufügen.
* Überprüfung der Passwortstärke und Empfehlungen zur Verbesserung.

## Benutzerfreundlichkeit

* Schnelle Suche und Filtermöglichkeiten, um Passwörter leicht zu finden.

* Möglichkeit, Passwörter in die Zwischenablage zu kopieren, ohne sie anzuzeigen.
* Automatische Ausfüllung von Anmeldeformularen auf Websites und in Apps.
* Einfache Möglichkeit zum Ändern von Passwörtern.

## Zusätzliche Funktionen

* Sicheres Teilen von Passwörtern mit anderen Benutzern.

* Verlauf der Passwortänderungen und Benachrichtigungen über Sicherheitsverletzungen.
* Integration mit gängigen Webbrowsern und Betriebssystemen.
* Export- und Importfunktionen für Passwortdaten.

## Datenschutz und Compliance

* Einhaltung der Datenschutzbestimmungen und Sicherstellung der Vertraulichkeit der Benutzerdaten.

* Regelmäßige Aktualisierungen und Sicherheitspatches.
* Option zur Selbsthosting für Benutzer, die volle Kontrolle über ihre Daten wünschen.

## Unterstützung und Dokumentation

* Zuverlässiger Kundensupport und ausführliche Dokumentation.

* Schulungsmaterialien für Benutzer, um die sichere Verwendung des Password Managers zu gewährleisten.

## Lizenzierung

* Klare und faire Lizenzierungsbedingungen, sowohl für kostenlose als auch für kostenpflichtige Versionen.

## Barrierefreiheit

* Zugänglichkeit für Menschen mit Behinderungen, einschließlich Screenreader-Unterstützung.

## Sicherheit

* Starke Verschlüsselungsalgorithmen für die Speicherung der Passwörter.

* Zwei-Faktor-Authentifizierung (2FA) zur zusätzlichen Sicherheit.
* Automatische Abmeldung nach Inaktivität.
* Schutz vor Brute-Force-Angriffen und Wiederherstellung des Masterpassworts.

Diese Anforderungen bilden eine solide Grundlage für die Entwicklung eines Password Managers, der Benutzern eine sichere und benutzerfreundliche Möglichkeit zur Verwaltung ihrer Passwörter bietet.

