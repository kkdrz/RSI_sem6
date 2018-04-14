copy .\srv.policy .\target
cd target
start cmd /k java -jar -Djava.security.policy=srv.policy FW.jar worker 1099 worker1
start cmd /k java -jar -Djava.security.policy=srv.policy FW.jar worker 1099 worker2
start cmd /k java -jar -Djava.security.policy=srv.policy FW.jar worker 1099 worker3
start cmd /k java -jar -Djava.security.policy=srv.policy FW.jar worker 1099 worker4
start cmd /k java -jar -Djava.security.policy=srv.policy FW.jar worker 1099 worker5

