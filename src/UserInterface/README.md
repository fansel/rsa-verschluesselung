# UserInterface

Methodensammlung f&uuml;r die Nutzung von Konsoleneingaben im Praktikum Objektorientierte Programmierung 2023.

## Verwendung

Nach Integration des enthaltenen packages `de` in die source files des Projektes kann die Klasse &uuml;ber
```java
import de.oop2023.util.UserInterface;
```
importiert werden.

Danach k&ouml;nnen in einfacher Form Aufforderungen f&uuml;r Nutzereingaben gestellt werden. Beispielsweise:
```java
double value = UserInterface.in.requestDouble("Bitte Flie&szlig;kommazahl &gt;= 5 und &lt;= 10 eingeben:", 5, 10);
```

F&uuml;r weitere Informationen, siehe die generierbare
[Javadoc](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html):
```bash
javadoc de.oop2023.util
```

## Lizenz

Die oben beschriebene Software wird unter der open source
[MIT License](LICENSE)
bereitgestellt.
