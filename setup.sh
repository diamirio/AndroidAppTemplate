#!/usr/bin/env bash

# Copyright 2020 Tailored Media GmbH
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License. */

clear

bold=$(tput bold)
normal=$(tput sgr0)

systemName=$(uname -s)
baseDir=$(dirname $0)

echo 

# simple check if setup was already done
if [ ! -f "$baseDir/app/src/main/kotlin/com/tailoredapps/androidapptemplate/MyApp.kt" ]; then
    echo "${bold}Setup already completed!"
    echo
    exit 0
fi

echo "${bold}Welcome to the Android App Template setup assistant.${normal}"
echo Please enter the details of your project.
echo

read -p "${bold}App name (e.g. MainGau, note: without the suffix 'App'):${normal} " appName
# trim app name
appName=$(echo $appName | xargs echo -n)
appNameNoWhiteSpace=$(echo $appName | sed -e 's/ //g')
appClassName="${appNameNoWhiteSpace^}"
appClassName=${appClassName%"app"}
appClassName=${appClassName%"App"}
appClassName=${appClassName%"Application"}

# Check for correct app name
regex="^[A-Za-z0-9 ]+$"
if ! [[ ${appName} =~ $regex ]]; then
    echo
    echo Aborting, please enter a correct app name. echo
    exit 1
fi

read -p "${bold}Package name (e.g. com.tailoredapps.maingau):${normal} " packageName
# trim package name
packageName=$(echo $packageName | xargs echo -n)

# check for correct package name
regex="^[a-z][a-z0-9_]*(\.[a-z0-9_]+)+[0-9a-z_]$"
if ! [[ ${packageName} =~ $regex ]]; then
    echo
    echo Aborting, please enter a correct package name.
    echo
    exit 1
fi

packagePath=$(echo $packageName | sed 's/\./\//g')


# set app name

if [ "Darwin" == ${systemName} ]; then
    sed -i '' "s/val appName = \"AndroidAppTemplate/val appName = \"$appName/g" ${baseDir}/buildSrc/src/main/kotlin/Config.kt
else
    sed -i "s/val appName = \"AndroidAppTemplate/val appName = \"$appName/g" ${baseDir}/buildSrc/src/main/kotlin/Config.kt
fi

if [ "Darwin" == ${systemName} ]; then
    sed -i '' "s/val applicationId = \"com.tailoredapps.androidapptemplate/val applicationId = \"$packageName/g" ${baseDir}/buildSrc/src/main/kotlin/Config.kt
else
    sed -i "s/val applicationId = \"com.tailoredapps.androidapptemplate/val applicationId = \"$packageName/g" ${baseDir}/buildSrc/src/main/kotlin/Config.kt
fi


# find and replace package name recursively

if [ "Darwin" == ${systemName} ]; then
    find ${baseDir} -not -path './.idea*' -a -not -path './.git*' -a -type f \( -iname \*.kt -o -iname \*.java -o -iname \*.xml -o -iname \*.gradle -o -iname \*.pro \) -exec sed -i '' "s/com\.tailoredapps\.androidapptemplate/$packageName/g" {} +
else
    find ${baseDir} -not -path './.idea*' -a -not -path './.git*' -a -type f \( -iname \*.kt -o -iname \*.java -o -iname \*.xml -o -iname \*.gradle -o -iname \*.pro \) -exec sed -i "s/com\.tailoredapps\.androidapptemplate/$packageName/g" {} +
fi

# find and replace app name recursively

if [ "Darwin" == ${systemName} ]; then
    find ${baseDir} -not -path './.idea*' -a -not -path './.git*' -a -type f \( -iname \*.kt -o -iname \*.java -o -iname \*.xml -o -iname \*.gradle \) -exec sed -i '' "s/MyApp/${appClassName}App/g" {} +
else
    find ${baseDir} -not -path './.idea*' -a -not -path './.git*' -a -type f \( -iname \*.kt -o -iname \*.java -o -iname \*.xml -o -iname \*.gradle \) -exec sed -i "s/MyApp/${appClassName}App/g" {} +
fi

# move app files

mkdir -p ${baseDir}/app/src/main/kotlin/${packagePath}
mkdir -p ${baseDir}/app/src/test/kotlin/${packagePath}
mkdir -p ${baseDir}/app/src/androidTest/kotlin/${packagePath}

