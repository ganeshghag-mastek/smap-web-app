@echo off

rem insert
D:\installs\curl\curl -i -X POST -H "Content-Type: application/json" -d "@fb_insert.txt" http://localhost:10080/smap-web-app/webapi/RawFacebookQueue



