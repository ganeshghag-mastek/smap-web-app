@echo off

rem insert
curl -i -X POST -H "Content-Type: application/json" -d "@twitter_insert.txt" http://localhost:8080/smap-web-app/webapi/RawTwitterQueue



