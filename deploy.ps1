echo "start deploy"
$version = $args[0]
echo $version
echo on
mvn package
docker build -t itziksela/orders:$version .
docker push itziksela/orders:$version
kubectl delete deployment orders
kubectl create deployment orders --image=itziksela/orders:$version
kubectl apply -f .\configuration\orders.yml

echo "end deploy"
echo "now run the serivce with admin rights: minikube service orders"