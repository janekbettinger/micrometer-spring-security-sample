#!/usr/bin/env bash

#
# A simple load test that repeatedly calls /actuator/health with 30 threads in parallel.
#
# The test requires JMeter as I haven't been able to reliably reproduce the issue with other benchmarking tools like
# hey or oha.
#

log_file="app.log"

if ! command -v jmeter > /dev/null; then
  echo "ERROR: Apache JMeter is necessary to run the load test.";
  echo
  echo "  https://jmeter.apache.org/download_jmeter.cgi"
  echo "  https://formulae.brew.sh/formula/jmeter"
  exit 1
fi

if [[ ! -f "$log_file" ]]; then
  echo "ERROR: $log_file does not exist. Please run the application with ./run-app.sh in parallel to this script."
  exit 1
fi

echo "================================================================================================="
echo "Will repeatedly run a simple load test until the expected exception is thrown by the application."
echo "================================================================================================="
echo

export HEAP="-Xms8g -Xmx8g -XX:MaxMetaspaceSize=1g";
while ! grep "Span wasn't started" "$log_file"; do
  jmeter -n -t ./loadtest.jmx
  echo
  echo "-------------------------------------------------------------------------------------------------"
  echo
done

echo
echo "================================================================================================="
echo "See $log_file for the full logs including a stacktrace."
echo "================================================================================================="
