# PM-Dungeon-Desktop

Das Desktop-Projekt liefert eine rudimentäre Basis-Implementation des PM-Dungeon und erlaubt einen schnellen Einstieg, um das eigene Dungeon zu entwickeln.

Dieses Projekt wird für den einfacheren Zugriff als Git-Subtree im Unterordner "`desktop/`" im Semester-Repository bereitgestellt.

## Framework Version anpassen

Um die Version des Frameworks festzulegen muss die `build.gradle` angepasst werden.  Ändern Sie die Zeile `implementation "io.github.pm-dungeon:core:1.0.+"`. `1.0.+` gibt dabei an, welche Version verwendet werden soll. Ändern Sie sie entsprechend Ihrer Wünsche.

Falls Sie lieber eine lokale Lösung verwenden möchten, laden Sie sich die `jar` aus dem `core` und legen Sie sie in den `lib` Ordner Ihres Projektes. Tauschen Sie die Zeile `implementation "io.github.pm-dungeon:core:1.0.+"` durch `implementation files("lib/code-1.0.8.jar")` in der `build.gradle` aus. Ersetzen Sie dabei `1.0.8` durch die gewünschte Version.

