#!/bin/sh

# Don't need recurse delete on all Java

find . -name '*.class' -exec rm -Rvf {} \;

