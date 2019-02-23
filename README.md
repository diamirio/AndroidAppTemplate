# Android App Template
![Kotlin](https://img.shields.io/badge/Language-Kotlin-orange.svg) ![Android](https://img.shields.io/badge/Platform-Android-green.svg) ![MVI](https://img.shields.io/badge/Architecture-MVI-red.svg)

This is an Android app template which can be used for new projects. A small example app is available [here](https://github.com/tailoredmedia/AndroidAppTemplateExample).  
Check out [AndroidAppUtil](https://github.com/tailoredmedia/AndroidAppUtil) for several utility classes.

## Technologies used

* Kotlin
* Android Architecture Component Navigation for navigation
* Koin for dependency injection
* Retrofit/OkHttp/Gson for networking
* AndroidReactor (MVI) as architectural pattern
* (Room for local data storage)

## App structure

For each feature a separate module should be created. The `core` module should be implemented in each module and contains code that is needed for all modules, for example an api or a database that is needed in multiple feature modules. The `app`module contains Android app related code. Depending on the project size each feature could be contained in a separated module to support and promote reusability, such as for example a `login` module or a `map` module. Each module then contains all the necessary code for the module to "live" on its own. For example a `login` module could contain the login api, the token storage database and the view containing the login user interface. However app and module structure should be thought through for every project separately.

## Module structure

* Koin modules for dependency injection are located in a `di` package.
* Model classes are located in a `model` package.
* Network related classes and interfaces (e.g. networking api's) are located in a `remote` package.
* Local storage related classes (e.g. databases or dao's) are located in a `local` package.
* UI related classes (Activities, Fragments, Views, ViewModels,...) are located in a `ui` package. Use the interfaces and abstract classes from the `ui.base` subpackage.
* Utility classes are located in a `util` package.

## Testing

* Every module should contain tests for its use cases.
* `test`: Write unit tests for every `Reactor`. Mockito or PowerMock can be used to mock objects and verify correct behaviour. Add the `RxSchedulersOverrideRule` to prevent errors with RxJava.
* `androidTest`: Write UI tests for common actions in your app. Use JUnit 4 Tests with Espresso. Some helper methods are available in EspressoUtils.

## Recommended Reading

* [Navigation Architecture Component](https://developer.android.com/topic/libraries/architecture/navigation/)
* [RxJava](http://www.vogella.com/tutorials/RxJava/article.html)
* [AndroidReactor](https://github.com/floschu/AndroidReactor)
* [Koin](https://insert-koin.io/)
* [Room](http://www.vogella.com/tutorials/AndroidSQLite/article.html)
* [Retrofit](http://www.vogella.com/tutorials/Retrofit/article.html)

## License

```
Copyright 2019 Tailored Media GmbH.

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
