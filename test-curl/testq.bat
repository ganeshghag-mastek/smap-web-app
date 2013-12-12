@echo off

rem returns zero rows
rem D:\installs\curl\curl -i -X GET -H "Content-Type: application/json" -d ".*SomeName*." http://localhost:10080/smap-web-app/webapi/RawFacebookQueue

rem returns one rows
D:\installs\curl\curl -i -X GET -H "Content-Type: application/json" -d "{ 'name': { '$regex': '.*Ganesh Ghag*.'} }" http://localhost:10080/smap-web-app/webapi/RawFacebookQueue


D:\installs\curl\curl -i -X GET -H "Content-Type: application/json" -d "{ 'name': 'Ganesh Ghag New1' }" http://localhost:10080/smap-web-app/webapi/RawFacebookQueue



