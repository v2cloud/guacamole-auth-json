# Test connection script
AUTH_KEY=4c0b569e4c96df157eee1b65dd0e4d41

# Note that we use /webconnect/api/tokens instead of /guacamole/api/tokens
#PROXY_URI=https://proxy-bvhjsx0s.devel02.hazeware.com/webconnect/api/tokens
PROXY_URI=http://localhost:8080/guacamole-1.3.0/guacamole/api/tokens

JSON_FILE=auth.json


# This key matched the one in the guacamole home folder
BASE64=`./encrypt-json.sh $AUTH_KEY $JSON_FILE`


curl --insecure --data-urlencode "data=${BASE64}" $PROXY_URI

# Curl should log a message with successful connection
