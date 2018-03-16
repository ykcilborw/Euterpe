# Euterpe
A way to track changes in song play history.

How to Run:

1) Go to pom.xml directory

2) Build fat jar

```
mvn clean compile assembly:single
```

3) Execute
```
java -jar target/Euterpe-1.0-SNAPSHOT-jar-with-dependencies.jar $VISUAL_MODE $EXECUTION_MODE
```

$VISUAL_MODE can be 'text' or 'swing'
$EXECUTION_MODE can be 'test'