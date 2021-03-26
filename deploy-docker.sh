TAG=devel # `git describe`

echo "Building v2clouddocker/json-auth-extension:$TAG"

docker build . --tag v2clouddocker/json-auth-extension:$TAG
docker push v2clouddocker/json-auth-extension:$TAG
