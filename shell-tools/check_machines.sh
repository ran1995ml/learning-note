#!/bin/bash
my_array=(
rccp106-10a.sjc2.prod.conviva.com
rccp106-4b.sjc2.prod.conviva.com
rccp106-3b.sjc2.prod.conviva.com
rccp106-6a.sjc2.prod.conviva.com
rccp106-5b.sjc2.prod.conviva.com
rccp106-10c.sjc2.prod.conviva.com
rccp106-5c.sjc2.prod.conviva.com
rccp106-4d.sjc2.prod.conviva.com
rccp106-6b.sjc2.prod.conviva.com
rccp106-10d.sjc2.prod.conviva.com
rccp106-6d.sjc2.prod.conviva.com
rccp106-5d.sjc2.prod.conviva.com
rccp106-3d.sjc2.prod.conviva.com
rccp106-4c.sjc2.prod.conviva.com
rccp106-10b.sjc2.prod.conviva.com
rccp106-3c.sjc2.prod.conviva.com
rccp106-6c.sjc2.prod.conviva.com
)
#command="echo '############## df ########################';"
#command+='df -h /conviva;'
#command+="echo '############## memory #################################';"
#command+='free -g;'
#command+="echo '############## cpu ####################################';"
#command+='nproc;'
command+="echo '############## version ################################';"
command+='lsb_release -a;'

for i in "${my_array[@]}"
do
        echo -e "\nstart check server:$i"
    ssh -i /Users/rwei/Documents/conviva_cm_private_key.pem root@$i $command
    echo -e "end check server:$i \n"
done
