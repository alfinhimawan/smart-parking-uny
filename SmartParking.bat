@echo off
cd /d %~dp0

set JAVAFX_LIB=lib\javafx-sdk-24.0.1\lib
set CP=lib\core-3.5.2.jar;lib\javase-3.5.2.jar;lib\opencv-4110.jar;bin
set OPENCV_DLL=bin

start "" javaw ^
  --module-path "%JAVAFX_LIB%" ^
  --add-modules javafx.controls,javafx.fxml,javafx.swing ^
  -cp "%CP%" ^
  -Djava.library.path="%OPENCV_DLL%" ^
  app.MainApp