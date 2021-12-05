#!/bin/bash --euo pipefail

cd "`dirname $0`/.."

clj -M:nREPL -m nrepl.cmdline