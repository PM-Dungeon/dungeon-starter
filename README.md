# pmdungeon



## Framework Version anpassen

Um die Version des Frameworks festzulegen muss die `build.gradle` angepasst werden.  Ändern Sie die Zeile `implementation "io.github.pm-dungeon:core:1.0.+"`. `core:1.0.+` gibt an, welche Version verwendet werden soll. Ändern Sie es entsprechend Ihrer wünsche.

Falls Sie lieber eine lokale Lösung verwenden möchten, laden Sie sich die `jar` aus dem `core` und legen Sie sie in den `lib` Ordner Ihres Projektes. Tauschen Sie die Zeile `implementation "io.github.pm-dungeon:core:1.0.+"` durch `  implementation files("lib/code-1.0.8.jar")` in der `build.gradle` aus. 

