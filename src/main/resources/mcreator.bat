del .\lib\mcreator.jar
if not exist mcreator.exe (
 echo Important files lost
 pause
 exit
)
copy mcreator.exe .\lib\mcreator.jar
.\jdk\bin\java.exe -javaagent:agent.jar=safeMode --add-opens=java.base/java.lang=ALL-UNNAMED -cp ./lib/* net.mcreator.Launcher