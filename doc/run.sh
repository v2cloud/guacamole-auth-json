# Test connection script
AUTH_KEY=4c0b569e4c96df157eee1b65dd0e4d41
PROXY_URI=https://proxy-bvhjsx0s.devel02.hazeware.com
JSON_FILE=auth.json


# This key matched the one in the guacamole home folder
BASE64=`./encrypt-json.sh $AUTH_KEY $JSON_FILE`

# Note that we use /webconnect/api/tokens instead of /guacamole/api/tokens
curl --insecure --data-urlencode "data=${BASE64}" $PROXY_URI/webconnect/api/tokens

# Curl should log a message with successful connection
