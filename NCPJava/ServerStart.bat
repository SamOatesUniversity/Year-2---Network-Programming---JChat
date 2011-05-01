@echo off
echo ##########################################
echo ##               JServer                ##
echo ##########################################

set INPUT=
set /P INPUT=Enter a port to start the server on : %=%
echo Starting a server on %INPUT%
echo ------------------------------------------
echo ------------------------------------------
java -jar JServer.jar %INPUT%

pause