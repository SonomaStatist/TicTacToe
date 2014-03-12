all : TicTacToeFrame.jar TicTacToeApplet.jar

TicTacToeFrame.jar : mainClassF TicTacToeFrame.class X.jpg O.jpg
	jar -J-version:"1.6.0_10+" cvmf mainClassF TicTacToeFrame.jar TicTacToe.class TicTacToePanel*.class TicTacToeFrame.class X.jpg O.jpg

TicTacToeApplet.jar : mainClassA TicTacToeApplet.class X.jpg O.jpg
	jar -J-version:"1.6.0_10+" cvmf mainClassA TicTacToeApplet.jar TicTacToe.class TicTacToePanel*.class TicTacToeApplet.class X.jpg O.jpg

TicTacToeFrame.class : TicTacToeFrame.java TicTacToePanel.java TicTacToe.java
	javac -source 1.6 -target 1.6 TicTacToeFrame.java

TicTacToeApplet.class : TicTacToeApplet.java TicTacToePanel.java TicTacToe.java
	javac -source 1.6 -target 1.6 TicTacToeApplet.java

clean :
	rm -f *.class *.jar
