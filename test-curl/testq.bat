@echo off

rem returns zero rows
rem D:\installs\curl\curl -i -X GET -H "Content-Type: application/json" -d ".*SomeName*." http://localhost:10080/smap-web-app/webapi/RawFacebookQueue

rem returns one rows
rem curl -i -X GET -H "Content-Type: application/json" -d "{ 'name': { '$regex': '.*Ganesh Ghag*.'} }" http://localhost:8080/smap-web-app/webapi/RawFacebookQueue


rem curl -i -X GET -H "Content-Type: application/json" -d "{ 'name': 'Ganesh Ghag New1' }" http://localhost:8080/smap-web-app/webapi/RawFacebookQueue

rem query using dot notation on fields, arrays indexes appended
rem curl -i -X GET -H "Content-Type: application/json" -d "{ 'activities.data.0.name': { '$regex': '.*Nefarious.*'} }" http://localhost:8080/smap-web-app/webapi/RawFacebookQueue

rem query using run command eg. db.quotes.runCommand( "text", { search: "TOMORROW" } )
curl -i -X GET -H "Content-Type: application/json" -d "{search:'Whistiling'}" http://localhost:8080/smap-web-app/webapi/RawFacebookQueue/textSearch


