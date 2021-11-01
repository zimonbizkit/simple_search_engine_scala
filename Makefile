.PHONY: help run tests compile
all: run

help: Makefile
	@sed -n 's/^##//p' $<

##	run		Creates a fat jar compiling the results, runs the engine pointing to /data folder
run:
	sbt assembly
	java -jar target/scala-2.12/SimpleSearchChallenge-assembly-0.1.jar data/

##	tests		Executes the test suite
tests:
	sbt test


##	compile		compiles the program in a separate jar using only sbt
compile:
	sbt compile
