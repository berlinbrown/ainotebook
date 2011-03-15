#!/bin/sh

cp -vf ../src/*.java ./java/ 
cp -vf ../src/org/berlin/tron/gl/game/*.java ./java/

find . -name '*.class' -exec rm -Rvf {} \;

# Remove unneede java classes
rm -vf ./java/GLRenderBoard.java
rm -vf ./java/BasicGameState.java
rm -vf ./java/GLGameBuilder.java
rm -vf ./java/GLGame.java
rm -vf ./java/UpdateStateTask.java

# Remove invalid packages and imports
sed s/'package org.berlin.tron.gl.game;'//g -i ./java/*.java 
sed s/'import org.berlin'/\\/\\//g -i ./java/*.java 
