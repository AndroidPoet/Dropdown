<h1 align="center">Dropdown </h1>

<p align="center">
	💧A customizable jetpack compose dropdown menu with cascade and animations.
	
</p>




<p align="center">
  <a href="https://devlibrary.withgoogle.com/authors/AndroidPoet"><img alt="Google" src="https://user-images.githubusercontent.com/13647384/162663007-d911f6ce-ac1b-4754-a63b-eadbef38087f.svg"/></a>
<br>
	<br>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/>
  <a href="https://github.com/AndroidPoet"><img alt="Profile" src="https://user-images.githubusercontent.com/13647384/162662962-82e3c1eb-baf8-4e21-ad26-d4c4e3c31e44.svg"/>


</p> <br>


<p align="center">
	<img src="https://user-images.githubusercontent.com/13647384/165006800-c18b3de1-b1d8-41c2-99f1-792561629a2d.png"  width="30%"/>

</p> 

## Who's using Dropdown?
**👉 [Check out who's using Dropdown](/usecases.md)**

## Include in your project
[![Maven Central](https://img.shields.io/maven-central/v/io.github.androidpoet/dropdown.svg?label=Maven%20Central)](https://search.maven.org/artifact/io.github.androidpoet/dropdown)

### Gradle
Add the dependency below to your **module**'s `build.gradle` file:

```gradle
dependencies {
    implementation("io.github.androidpoet:dropdown:1.0.0")
}
```
	  
<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/165883253-8e81edaf-0a72-42a4-9a50-bed930e8828f.gif" width="32%"/>

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
	

## Gif uploading.... 
	  
Fade  | SharedAxisX | SharedAxisY | SharedAxisZ |
| :---------------: | :---------------: | :---------------: | :---------------: |
| <img src="https://user-images.githubusercontent.com/13647384/165888284-6fbfb8c6-e8c7-43ab-a97f-420e75381d0b.gif" align="center" width="100%"/> | <img src="https://user-images.githubusercontent.com/13647384/165883253-8e81edaf-0a72-42a4-9a50-bed930e8828f.gif" align="center" width="90%"/> | <img src="https://user-images.githubusercontent.com/13647384/165889212-3154f5db-ab21-49fb-99d6-617852ac72a7.gif" align="center" width="90%"/> | <img src="https://user-images.githubusercontent.com/13647384/165889519-490e5162-6c20-42e5-adc6-1d22ed716d5b.gif" align="center" width="90%"/> |  	  	  
	  
	  
	  
ElevationScale  | SlideIn(SlideOut) | SlideHorizontally | SlideVertically |
| :---------------: | :---------------: | :---------------: | :---------------: |
| <img src="https://user-images.githubusercontent.com/13647384/165008192-56ec4075-3379-49ab-8912-954c0755b704.gif" align="center" width="80%"/> | <img src="" align="center" width="100%"/> | <img src="" align="center" width="100%"/> | <img src="" align="center" width="100%"/> |		  
	  
Scale  | ExpandIn(Shrink) | ExpandHorizontally(ShrinkHorizontally) | ExpandVertically(ShrinkVertically) |
| :---------------: | :---------------: | :---------------: | :---------------: |
| <img src="" align="center" width="100%"/> | <img src="" align="center" width="100%"/> | <img src="" align="center" width="100%"/> | <img src="" align="center" width="100%"/> |		  
	  
  
[Demo with ](https://github.com/AndroidPoet/Dropdown/blob/master/app/src/main/java/com/androidpoet/cascademenucompose/MainActivity.kt)
	  
## Inspiration
This library was heavily inspired  by [ComposeCookBook](https://github.com/Gurupreet/ComposeCookBook).<br>

> A Collection on all Jetpack compose UI elements, Layouts, Widgets and Demo screens to see it's potential.
	  
	  
	  
<a href="https://www.flaticon.com/free-icons/list" title="list icons">List icons created by Freepik - Flaticon</a>




## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/AndroidPoet/Dropdown/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/AndroidPoet)__ on GitHub for cool projects! 🤩



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
