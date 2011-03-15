#!/bin/sh

# Generate ANTLR grammars
OUTDIR=src/org/berlin/pino/dev/unittest/antlr/

java -classpath lib/antlr-2.7.7.jar  antlr.Tool -o $OUTDIR scripts/antlr/java15.g 
java -classpath lib/antlr-2.7.7.jar  antlr.Tool -o $OUTDIR scripts/antlr/java15.tree.g 
