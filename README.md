![Android App Template Logo](https://user-images.githubusercontent.com/2580292/59103107-390a7b80-892e-11e9-9466-774d413697ee.jpg)

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-orange.svg) 
![Reactive](https://img.shields.io/badge/Reactive-coroutines-blue.svg)

This is an Android app template which can be used for new projects. A small example app is available [here](https://github.com/tailoredmedia/AndroidAppTemplateExample).

Check out [AndroidAppUtil](https://github.com/tailoredmedia/AndroidAppUtil) for several utility classes.


### Table of Contents
* [Technologies](#technologies)
* [Setup](#setup)
* [App structure](#app_structure)
* [Predefined Modules](#predefined_structure)
* [Module/Package structure](#module_structure)
* [Testing](#testing)
* [Other](#other)
    * [Dependencies](#dependencies)
        * [How to add a dependency](#adddependencies)
    * [ktlint](#ktlint)
    * [Resource Naming Conventions](#resource_naming_conventions)
* [Recommended Reading](#recommended_reading)
* [License](#license)


## Technologies <a name="technologies"></a>
* Kotlin
* AndroidX Jetpack
* AAC Navigation for in-app navigation
* ViewBinding
* Koin for dependency injection
* Retrofit/OkHttp/Gson for networking
* Reaktor (MVI) as architectural pattern
* (Room for local data storage)


## Setup <a name="setup"></a>
1. Create your **git** repository and check it out on your machine.
2. Add `git@github.com:tailoredmedia/AndroidAppTemplate.git` as remote and merge the latest commit into your repository (you most likely need to `--allow-unrelated-histories`).
3. Run the provided `setup.sh` script to rename files and properties according to project. Afterwards delete `setup.sh` from your repository.
`setup.sh` script needs bash version 4+. [MacOS has bash version 3.2 pre-installed and needs to be upgraded](https://itnext.io/upgrading-bash-on-macos-7138bd1066ba)!
4. Change `README.md` content to something appropriate.
5. Commit and Push to your **git** repository.


## App structure <a name="app_structure"></a>
Features should be contained in a separate module. The `core` module should contain shared code, for example *api* or *database* classes that are needed in multiple feature modules. The `app` module contains Android app related (*ui*) code. Depending on the project size 

* each feature could be contained in a separated module to support and promote reusability, such as for example a `login` module or a `map` module. Each module then contains all the necessary code for the module to **live** on its own. For example a `login` module could contain the login *api*, the token storage *database* and the *views* containing the login user interface.

* or all *ui* related elements are located in the `app` module in the according feature packages and the `core` module contains business logic that can potentially be shared with other feature or ui modules in future expansions of the application.

Remember however: app and module structure should be thought through separately for every project.


## Predefined Modules <a name="predefined_structure"></a>
Despite you and your *brain* being the judge of how the project should best be structured, at least **three** modules should be present:

1. `core`: A base Kotlin or Android module containing reusable (business logic) code; such as Api, Database or Repos.
2. `base-ui`: A base Android module containing reusable UI components.
3. `app`: An Android module containing the application.

When creating new modules, you should probably use (apply) one of the predefined library-module gradle files: `gradle/library-module-android.gradle` or `gradle/library-module.gradle`.


## Module/Package structure <a name="module_structure"></a>
* Model classes should be located in a `model` package.
* Network related classes and interfaces (e.g. networking api's) are located in a `remote` package.
* Local storage related classes (e.g. databases or dao's) are located in a `local` package.
* Classes that do not correspond to a certain feature, should either be located in an `all` package, an `util` package or at the top level of the module.

These rules can be applied to either whole *modules* or *packages* depending on if you have feature modules or feature packages. An example for such a module/package structure can be found [here](https://github.com/tailoredmedia/AndroidAppTemplateExample).


## Testing <a name="testing"></a>
Every module should contain tests for its use cases:

* `test`: Write unit tests for every `ViewModel` or `Service`/`Repository`. Mockito or PowerMock can be used to mock objects and verify correct behaviour.
* `androidTest`: Write UI tests for common actions in your app. Use JUnit 4 Tests with Espresso. Some helper methods are available in EspressoUtils.

The dependencies for testing are located in the `gradle/test-dependencies-android.gradle` and `gradle/test-dependencies.gradle` files. If your `module` already implements `gradle/library-module-android.gradle` or `gradle/library-module.gradle`, then these dependencies are automatically added to the `module`.

If your module does not implement these standard library gradle files, add the test dependencies with:

``` groovy
apply from: rootProject.file("gradle/XXX.gradle")
```


## Other <a name="other"></a>


### Dependencies <a name="dependencies"></a>

**All** dependencies are located in the `Libs.kt` file in the `buildSrc` folder. To implement them use `implementation Libs.XXX`.

Checking whether dependencies are ready to be updated, use `./gradlew refreshVersions`. Afterwards the newer version is added as comments to the `versions.properties` file. Look [here](https://jmfayard.github.io/refreshVersions/) for the `refreshVersions` gradle plugin that is used for that.


#### How to add a Dependency <a name="adddependencies"></a>

If you want to add a new dependency, add it to the module's `build.gradle` as you would normally:

``` groovy
def room_version = "1.0.0"
implementation "androidx.room:room-runtime:$room_version"
```

Afterwards execute `./gradlew buildSrcVersions`. This task then extracts the dependency, adds it to `Libs.kt`, adds its version to `versions.properties` and automatically adds any updates next to the version if there is any.
**Do not** add your dependency manually to `Libs.kt` - this works but is discouraged.

After the plugin has added your dependency to the `Libs.kt` file, replace the lines in your `build.gradle`:

``` groovy
implementation(Libs.room_runtime)
```

### ktlint <a name="ktlint"></a>
[ktlint](https://ktlint.github.io/) is a *Kotlin* linter and formatter. Using it is required to keep the code base clean and readable.

Use `./gradlew ktlintCheck` to lint your code.

To conform to the rules either:

* configure AndroidStudio [accordingly](https://github.com/pinterest/ktlint#-with-intellij-idea).
* use `./gradlew ktlintApplyToIdea` to overwrite IDE style files. Read more [here](https://github.com/JLLeitschuh/ktlint-gradle).


### Resource Naming Conventions <a name="resource_naming_conventions"></a>

The goal of these conventions is to reduce the effort needed to read and understand code and also enable reviews to focus on more important issues than arguing over syntax.

**Bold** rules should be applied. *Italic* rules are optional.

| Component        | Rule             | Example                   |
| ---------------- | ---------------------- | ----------------------------- |
| Layouts | **\<what\>**\_**\<where\>**.xml | `activity_main.xml`, `item_detail.xml` |
| Sub-Layouts | **\<what\>**\_**\<where\>**\_**\<description\>**.xml | `activity_main_appbar.xml` |
| Strings | **\<where\>**\_**\<what\>**\_**\<description\>** | `detail_tv_location` |
| Drawables | **\<what\>**\_**\<where\>**\_**\<description\>** | `btn_detail_background`, `card_overview_background` |
| Icons | ic_**\<description\>**\_**\<where\>**.xml | `ic_close.xml`, `ic_location_pin_detail.xml` |
| Dimensions | *\<where\>*\_**\<what\>**\_*\<description\>*\_*\<size\>* | `margin`, `detail_height_card`, `textsize_small` |
| Styles | **\<What\>**\.**\<Description\>** | `Text.Bold`, `Ratingbar.Preview` |
| Component Ids | **\<what\>\<Description\>** | `btnOpen`, `tvTitle` |


## Recommended Reading <a name="recommended_reading"></a>
* [Kotlin](https://kotlinlang.org/docs/reference/)
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/basics.html)
* [Kotlin Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
* [Navigation Architecture Component](https://developer.android.com/topic/libraries/architecture/navigation/)
* [ViewBinding](https://developer.android.com/topic/libraries/view-binding)
* [control](https://github.com/floschu/control/)
* [Koin](https://insert-koin.io/)
* [Retrofit](http://www.vogella.com/tutorials/Retrofit/article.html)
* [Room](http://www.vogella.com/tutorials/AndroidSQLite/article.html)


## License <a name="license"></a>
```
Copyright 2020 Tailored Media GmbH.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
