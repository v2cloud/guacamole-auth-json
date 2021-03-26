TAG=latest # `git describe`

echo "Building v2clouddocker/guacamole-auth-json:$TAG"

docker build . --tag v2clouddocker/guacamole-auth-json:$TAG
docker push v2clouddocker/guacamole-auth-json:$TAG
