#!/bin/sh
# vpud daemon
# description: mtk vpud daemon
# processname: vpud

DAEMON_PATH="/usr/bin/"

DAEMON=vpud
DAEMONOPTS="-f"

NAME=vpud
DESC="Mediatek VPUD daemon"
PIDFILE=/var/run/$NAME.pid
SCRIPTNAME=/etc/init.d/$NAME

case "$1" in
start)
	printf "%-50s" "Starting $NAME..."
	cd $DAEMON_PATH
	PID=`$DAEMON $DAEMONOPTS > /dev/null 2>&1 & echo $!`
	if [ -z $PID ]; then
		printf "%s\n" "Fail"
	else
		echo $PID > $PIDFILE
		printf "%s\n" "Ok"
	fi
;;
status)
	printf "%-50s" "Checking $NAME..."
	if [ -f $PIDFILE ]; then
		PID=`cat $PIDFILE`
		if [ -z "`ps axf | grep ${PID} | grep -v grep`" ]; then
			printf "%s\n" "Process dead but pidfile exists"
		else
			echo "Running"
		fi
	else
		printf "%s\n" "Service not running"
	fi
;;
stop)
	printf "%-50s" "Stopping $NAME"
	PID=`cat $PIDFILE`
	cd $DAEMON_PATH
	if [ -f $PIDFILE ]; then
		kill -HUP $PID
		printf "%s\n" "Ok"
		rm -f $PIDFILE
	else
		printf "%s\n" "pidfile not found"
	fi
;;

restart)
	$0 stop
	$0 start
;;

*)
	echo "Usage: $0 {status|start|stop|restart}"
	exit 1
esac

