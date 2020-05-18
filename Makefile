
JFLAGS = -g
JC = javac
JR = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) src/objetos/*.java
	$(JC) $(JFLAGS) src/archivos/*.java
	$(JC) $(JFLAGS) src/analizador/*.java
	$(JC) $(JFLAGS) src/estructuras/arboles/*.java
	$(JC) $(JFLAGS) src/estructuras/grafos/*.java
	$(JC) $(JFLAGS) src/ventanas/*.java
	$(JC) $(JFLAGS) src/finalestructuradatos/*.java

CLASSES = \
	src/objetos/Camino.java \
	src/objetos/Grafica.java \
        src/objetos/Registro.java \
	src/objetos/Ruta.java \
	src/objetos/VerificadorRuta.java \
        src/analizador/AnalizadorLexico.java \
        src/analizador/AnalizadorSintactico.java \
        src/analizador/Simbolos.java \
	src/archivos/Archivo.java \
	src/estructuras/arboles/Nodo.java \
	src/estructuras/arboles/Pagina.java \
	src/estructuras/arboles/Arbol.java \
	src/estructuras/grafos/NodoG.java \
	src/estructuras/grafos/Arista.java \
	src/estructuras/grafos/Grafo.java \
	src/ventanas/Inicio.java \
	src/ventanas/VentanaDatos.java \
	src/ventanas/VentanaGraficasArbol.java \
	src/finalestructuradatos/Main.java 

default: classes

classes: $(CLASSES:.java=.class)

clean: $(RM) *.class

