#!/bin/bash
my_array=(
rccp106-1a.sjc2.prod.conviva.com
rccp107-1a.sjc2.prod.conviva.com
rccp203-1a.sjc2.prod.conviva.com
rccp203-1b.sjc2.prod.conviva.com
rccp203-2b.sjc2.prod.conviva.com
rccp203-3b.sjc2.prod.conviva.com
rccp203-5b.sjc2.prod.conviva.com
rccp204-1a.sjc2.prod.conviva.com
rccp204-1b.sjc2.prod.conviva.com
rccp204-2a.sjc2.prod.conviva.com
rccp204-2b.sjc2.prod.conviva.com
rccp204-3a.sjc2.prod.conviva.com
rccp204-3b.sjc2.prod.conviva.com
rccp205-1b.sjc2.prod.conviva.com
rccp205-2a.sjc2.prod.conviva.com
rccp205-2b.sjc2.prod.conviva.com
rccp205-3a.sjc2.prod.conviva.com
rccp205-3b.sjc2.prod.conviva.com
rccp205-4b.sjc2.prod.conviva.com
rccp207-1a.sjc2.prod.conviva.com
rccp207-2a.sjc2.prod.conviva.com
rccp207-3a.sjc2.prod.conviva.com
rccp207-3b.sjc2.prod.conviva.com
)
command="echo '############## df ########################';"
command+='df -h|grep hdd;'
#command+="echo '############## memory #################################';"
#command+='free -g;'
#command+="echo '############## cpu ####################################';"
#command+='nproc;'
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
