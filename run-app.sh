#!/usr/bin/env bash

log_file="app.log"

./gradlew bootRun --args='--spring-boot.run.jvmArguments="-Xms256m -Xmx256m"' | tee "$log_file"
