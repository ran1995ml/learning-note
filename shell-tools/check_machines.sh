#!/bin/bash
my_array=(
rccp103-1a.sjc2.prod.conviva.com
rccp103-1c.sjc2.prod.conviva.com
rccp104-1c.sjc2.prod.conviva.com
rccp105-1c.sjc2.prod.conviva.com
rccp104-1a.sjc2.prod.conviva.com
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
