<h1 align="center">Dropdown</h1>

<p align="center">
    💧A Powerful and customizable Compose Multiplatform dropdown menu with cascade and animations.
</p>

<p align="center">
  <a href="https://devlibrary.withgoogle.com/authors/AndroidPoet"><img alt="Google" src="https://user-images.githubusercontent.com/13647384/162663007-d911f6ce-ac1b-4754-a63b-eadbef38087f.svg" height="25"/></a>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg" height="25"/></a>
  <a href="https://github.com/AndroidPoet"><img alt="Profile" src="https://user-images.githubusercontent.com/13647384/162662962-82e3c1eb-baf8-4e21-ad26-d4c4e3c31e44.svg" height="25"/></a>
</p>

<p align="center">
  <img src="https://user-images.githubusercontent.com/13647384/166132988-da60fd52-7b1d-48c4-b944-e6c20a8cb619.png" width="50%"/>
</p>

## Who's using Dropdown?

**👉 [Check out who's using Dropdown](/usecases.md)**

## Include in your project

[![Maven Central](https://img.shields.io/maven-central/v/io.github.androidpoet/dropdown.svg?label=Maven%20Central)](https://search.maven.org/artifact/io.github.androidpoet/dropdown)

### Gradle

Add the dependency below to your **module**'s `build.gradle` file:

```gradle
dependencies {
   implementation("io.github.androidpoet:dropdown:$version"")
}
```

For Kotlin Multiplatform, add the dependency below to your **module**'s `build.gradle.kts` file:

```gradle
sourceSets {
    val commonMain by getting {
        dependencies {
           implementation("io.github.androidpoet:dropdown:$version"")
        }
    }
}
```

<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/166186269-091efb42-a143-4ccd-b9c4-0e0c4748c56e.gif"/>

</p>	  

### Create Menu Builder

Create an instance of the `Menu Builder`.

```kotlin  

fun getMenu(): MenuItem<String> {
    val menu = dropDownMenu<String> {
        item("about", "About") {
            icon(Icons.TwoTone.Language)
        }
        item("copy", "Copy") {
            icon(Icons.TwoTone.FileCopy)
        }
        item("share", "Share") {
            icon(Icons.TwoTone.Share)
            item("to_clipboard", "To clipboard") {
                item("pdf", "PDF")
                item("epub", "EPUB")
                item("web_page", "Web page")
                item("microsoft_word", "Microsoft word")
            }
            item("as_a_file", "As a file") {
                item("pdf", "PDF")
                item("epub", "EPUB")
                item("web_page", "Web page")
                item("microsoft_word", "Microsoft word")
            }
        }
        item("remove", "Remove") {
            icon(Icons.TwoTone.DeleteSweep)
            item("yep", "Yep") {
                icon(Icons.TwoTone.Done)
            }
            item("go_back", "Go back") {
                icon(Icons.TwoTone.Close)
            }
        }
    }
    return menu
}


```

### Create Dropdown menu

Create an instance of the `Dropdown menu`.

```kotlin  	  
@ExperimentalAnimationApi
@Composable
fun Menu(isOpen: Boolean = false, setIsOpen: (Boolean) -> Unit, itemSelected: (String) -> Unit) {
    val menu = getMenu()
    Dropdown(

        isOpen = isOpen,
        menu = menu,
        colors = dropDownMenuColors(Teal200, White),
        onItemSelected = itemSelected,
        onDismiss = { setIsOpen(false) },
        offset = DpOffset(8.dp, 0.dp),
        enter = EnterAnimation.ElevationScale,
        exit = ExitAnimation.ElevationScale,
        easing = Easing.FastOutSlowInEasing,
        enterDuration = 400,
        exitDuration = 400

    )
}


```

## Supported Animations

## Enter Animations

```kotlin
EnterAnimation.FadeIn
EnterAnimation.SharedAxisXForward
EnterAnimation.SharedAxisYForward
EnterAnimation.SharedAxisZForward
EnterAnimation.ElevationScale
EnterAnimation.SlideIn
EnterAnimation.SlideInHorizontally
EnterAnimation.SlideInVertically
EnterAnimation.ScaleIn
EnterAnimation.ExpandIn
EnterAnimation.ExpandHorizontally
EnterAnimation.ExpandVertically
```

## Exit Animations

```kotlin
ExitAnimation.FadeOut
ExitAnimation.SharedAxisXBackward
ExitAnimation.SharedAxisYBackward
ExitAnimation.SharedAxisZBackward
ExitAnimation.ElevationScale
ExitAnimation.SlideOut
ExitAnimation.SlideOutHorizontally
ExitAnimation.SlideOutVertically
ExitAnimation.ScaleOut
ExitAnimation.ShrinkOut
ExitAnimation.ShrinkHorizontally
ExitAnimation.ShrinkVertically
```

## Easing

```kotlin
Easing.FastOutSlowInEasing
Easing.LinearOutSlowInEasing
Easing.FastOutLinearInEasing
Easing.LinearEasing
```
| Fade | SharedAxisX | SharedAxisY | SharedAxisZ |
|:---:|:---:|:---:|:---:|
| <img src="https://user-images.githubusercontent.com/13647384/165891972-ec0a4239-8998-4052-9338-1daa67fc0dce.gif" width="150px"/> | <img src="https://user-images.githubusercontent.com/13647384/165891266-fd51e64d-d871-4438-acb7-4a2ec43d3efd.gif" width="150px"/> | <img src="https://user-images.githubusercontent.com/13647384/165892730-2e2a4c81-6196-47d0-8e12-02668ccd9d9c.gif" width="150px"/> | <img src="https://user-images.githubusercontent.com/13647384/165893392-65c7a7a9-d7b6-47bb-8141-5a49aa26958b.gif" width="150px"/> |
| ElevationScale | SlideIn(SlideOut) | SlideHorizontally | SlideVertically |
| <img src="https://user-images.githubusercontent.com/13647384/165893392-65c7a7a9-d7b6-47bb-8141-5a49aa26958b.gif" width="150px"/> | <img src="https://user-images.githubusercontent.com/13647384/165894211-ff05c4c3-4e1d-47bb-8aac-b7272ccc2750.gif" width="150px"/> | <img src="https://user-images.githubusercontent.com/13647384/165894765-2d0821bf-dabe-4f11-848f-f09180d4f0df.gif" width="150px"/> | <img src="https://user-images.githubusercontent.com/13647384/165895557-231b55dc-1684-4b5f-b948-a640779f0eca.gif" width="150px"/> |
| Scale | Expand | Expand Horizontally | Expand Vertically |
| <img src="https://user-images.githubusercontent.com/13647384/165896166-7d62bb01-2701-4c24-a8b0-9244409f30ec.gif" width="150px"/> | <img src="https://user-images.githubusercontent.com/13647384/165896619-d8aef09a-deea-4e75-926b-446caa3ad677.gif" width="150px"/> | <img src="https://user-images.githubusercontent.com/13647384/165897083-cc5789aa-c086-40c4-903f-6188099fdae8.gif" width="150px"/> | <img src="https://user-images.githubusercontent.com/13647384/165897576-304d84ea-bb2d-4fea-a110-e4ac77eccac2.gif" width="150px"/> |
<a href="https://www.flaticon.com/free-icons/list" title="list icons">List icons created by
Freepik - Flaticon</a>

## Find this repository useful? :heart:

Support it by joining __[stargazers](https://github.com/AndroidPoet/Dropdown/stargazers)__ for this
repository. :star: <br>
Also, __[follow me](https://github.com/AndroidPoet)__ on GitHub for more cool projects! 🤩

<a href="https://www.buymeacoffee.com/AndroidPoet" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>

# License

```xml
Copyright 2022 AndroidPoet (Ranbir Singh)

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