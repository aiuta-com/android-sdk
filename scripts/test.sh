#!/bin/bash

set -e

# Check binary capability & lint
./gradlew apiCheck spotlessCheck
