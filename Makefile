all : build doc

build : functions
	javac -d bin -cp "src" src/com/mie/plotter/Main.java

functions :
	javac -d bin -cp "src" src/com/mie/plotter/model/function/*.java
	javac -d bin -cp "src" src/com/mie/plotter/model/nonOrdFunction/*.java

doc :
	javadoc -d doc -sourcepath src -subpackages com.mie.plotter

run : build
	java -cp bin com.mie.plotter.Main

.PHONY : clean doc

clean :
	-rm -r doc/* bin/*
