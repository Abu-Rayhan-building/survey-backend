# !/bin/bash
 
cd ../survey-frontend
git fetch
git reset --hard origin/master
npm install
npm run build
cp -r ./build/. ../src/main/resources/META-INF/resources
