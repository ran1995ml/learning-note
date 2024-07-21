#!/bin/bash
my_array=(
rccp110-8c.iad4.prod.conviva.com
rccp110-8d.iad4.prod.conviva.com
rccp110-9a.iad4.prod.conviva.com
rccp110-9c.iad4.prod.conviva.com
rccp110-9d.iad4.prod.conviva.com
rccp112-8a.iad4.prod.conviva.com
rccp112-8c.iad4.prod.conviva.com
rccp112-9a.iad4.prod.conviva.com
rccp112-9b.iad4.prod.conviva.com
rccp112-9c.iad4.prod.conviva.com
rccp112-9d.iad4.prod.conviva.com
rccp113-8a.iad4.prod.conviva.com
rccp113-8c.iad4.prod.conviva.com
rccp113-8d.iad4.prod.conviva.com
rccp114-9a.iad4.prod.conviva.com
)
command="echo '############## df ########################';"
command+='df -h /conviva;'
command+="echo '############## memory #################################';"
command+='free -g;'
command+="echo '############## cpu ####################################';"
command+='nproc;'
command+="echo '############## version ################################';"
command+='cat /etc/os-release|grep VERSION;'

for i in "${my_array[@]}"
do
        echo -e "\nstart check server:$i"
    ssh -i /Users/rwei/Documents/conviva_cm_private_key.pem root@$i $command
    echo -e "end check server:$i \n"
done
