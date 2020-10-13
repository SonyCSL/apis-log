echo "call start.sh"

get_pids() {
 ps -f -U account | grep apis-log | grep java | while read _USER_ _PID_ _OTHERS_; do
  echo $_PID_
 done
}

_PIDS_=`get_pids`
if [ -n "$_PIDS_" ]; then
 echo "... already running"
 exit 1
fi

java -XX:OnOutOfMemoryError="'kill -KILL %p'"  -Duser.timezone=Asia/Tokyo -Djava.util.logging.config.file=./logging.properties -jar ./apis-log-3.0.0-a01-fat.jar -conf ./config.json -cp ./   &

echo "... done"