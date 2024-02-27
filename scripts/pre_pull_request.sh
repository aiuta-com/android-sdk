#!/bin/bash
set -e

# Firstly, check all changes
./test.sh

# Secondly, update docs
./make_docs.sh
