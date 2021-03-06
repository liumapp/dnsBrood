#!/bin/bash
HOME_DIR=/usr/local/DNSBrood
PATH=$PATH:$HOME_DIR
export PATH
JVM_OPTION="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"

function doCache()
{
  case "$1" in
      stat)
        java -jar $HOME_DIR/lib/DNSQueen-1.0.jar -cstat_cache
        ;;
      dump)
        echo "Dump cache to $HOME_DIR/cache.dump"
        java -jar $HOME_DIR/lib/DNSQueen-1.0.jar -cdump_cache > /dev/null
        ;;
      clear)
        echo "Clear cache"
        java -jar $HOME_DIR/lib/DNSQueen-1.0.jar -cclear_cache
        ;;
      *)
    echo "Usage: $0 cache {dump|clear|stat}"
    ;;
  esac
}

case "$1" in
  start)
    echo "Starting DNSBrood..."
    java -jar ${JVM_OPTION} -Djava.io.tmpdir="$HOME_DIR/cache" $HOME_DIR/DNSBrood.jar -d"$HOME_DIR">> $HOME_DIR/log &
    ;;
  stop)
    echo "Stopping DNSBrood"
    java -jar $HOME_DIR/lib/DNSQueen-1.0.jar -cshutdown > /dev/null
    ;;
  cache)
    doCache $2
    ;;
  restart)
    echo "Stopping DNSBrood..."
    java -jar $HOME_DIR/lib/DNSQueen-1.0.jar -cshutdown > /dev/null;
    sleep 2;
    echo "Starting DNSBrood..."
    java -jar ${JVM_OPTION} -Djava.io.tmpdir="$HOME_DIR/cache" $HOME_DIR/DNSBrood.jar -d"$HOME_DIR">> $HOME_DIR/log &
    echo $! > $HOME_DIR/pid
    ;;
  reload)
    echo "Reloading DNSBrood"
    java -jar $HOME_DIR/lib/DNSQueen-1.0.jar -creload > /dev/null
    ;;
  zones)
    vi $HOME_DIR/config/zones
    java -jar $HOME_DIR/lib/DNSQueen-1.0.jar -creload > /dev/null
    ;;
  config)
    vi $HOME_DIR/config/DNSBrood.conf
    java -jar $HOME_DIR/lib/DNSQueen-1.0.jar -creload > /dev/null
    ;;
  *)
    echo "Usage: $0 {start|stop|reload|zones|config|restart|cache}"
    ;;
esac

exit 0
