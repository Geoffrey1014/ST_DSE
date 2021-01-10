#!/bin/bash

day=1
echo $#
if [[ "$#" -eq "1" ]];
then
{
 day=$1
 echo "day ago :" $day
}
fi
day=$((day-1))
dayT=`date +'%Y-%m-%d' -d "-$day days"`
entime="$dayT 00:00"
day=$((day+1))
dayT=`date +'%Y-%m-%d' -d "-$day days"`
bgtime="$dayT 00:00"
date=$dayT