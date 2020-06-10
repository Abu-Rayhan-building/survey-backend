# !/usr/bin/bash
cmd=$1

if [ "$cmd" == "start" ]
then
    ./create-uber-jar.sh
    cd ../build
    java -jar koft &
    echo $! > /tmp/koftid
elif [ "$cmd" == "stop" ]
then
    kill $(cat /tmp/koftid)
elif [ "$cmd" == "restart" ]
then
    kill $(cat /tmp/koftid)
    ./create-uber-jar.sh
    cd ../build
    java -jar koft &
    echo $! > /tmp/koftid
else
    echo "Command not recognized"
fi
