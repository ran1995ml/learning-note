#!/bin/bash
my_array=(
rccp207-9c.iad4.prod.conviva.com
rccp207-9d.iad4.prod.conviva.com
rccp208-9c.iad4.prod.conviva.com
rccp208-9d.iad4.prod.conviva.com
rccp209-9c.iad4.prod.conviva.com
rccp209-9d.iad4.prod.conviva.com
rccp210-9c.iad4.prod.conviva.com
rccp210-9d.iad4.prod.conviva.com
rccp211-9c.iad4.prod.conviva.com
rccp213-9c.iad4.prod.conviva.com
rccp213-9d.iad4.prod.conviva.com
rccp214-9c.iad4.prod.conviva.com
rccp214-9d.iad4.prod.conviva.com
)
command="echo '############## df ########################';"
command+='df -h;'
command+="echo '############## memory #################################';"
command+='free -g;'
command+="echo '############## cpu ####################################';"
command+='nproc'
#command+="echo '############## version ################################';"
#command+='cat /etc/os-release|grep VERSION;'
#command="mkdir -p /conviva/data/druid/ssd1/0 /conviva/data/druid/ssd2/1;"
#command+="chown -R 1006:1006 /conviva/data/druid/ssd1/0 /conviva/data/druid/ssd2/1;"
#command+="ls -l /conviva/data/druid/ssd1/ /conviva/data/druid/ssd2/"

for i in "${my_array[@]}"
do
    echo -e "\nstart check server:$i"
    ssh -i /Users/rwei/Documents/conviva_cm_private_key.pem root@$i $command
    echo -e "end check server:$i \n"
done
