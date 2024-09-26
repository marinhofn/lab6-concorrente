#!/bin/bash

BASE_DIR=$(dirname -- "$( readlink -f -- "$0"; )")
echo $BASE_DIR
# chama o programa java DistriutedSystem
java -cp $BASE_DIR/bin/ scenario1.ScenarioBase
