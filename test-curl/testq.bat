@echo off

rem returns zero rows
rem D:\installs\curl\curl -i -X GET -H "Content-Type: application/json" -d ".*SomeName*." http://localhost:10080/smap-web-app/webapi/RawFacebookQueue

rem returns one rows
curl -i -X GET -H "Content-Type: application/json" -d "{ 'name': { '$regex': '.*Ganesh Ghag*.'} }" http://localhost:8080/smap-web-app/webapi/RawFacebookQueue


curl -i -X GET -H "Content-Type: application/json" -d "{ 'name': 'Ganesh Ghag New1' }" http://localhost:8080/smap-web-app/webapi/RawFacebookQueue



