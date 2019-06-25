#!/bin/bash
set -e

git config --global user.name "Travis CI"
git config --global user.email "noreply+travis@wikiedu.org"

git clone --quiet --branch=apk https://ujjwalagrawal17:$GITHUB_API_KEY@github.com/WikiEducationFoundation/apps-android-wikiedudashboard apk > /dev/null
cd apk


\cp -r ../app/build/outputs/apk/debug/app-debug.apk .

# Create a new branch that will contains only latest apk
git checkout --orphan temporary

# Add generated APK
git add --all .
git commit -am "[Auto] Update Test Apk ($(date +%Y-%m-%d.%H:%M:%S))"

# Delete current apk branch
git branch -D apk
# Rename current branch to apk
git branch -m apk

# Force push to origin since histories are unrelated
git push origin apk --force --quiet > /dev/null