if [ Darwin == ${systemName} ]; then
    mv ${baseDir}/app/src/main/kotlin/com/tailoredapps/androidapptemplate/* ${baseDir}/app/src/main/kotlin/${packagePath}
    mv ${baseDir}/app/src/test/kotlin/com/tailoredapps/androidapptemplate/* ${baseDir}/app/src/test/kotlin/${packagePath}
    mv ${baseDir}/app/src/androidTest/kotlin/com/tailoredapps/androidapptemplate/* ${baseDir}/app/src/androidTest/kotlin/${packagePath}
    mv ${baseDir}/app/src/main/kotlin/${packagePath}/MyApp.kt ${baseDir}/app/src/main/kotlin/${packagePath}/${appClassName}App.kt
else
    mv "${baseDir}/app/src/main/kotlin/com/tailoredapps/androidapptemplate"/* "${baseDir}/app/src/main/kotlin/${packagePath}"
    mv "${baseDir}/app/src/test/kotlin/com/tailoredapps/androidapptemplate"/* "${baseDir}/app/src/test/kotlin/${packagePath}"
    mv "${baseDir}/app/src/androidTest/kotlin/com/tailoredapps/androidapptemplate"/* "${baseDir}/app/src/androidTest/kotlin/${packagePath}"
    mv "${baseDir}/app/src/main/kotlin/${packagePath}/MyApp.kt" "${baseDir}/app/src/main/kotlin/${packagePath}/${appClassName}App.kt"
fi

# move core files

mkdir -p ${baseDir}/core/src/main/kotlin/${packagePath}
mkdir -p ${baseDir}/core/src/test/kotlin/${packagePath}

if [ Darwin == ${systemName} ]; then
    mv ${baseDir}/core/src/main/kotlin/com/tailoredapps/androidapptemplate/* ${baseDir}/core/src/main/kotlin/${packagePath}
    mv ${baseDir}/core/src/test/kotlin/com/tailoredapps/androidapptemplate/* ${baseDir}/core/src/test/kotlin/${packagePath}
else
    mv "${baseDir}/core/src/main/kotlin/com/tailoredapps/androidapptemplate"/* "${baseDir}/core/src/main/kotlin/${packagePath}"
    mv "${baseDir}/core/src/test/kotlin/com/tailoredapps/androidapptemplate"/* "${baseDir}/core/src/test/kotlin/${packagePath}"
fi

# move base-ui files

mkdir -p ${baseDir}/base-ui/src/main/kotlin/${packagePath}

if [ "Darwin" == ${systemName} ]; then
    mv ${baseDir}/base-ui/src/main/kotlin/com/tailoredapps/androidapptemplate/* ${baseDir}/base-ui/src/main/kotlin/${packagePath}
else
    mv "${baseDir}/base-ui/src/main/kotlin/com/tailoredapps/androidapptemplate"/* "${baseDir}/base-ui/src/main/kotlin/${packagePath}"
fi

# remove old folders

originalPackagePathParts=(com tailoredapps androidapptemplate)
newPackagePathParts=(`echo ${packageName} | sed 's/\./ /g'`)

if [[ $packageName != com.tailoredapps.androidapptemplate* ]]; then
    # New package name is not equal or subpackage of old package name

    deletePath=""
    for index in ${!originalPackagePathParts[*]}
    do
        if [ ${index} -ne 0 ]; then
            deletePath=${deletePath}/
        fi

        deletePath=${deletePath}${originalPackagePathParts[$index]}

        if  [ ${index} -eq ${#newPackagePathParts[@]} ] || [ ${originalPackagePathParts[$index]} != ${newPackagePathParts[$index]} ]; then
            break
        fi
    done

    rm -r ${baseDir}/app/src/main/kotlin/${deletePath}
    rm -r ${baseDir}/app/src/test/kotlin/${deletePath}
    rm -r ${baseDir}/app/src/androidTest/kotlin/${deletePath}

    rm -r ${baseDir}/core/src/main/kotlin/${deletePath}
    rm -r ${baseDir}/core/src/test/kotlin/${deletePath}

    rm -r ${baseDir}/base-ui/src/main/kotlin/${deletePath}

fi

echo
echo "${bold}Setup complete${normal}"
echo
echo
echo "${bold}Press Enter to close${normal}"

read