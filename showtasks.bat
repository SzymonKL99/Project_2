call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openchrome
echo.
echo runcrud.bat has errors – breaking work
goto fail

:openchrome
start chrome http://localhost:8080/crud/v1/tasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo starting chrome has errors – breaking work
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.