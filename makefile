CP = .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar

MarkdownParse.class: MarkdownParse.java
	javac -g -cp $(CP) MarkdownParse.java

MarkdownParseTest.class: MarkdownParseTest.java MarkdownParse.class
	javac -g -cp $(CP) org.junit.runner.JUnitCore MarkdownParseTest.java

test: MarkdownParseTest.class
	java -cp $(CP) org.junit.runner.JUnitCore MarkdownParseTest 
