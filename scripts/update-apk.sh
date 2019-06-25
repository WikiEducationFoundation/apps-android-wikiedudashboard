#!/bin/bash
set -e

git config --global user.name "Travis CI"
git config --global user.email "noreply+travis@wikiedu.org"

# export DEPLOY_BRANCH=${DEPLOY_BRANCH:-master}
# export PUBLISH_BRANCH=${PUBLISH_BRANCH:-production}

# if [ "$TRAVIS_PULL_REQUEST" != "false" -o "$TRAVIS_REPO_SLUG" != "WikiEducationFoundation/apps-android-wikiedudashboard" ] || ! [ "$TRAVIS_BRANCH" == "$DEPLOY_BRANCH" -o "$TRAVIS_BRANCH" == "$PUBLISH_BRANCH" ]; then
#	echo "We upload apk only for changes in development or master, and not PRs. So, let's skip this shall we ? :)"
#	exit 0
# fi


git clone --quiet --branch=apk https://ujjwalagrawal17:$GITHUB_API_KEY@github.com/WikiEducationFoundation/apps-android-wikiedudashboard apk > /dev/null
cd apk

# if [ "$TRAVIS_BRANCH" == "$PUBLISH_BRANCH" ]; then
# 	/bin/rm -f  open-event-master-app-playStore-release.apk open-event-master-app-fdroid-release.apk open-event-master-app-playStore-debug.apk open-event-master-app-fdroid-debug.apk
# else
#	/bin/rm -f open-event-dev-app-fdroid-debug.apk open-event-dev-app-playStore-debug.apk open-event-dev-app-playStore-release.apk open-event-dev-app-fdroid-release.apk
# fi

\cp -r ../app/build/outputs/apk/debug/app-debug.apk .
\cp -r ../app/build/outputs/apk/release/app-release.apk .
# \cp -r ../app/build/outputs/apk/playStore/*/**.apk .
# \cp -r ../app/build/outputs/apk/fdroid/*/**.apk .
# \cp -r ../app/build/outputs/apk/playStore/debug/output.json playStore-debug-output.json
# \cp -r ../app/build/outputs/apk/playStore/release/output.json playStore-release-output.json
# \cp -r ../app/build/outputs/apk/fdroid/debug/output.json fdroid-debug-output.json
# \cp -r ../app/build/outputs/apk/fdroid/release/output.json fdroid-release-output.json

# if [ "$TRAVIS_BRANCH" == "$PUBLISH_BRANCH" ]; then
#	for file in app*; do
#		if [[ $file = "open-event"* ]]; then
#			continue
#		fi
#		mv $file open-event-master-${file%%}
#	done
# fi

# if [ "$TRAVIS_BRANCH" == "$DEPLOY_BRANCH" ]; then
#	for file in app*; do
#		if [[ $file = "open-event"* ]]; then
#			continue
#		fi
#		mv $file open-event-dev-${file%%}
#	done
# fi

# Signing Apps

# if [ "$TRAVIS_BRANCH" == "$PUBLISH_BRANCH" ]; then
#    echo "Push to master branch detected, signing the app..."
#    cp wikiedu-dashboard-master-app-playStore-release-unsigned.apk wikiedu-dashboard-master-app-playStore-release-unaligned.apk
#    jarsigner -verbose -tsa http://timestamp.comodoca.com/rfc3161 -sigalg SHA1withRSA -digestalg SHA1 -keystore ../scripts/key.jks -storepass $STORE_PASS -keypass $KEY_PASS wikiedu-dashboard-master-app-playStore-release-unaligned.apk $ALIAS
#    ${ANDROID_HOME}/build-tools/28.0.3/zipalign -v -p 4 wikiedu-dashboard-master-app-playStore-release-unsigned.apk wikiedu-dashboard-master-app-playStore-release.apk
# fi

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

# Publish App to Play Store
# if [ "$TRAVIS_BRANCH" != "$PUBLISH_BRANCH" ]; then
#    echo "We publish apk only for changes in master branch. So, let's skip this shall we ? :)"
#    exit 0
# fi

# gem install fastlane
# fastlane supply --apk open-event-master-app-playStore-release.apk --track alpha --json_key ../scripts/fastlane.json --package_name $PACKAGE_NAME
