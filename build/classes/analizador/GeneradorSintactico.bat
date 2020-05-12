SET JAVA_HOME="C:\Program Files\Java\jdk1.8.0_201\bin"
SET PATH=%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
cd E:\LENOVO-PC\Documents\NetBeansProjects\Estructura de Datos\FinalEstructuraDatos\src\analizador
java -jar C:\Users\LENOVO-PC\Downloads\java-cup-11a.jar -parser AnalizadorSintactico -symbols Simbolos parser.cup
pause
