@echo off
set "JAVA_HOME=C:\Program Files\Java\jdk-21.0.10"
cd /d "C:\Users\MariaGomez\Documents\Proyecto- Parque de diversiones\salitremagico"
echo JAVA_HOME=%JAVA_HOME%
mvnw.cmd versions:display-dependency-updates
exit /b %ERRORLEVEL%
