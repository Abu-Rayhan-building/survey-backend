#! /bin/bash
cmd=$1

cd ..
version=$(./gradlew properties -q | grep "version:" | awk '{print $2}' | tr -d '[:space:]')
echo $version
koft="./build/survey-backend-${version}-runner.jar"
echo $koft

runcmd="java -jar $koft >> ./koft.log &"

if [ "$cmd" == "start" ]
then
    eval $runcmd
    echo $! > /tmp/koftid
elif [ "$cmd" == "stop" ]
then
    kill $(cat /tmp/koftid)
elif [ "$cmd" == "update" ]
then
    kill $(cat /tmp/koftid)
    ./update-client.sh
    ./create-uber-jar.sh
    eval $runcmd
    echo $! > /tmp/koftid
elif [ "$cmd" == "restart" ]
then
    kill $(cat /tmp/koftid)
    eval $runcmd
    echo $! > /tmp/koftid
else
    echo "Command not recognized"
fi
