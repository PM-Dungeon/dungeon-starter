# Quickstart

Dieses Dokument liefert einen Einstieg in das PM-Dungeon. Es erläutert die Installation des Frameworks und die ersten Schritte, um eigene Inhalte zum Dungeon hinzuzufügen. Es dient als Grundlage für alle weiteren Praktika. Lesen Sie das Dokument daher aufmerksam durch und versuchen Sie sich zusätzlich selbst mit dem Aufbau vertraut zu machen.
Das Framework ist in `core` und `desktop` aufgeteilt, wobei `core` das Framework und `desktop` ein Basis-Starter ist.

*Hinweis: Achten Sie darauf, Daten nur dann in öffentliche Git-Repos zu laden, wenn Sie die nötigen Rechte an diesen Daten haben. Dies gilt insbesondere auch für Artefakte wie Bilder, Bitmaps, Musik oder Soundeffekte.*

## Installation

Sie werden das Java SE Development Kit 17.0.x oder höher benötigen.

Um das PM-Dungeon-Framework zu nutzen erstellen Sie sich einen Fork des [`desktop`-Repository](https://github.com/PM-Dungeon/desktop) und ziehen Sie sich einen lokalen Klon auf Ihr Gerät.

## Arbeiten mit dem Framework

Zu Beginn einige grundlegende Prinzipien, die Sie verstanden haben sollten, bevor Sie mit dem Dungeon arbeiten.

Das PM-Dungeon benutzt aktuell das Cross-Plattform Java-Framework [`libGDX`](https://libgdx.com) als Backend. Dieses ist im `core`- und `desktop`-Projekt bereits als Abhängigkeit in die Gradle-Konfiguration integriert, Sie müssen dieses nicht extra installieren. Die Ihnen zur Verfügung gestellten Vorgaben sind so umgesetzt, dass Sie kein tieferes Verständnis für das Framework oder `libGDX` benötigen, um die Aufgaben zu lösen. Sollten Sie allerdings einmal auf Probleme stoßen, kann es unter Umständen helfen, einen Blick in die Dokumentation von `libGDX` zu werfen.

Das Framework ist in ein Frontend ([`desktop`]((https://github.com/PM-Dungeon/desktop))) und ein Backend ([`core`]((https://github.com/PM-Dungeon/core))) aufgeteilt.
Das Frontend setzt die Parameter, erzeugt ein Fenster und startet die Anwendung.
Das Backend liefert die Schnittstellen, mit denen Sie arbeiten, und integriert die `libGDX`.

Sie selbst schreiben die Logik des Spiels und implementieren die Helden/Monster/Gegenstände.

Bis auf seltene (dokumentierte) Ausnahmen werden Sie nicht gezwungen sein, an den Vorgaben Änderungen durchzuführen.

Sie werden im Laufe der Praktika verschiedene Assets benötigen. Diese liegen per Default im `asset`-Verzeichnis. Sie können das Standardverzeichnis in der `build.gradle` anpassen.
  - Standardpfad für Texturen: `assets/`
  - Standardpfad für Level: `assets/level`
  - Standardpfad für Level-Texturen: `assets/textures/level`

## Strukturen

Bevor wir mit der eigentlichen Implementierung des Spiels anfangen, eine kurze Erklärung über den Aufbau des Frameworks.

- Das Framework verwendet sogenannte `Controller` um die einzelnen Aspekte des Spiels zu managen und Ihnen das Leben einfacher zu machen.
    - `EntityController`: Dieser verwaltet alle "aktiven" Elemente wie Helden, Monster, Items etc.
    - `LevelAPI`: Kümmert sich darum, dass neue Level erzeugt und geladen werden.
    - `HUDController`: Verwaltet alle Bildschirmanzeigen die Sie implementieren.
    - `MainController` Verwaltet die anderen `Controller` und beinhaltet die Game-Loop. Ihre Implementierung wird Teil des `MainController`
- Game-Loop: Die Game-Loop ist die wichtigste Komponente des Spieles. Sie ist eine Endlosschleife, welche einmal pro [Frame](https://de.wikipedia.org/wiki/Bildfrequenz) aufgerufen wird. Das Spiel läuft in 30-FPS (also 30 *frames per seconds*, zu Deutsch 30 Bildern pro Sekunde), die Game-Loop wird also 30mal in der Sekunde aufgerufen. Alle Aktionen, die wiederholt ausgeführt werden müssen, wie zum Beispiel das Bewegen und Zeichnen von Figuren, müssen innerhalb der Game-Loop stattfinden. Das Framework ermöglicht es Ihnen, eigene Aktionen in die Game-Loop zu integrieren. Wie genau das geht, erfahren Sie im Laufe dieser Anleitung. *Hinweis: Die Game-Loop wird automatisch ausgeführt, Sie müssen sie nicht aktiv aufrufen.*
- Zusätzlich existieren noch eine Vielzahl an weiteren Helferklassen mit dem Sie mal mehr oder mal weniger Kontakt haben werden.
- `Painter`: Kümmert sich darum, dass die Inhalte grafisch dargestellt werden.
- `DungeonCamera`: Ihr Auge in das Dungeon.
- Unterschiedliche Interfaces, welche Sie im Verlauf dieses Dokumentes kennen lernen werden.

**TODO UML**

## Erster Start

Die Vorgaben sind bereits lauffähig und können direkt ausgeführt werden.
Dafür können Sie die Vorgaben entweder als Projekt in Ihrer IDE laden und die Anwendung über die Run-Funktion starten oder Sie starten die Anwendung über die Kommandozeile.
Gehen Sie dafür in das `desktop/code` Verzeichnis und öffnen Sie die Kommandozeile und geben Sie folgenden Befehl ein:
- Unter Windows: `bash gradlew run`
- Unter Linux: `./gradlew run`

Das Spiel sollte nun starten und Sie sollten einen Ausschnitt des Levels sehen können.

## Blick in den Code

Bevor wir nun unseren Helden implementieren sollten wir verstehen, wie genau die Vorgaben aufgebaut sind.
Öffnene Sie dafür das `desktop/code` Verzeichnis als Gradle-Projekt in Ihrer bevorzugten IDE.

Betrachten wir nun die `desktop.MyGame.class`. Diese Klasse ist Ihr einstiegspunkt in das Dungeon. Hier werden Sie später Ihre Inhalte erzeugen und in das Dungeon hinzufügen.

`MyGame` erbt von den `MainController`. Wie der Name schon vermuten lässt, ist der MainController die Haupt-Steuerung des Spiels. Er bereitet alles für den Start des Spieles vor, verwaltet die anderen Controller und enthält die Game-Loop. Wir nutzen `MyGame` um selbst in die Game-Loop einzugreifen und unsere eigenen Objekte wie Helden und Monster zu verwalten. Der `MainController` ist der Punkt, an dem alle Fäden des Dungeons zusammenlaufen.

`MyGame` implementiert bereits einige Methoden:
- `setup` wird zu Beginn der Anwendung aufgerufen. In dieser Methode werden später die Objekte initialisiert und konfiguriert, welche bereits vor dem Spielstart existieren müssen. In der Vorgabe wird hier bereits das erste Level geladen.  
- `beginFrame` wird am Anfang jedes Frames aufgerufen. Hier werden später Abfragen und Berechnungen implementiert.  
- `endFrame` wird am Ende jedes Frames aufgerufen. Hier werden später Abfragen und Berechnungen implementiert.  
- `onLevelLoad` wird immer dann aufgerufen, wenn ein Level geladen wird. Hier werden später Monster und Items im Level verteilt. 
- `main` startet das Spiel.

## Eigener Held

Jetzt, wo Sie sichergestellt haben, dass das Dungeon ausgeführt werden kann, geht es darum, das Spiel mit Ihren Inhalten zu erweitern. Im Folgenden wird ein rudimentärer Held implementiert, um Ihnen die verschiedenen Aspekte des Dungeon zu erläutern.

Fangen wir damit an eine neue Klasse für den Helden anzulegen. Unser Held soll grafisch dargestellt werden und vom `EntityController` verwaltet werden können. Daher implementiert er das Interface `IEntity`.

Das Interface `IEntity` liefert einige Methoden, welche wir implementieren müssen.

- `update` diese Methode wird später vom `EntityController` in jedem Frame einmal aufgerufen. Änderungen am Status des Helden, wie zum Beispiel die Position, werden hier berechnet. 
- `removeable` wenn diese Methode `true` zurückgibt, wird das Objekt aus dem `EntityController` entfernt und nicht mehr verwaltet. Besiegt unser Held später ein Monster, sollte dieses nach dem Ableben `true` zurückgeben. 
- `getBatch` die SpriteBatch ist die Zeichenfläche auf dem unser Objekt gezeichnet werden soll. Jede Entität muss wissen, worauf Sie gezeichnet werden soll. 
- `getPainter` ist der Zeichner, der unser Objekt zeichnet. Jede Entität muss wissen, von wem es gezeichnet wird.
- `getPosition` gibt an, wo unser Held im Dungeon steht. 
- `getTexture` gibt an, welche Textur verwendet werden soll, wenn unser Held gezeichnet wird. 

Wir sollten einige dieser Methoden mit Code füllen.
Die `SpriteBatch` und den `Painter` bekommt unser Held bei der Erstellung übergeben. Daher legen wir Variablen an und erstellen einen Konstruktor für unseren Helden. Die Textur für unseren Helden können wir auch schon implementieren. Dafür geben wir den Pfad zu unserer Textur als String an.

```java
    private Painter painter;
    private SpriteBatch batch;
    private String texture;

    public MyHero(SpriteBatch batch, Painter painter){
        this.batch=batch;
        this.painter=painter;
        texture=("assets_path_to_texture/held.png");
    }
```

In den jeweiligen `get` Methoden, geben wir nun die jeweiligen Variablen zurück.

Unser Held benötigt aber noch eine Position im Level. Dafür muss unser Held auch das Level kenne. Wir implementieren daher eine `setLevel` Methode in unseren Helden, speichern das Level ab (das werden wir später noch brauchen) und suchen uns die Startposition im Level und platzieren unseren Helden darauf.

```java
   public void setLevel(Level level){
        this.level=level;
        position=level.getStartTile().getCoordinate().toPoint();
    }
```

Bevor wir weiter machen, sollten wir uns einmal den aufbau des Level anschauen. Level werden als 2D-Tile-Array gespeichert. Ein `Tile` ist dabei ein Feld im Level, also eine Wand oder ein Bodenfeld. Jedes `Tile` hat eine feste `Coordinate` im Array (also wo im Array das `Tile` abgespeichert ist). Diese `Coordinate` gibt auch an, wo das `Tile` im Level liegt. `Coordinate` sind zwei Integerwerte (`x` und `y`). Die Position von Entitäten geben wir als `Point` an. Ein `Point` sind zwei Floatwerte (`x` und `y`). Das machen wir darum, weil unsere Entitäten auch zwischen zwei `Tiles`stehen können. Wenn wir später die Steuerrung für unseren Helden implementieren, wird dieses noch deutlicher. Jetzt ist wichtig, dass wir mit `Coordinate.toPoint()` unseren Helden auf die Position des Starttiles setzen können.

Wir müssen noch dafür sorgen, dass unser Held auch gezeichnet wird. Da unser Held später vom `EntityController`verwaltet wird, nutzen wir dafür die `update` Methode.

```java
    @Override
    public void update() {
        draw();
    }
```
Die Methode `draw()` ist eine im Interface `IEntity` vorimplementierte Methode (was das bedeutet lernen Sie im laufe des Semesters) und sorgt dafür, dass unser Held an der entsprechenden Position im Dungeon gezeichnet wird.

Wir haben die erste Version unseres Helden implementiert. Jetzt müssen wir ihn noch Erstellen.
Dafür gehen wir wieder in `MyGame` und legen eine Variable `MyHero hero` an.
In `setup` erstellen wir nun unseren Helden und registrieren ihn im `EntityController`. Außerdem wollen wir, dass die Kamera auf unseren Helden zentriert wird.

```java
    @Override
    protected void setup() {
        hero = new MyHero(batch, painter);
        entityController.add(hero);
        camera.follow(hero);
        // load the first level
        ...
    }
```

Jetzt müssen wir den Helden nurnoch im Dungeon platzieren. Dafür rufen wir die `setLevel` Methode auf, nachdem ein Level geladen wurde.

```java
    @Override
    public void onLevelLoad() {
        hero.setLevel(levelAPI.getCurrentLevel());
    }
```

Wenn Sie das Spiel nun starten, sollten Sie ihren unbeweglichen Helden im Dungeon sehen können.

### Intermezzo: Der Assets-Ordner

Im Ordner [`code/assets/`](https://github.com/PM-Dungeon/desktop/tree/master/code/assets) werden alle Assets gespeichert, die im Dungeon verwendet werden. Assets sind dabei im Prinzip die Texturen, die später gezeichnet werden sollen.
Der `assets`-Ordner hat aber eine spezielle Adressierung.
Wenn der absolute Pfad zu einer Textur zum Beispiel `code/assets/character/knight/knight_m_idle_anim_f0.png` ist, dann geben wir den relativen Pfad zur Textur mit `character/knight/knight_m_idle_anim_f0.png` an.
Das Präfix `code/assets/` wird dabei also einfach weggelassen.

Bitte finden Sie selbst heraus, welche Texturen es gibt und verwendet werden können.

Der Assets-Ordner kann übrigens auch **umbenannt** oder an eine andere Stelle **verschoben** werden: Passen Sie dafür die Pfadangabe `sourceSets.main.resources.srcDirs = ["assets/"]` in der [`build.gradle`](https://github.com/PM-Dungeon/desktop/blob/master/code/build.gradle)-Datei an.

**Beispiel:** Sie möchten den Ordner `desktop/code/assets/` nach `desktop/code/bar/wuppie/` verschieben, dann ändern Sie `sourceSets.main.resources.srcDirs = ["assets/"]` in `sourceSets.main.resources.srcDirs = ["bar/wuppie/"]`.

Beachten Sie, dass der Ordner nur innerhalb von `desktop/code/` umbenannt bzw. verschoben werden kann.

Später werden Sie es wahrscheinlich praktischer finden, anstelle von relativen Pfaden den [`textures/TextureHandler.java`](https://github.com/PM-Dungeon/core/blob/master/code/core/src/textures/TextureHandler.java) zu verwenden, der reguläre Ausdrücke entgegennehmen und entsprechende Textur-Pfade zurückgeben kann.

***

### Der bewegte (animierte) Held

Aktuell besitzt unser Held nur eine feste Textur, in diesen Abschnitt animieren wir unseren Helden.
Im PM-Dungeon ist eine Animation ein Loop verschiedener Texturen die im wechsel gezeichnet werden.
Um unseren Helden zu animieren, nutzen wir eine erweiterte Version des `IEntity` interfaces `IAnimatable`.

`public class MyHero implements IAnimatable`

Die Methode `getTexture` müssen wir nun mit der Methode `getgetActiveAnimation` ersetzten. Ebenso ersetzten wir unsere `texture` Varibale durch eine `idle` Variable (`private Animation idle`).


```java
    @Override
    public Animation getActiveAnimation() {
        return idle;
    }
```
Jetzt müssen wir die Animation noch erstellen. Dafür gehen wir wieder in den Konstruktor unseres Helden.

```java
public Hero(SpriteBatch batch, Painter painter) {
    this.batch=batch;
    this.painter=painter;

    // Erstellen einer ArrayList
    List<String> animation = new ArrayList<>();
    // Laden der Texturen für die Animation (relativen Pfad angeben)
    animation.add("assets_path_to_texture/texture_1.png");
    animation.add("assets_path_to_texture/texture_2.png");
    // Erstellen einer Animation, als Parameter wird die Liste mit den Texturen
    // und die Wartezeit (in Frames) zwischen den Wechsel der Texturen angegeben
    idle = new Animation(idle, 8);
}
```

Um eine Animation zu erstellen benötigen Sie eine Liste mit verschiedenen Texturen. Dann können Sie mit `new Animation()` eine Animation erstellen. Dabei übergeben Sie zu einem die Liste mit den Texturen und zu anderen ein `int` (hier 8). Dieser Wert gibt an, nach wie vielen Frames die nächste Textur geladen werden soll. In unserem Beispiel wird also 8 Frames lang die Textur `texture_1` angezeigt, dann 8 Frames die Textur `texture_2` und dann wieder 8 Frames die Textur `texture_1` usw.

Sie können (und sollten) auch verschiedene Animationen für verschiedene situationen ertellen (stehen, laufen etc.). Geben Sie einfach in `getActiveAnimation` immer die Animation zurück, die grade verwendet werden soll.

Wenn Sie das Spiel nun starten, sollten Sie Ihren animierten unbeweglichen Helden sehen.


### WASD oder die Steuerung des Helden über die Tastatur

Es wird Zeit, dass unser Held sich bewegen kann. Dafür fügen wir Steuerungsoptionen in der `MyHero#update`-Methode hinzu:

```java
    // Temporären Point um den Held nur zu bewegen, wenn es keine Kollision gab
    Point newPosition = new Point(this.position);
    // Unser Held soll sich pro Schritt um 0.1 Felder bewegen.
    float movementSpeed = 0.1f;
    // Wenn die Taste W gedrückt ist, bewege dich nach oben
    if (Gdx.input.isKeyPressed(Input.Keys.W)) newPosition.y += movementSpeed;
    // Wenn die Taste S gedrückt ist, bewege dich nach unten
    if (Gdx.input.isKeyPressed(Input.Keys.S)) newPosition.y -= movementSpeed;
    // Wenn die Taste D gedrückt ist, bewege dich nach rechts
    if (Gdx.input.isKeyPressed(Input.Keys.D)) newPosition.x += movementSpeed;
    // Wenn die Taste A gedrückt ist, bewege dich nach links
    if (Gdx.input.isKeyPressed(Input.Keys.A)) newPosition.x -= movementSpeed;
    // Wenn der übergebene Punkt betretbar ist, ist das nun die aktuelle Position
    if(level.getTileAt(newPosition.toCoordinate()).isAccessible())
        this.position = newPosition;
```
Damit unser Held sich nicht durch Wände bewegt, berechnen wir zuerst die neue Position, kontrollieren dann ob diese gültig ist und platzieren dann unseren Helden (oder auch nicht).
Mit `Gdx.input.isKeyPressed` können wir überprüfen, ob eine Taste gedrückt ist.
Jenachdem welche Taste gedrückt wurde, ändern wie die (nächste) Position des Helden.
mit `level.getTileAt(newPosition.toCoordinate()).isAccessible()` können wir überprüfen, ob es sich bei der neuen Position um ein betretbares `Tile` handelt oder nicht.

Wenn Sie nun das Spiel starten, sollten Sie sich mit Ihren Helden bewegen können.
Fügen Sie eine Animation für jede Laufrichtung hinzu.

### Nächstes Level laden

Da unser Held immer tiefer in das Dungeon gelangen soll, lassen wir jetzt ein neues Level laden, wenn der Held auf die Leiter tritt.

Dafür nutzen wir die `endFrame` Methode in `MyGame`. Mit `levelAPI.getCurrentLevel().isOnTile()` können wir überprüfen, ob unser Held auf dem EndTile steht. Ist dies der Fall, lassen wir ein neues Level laden.

Da wir unseren Helden in `onLevelLoad` beim laden eines neuen Level automatisch neu platzieren, müssen wir uns darum nicht mehr kümmern.

```java
    @Override
    protected void endFrame() {
        if(levelAPI.getCurrentLevel().isOnEndTile(hero))
            levelAPI.loadLevel();
    }
```

*Anmerkung: Später werden Sie viele weitere Entitäten im Level platziert haben (Monster, Schatztruhen, Falle etc.). Diese sollten Sie beim laden eines neuen Level löschen oder in das nächste Level "mitnehmen".*

Starten Sie nun das Spiel sollten Sie sich mit Ihrem Helden durch das Level bewegen können und auch in das nächste Level gelangen können.

## Levelgenerator

Das PM-Dungeon nutzt einen eigenen prozeduralen Levelgenerator. Eigentlich müssen Sie sich nicht damit beschäftigen, unter umständen kann die Brechnung von Level aber viel Zeit auf Ihrer Maschine benötigen, sie können daher auch abgespeicherte Level anstelle von "frisch generierten" Level verweden. Fügen Sie dafür einfach `levelAPI.setGenerator(new LevelLoader());` zu Beginn der `MyGame#setup` Methode hinzu. Die Level werden aus `.json` im `assets/level/files/` Verzeichnis geladen.

## Abschlussworte

Sie haben nun die ersten Schritte im Dungeon gemacht. Von nun an müssen Sie selbst entscheiden, wie Sie die Aufgaben im Praktikum umsetzten möchten. Ihnen ist mit Sicherheit aufgefallen, dass einige Interface-Methoden in diesem Dokument noch nicht erläutert wurden. Machen Sie sich daher mit der Javadoc des Frameworks vertraut.

## Zusätzliche Funktionen

Hier finden Sie weitere Funktionen, welche Sie im Verlauf des Praktikums gebrauchen können.

### Head-up-Display (HUD)

Dieser Abschnitt soll Ihnen die Werkzeuge nahebringen, welche Sie für die Darstellung eines HUD benötigen.

Um eine Grafik auf dem HUD anzeigen zu können, erstellen wir zuerst eine neue Klasse, welche das Interface `IHUDElement` implementiert.

```java
public class MyIcon implements IHUDElement {
    private SpriteBatch batch;
    private HUDPainter painter;
    private Point position;
    private String texture;

    public MyIcon(SpriteBatch batch, HUDPainter painter, Point position, String texture) {
        this.batch = batch;
        this.painter = painter;
        this.position = position;
        this.texture = texture;
    }

    @Override
    public void update() {
        this.draw();
        // this.drawWithScaling(8, 4);
    }

    @Override
    public boolean removable() {
        return false;
    }

    @Override
    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public String getTexture() {
        return texture;
    }

    @Override
    public HUDPainter getPainter() {
        return painter;
    }
}
```

Die Methode `getTexture` gibt den Pfad zu der gewünschten Textur zurück, dies funktioniert identisch zur bereits bekannten Helden-Implementierung. Die Methode `getPosition` gibt die Position der Grafik auf dem HUD zurück. Es wird vorkommen, dass Sie Grafiken in Abhängigkeit zu anderen Grafiken positionieren möchten, überlegen Sie sich daher bereits jetzt eine gute Struktur, um Ihre HUD-Elemente abzuspeichern.

Jetzt müssen wir unsere Grafik nur noch anzeigen lassen. Ähnlich zu den bereits bekannten Controllern gibt es auch für das HUD eine Steuerungsklasse, welche im `MainController` mit `hud` angesprochen werden kann.

```
public class YourClass extends MainController {
     @Override
    protected void setup() {
        ...
        // hinzufügen eines Elementes zum HUD
        hudController.add(
                new MyIcon(
                        hudBatch,
                        hudPainter,
                        new Point(0f, 0f),
                        TextureHandler.getInstance().getTextures("ui_heart_full.png").get(0)));
        //so entfernt man ein Element
        //hud.remove(OBJECT);
    }
}
```

#### Text im HUD

Verwenden Sie die Methode `HUDController#drawText`, um einen String auf Ihren Bildschirm zu zeichnen. Sie haben dabei eine umfangreiche Auswahl an Parametern, um Ihre Einstellungen anzupassen.`HUDController#drawText` gibt Ihnen ein `Label`-Objekt zurück, dieses können Sie verwenden, um den Text später anzupassen oder ihn vom Bildschirm zu entfernen. Um den Text anzupassen, können Sie `label.setText("new String")` verwenden und um das Label zu löschen, können Sie `label.remove()` verwenden.

Im unteren Beispiel wird ein Text implementiert, welcher das aktuelle Level ausgibt.

```
public class MyGame extends MainController {
    .....
    Label levelLabel;
    int levelCounter=0;

    public void onLevelLoad() {
        levelCounter++;
        if (levelCounter==1){
            levelLabel=hudController.drawText("Level"+x,"PATH/TO/FONT.ttf",Color.RED,30,50,50,30,30);
        }
        else{
            levelLabel.setText("Level"+x);
        }
    }
    //remove label
    //levelLabel.remove();
}
```

Genauere Informationen zu den Parametern entnehmen Sie bitte der JavaDoc.

### Level-API

Sie haben viele Möglichkeiten mit dem Level zu interagieren.
Schauen Sie in die Javadoc um herauszufinden, wie Sie optionale Räume identifizieren oder Wände wegsprengen können.

### Sound

Möchten Sie Soundeffekte oder Hintergrundmusik zu Ihrem Dungeon hinzufügen, bietet `libGDX` eine einfache Möglichkeit dafür.

Es werden die Formate `.mp3`, `.wav` und `.ogg` unterstützt. Das Vorgehen unterscheidet sich zwischen den Formaten nicht.

```Java
//Datei als Sound-Objekt einladen
Sound bumSound = Gdx.audio.newSound(Gdx.files.internal("assets/sound/bum.mp3"));
bumSound.play();
//Sound leise abspielen
bumSound.play(0.1f);
//Sound mit maximal Lautstärke abspielen
bumSound.play(1f);
//Soud endlos abspielen
bumSound.loop();
```

Sie können noch weitere Parameter und Methoden verwenden, um den Sound Ihren Wünschen anzupassen. Schauen Sie dafür in die [`libGDX`-Dokumentation](https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/audio/Sound.html)

