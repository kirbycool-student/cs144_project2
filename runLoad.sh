#!/bin/bash

# Run the drop.sql batch file to drop existing tables
# Inside the drop.sql, you sould check whether the table exists. Drop them ONLY if they exists.
mysql CS144 < drop.sql

# Run the create.sql batch file to create the database and tables
mysql CS144 < create.sql

# Compile and run the parser to generate the appropriate load files
ant
ant run-all

# If the Java code does not handle duplicate removal, do this now
echo "Sorting files"
for file in *.csv; do
  echo "$file"
  sort $file | uniq >> temp
  rm $file
  mv temp $file
done

echo "Fixing User"

prevUserId=""
prevLine=""
while read line
do
    userId=$(echo $line | cut -f1 -d",")
    if [ "$userId" != "$prevUserId" ]; then
      if [ "$prevLine" != "" ]; then
        echo "$prevLine" >> temp
      fi
    fi
    prevUserId=$userId
    prevLine=$line
done < User.csv
echo "$prevLine" >> temp
rm User.csv
mv temp User.csv

echo "Loading"

# Run the load.sql batch file to load the data
mysql CS144 < load.sql

# Remove all temporary files
rm *.csv
