<h1 align="center">Dropdown </h1>

<p align="center">
	💠A customizable jetpack compose dropdown menu with cascade and animations.
</p>




<p align="center">
  <a href="https://devlibrary.withgoogle.com/authors/AndroidPoet"><img alt="Google" src="https://user-images.githubusercontent.com/13647384/162663007-d911f6ce-ac1b-4754-a63b-eadbef38087f.svg"/></a>
<br>
	<br>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://medium.com/@androidpoet/metaphor-make-your-app-shine-with-material-motion-animations-73e5ffc698b4"><img alt="Medium"       src="https://user-images.githubusercontent.com/13647384/162663072-9d93cb76-1af0-49fc-b003-372e536ae171.svg"/></a>
  <a href="https://github.com/AndroidPoet"><img alt="Profile" src="https://user-images.githubusercontent.com/13647384/162662962-82e3c1eb-baf8-4e21-ad26-d4c4e3c31e44.svg"/>
    <a href="https://androidweekly.net/issues/issue-509"><img alt="Android Weekly" src="https://androidweekly.net/issues/issue-509/badge"/></a>	
	</a>



<p align="center">
<a href="https://mailchi.mp/kotlinweekly/kotlin-weekly-295"><img alt="Kotlin Weekly" src="https://img.shields.io/badge/Featured%20in%20kotlinweekly-Issue%20%20295-blue.svg"/></a>

</p> <br>







<p align="center">
	<img src="https://user-images.githubusercontent.com/13647384/163097135-a2de3617-e0b8-4629-82e2-cb5d1110c051.svg" />

</p> <br>

<p align="center">

</p>

## Who's using Metaphor?
**👉 [Check out who's using Metaphor](/usecases.md)**

## Include in your project
[![Maven Central](https://img.shields.io/maven-central/v/io.github.androidpoet/metaphor.svg?label=Maven%20Central)](https://search.maven.org/artifact/io.github.androidpoet/metaphor)

### Gradle
Add the dependency below to your **module**'s `build.gradle` file:

```gradle
dependencies {
    implementation("io.github.androidpoet:metaphor:1.1.5")
}
```
Metaphor provides support for all four motion patterns
defined in the Material spec.

1.  [Container transform](#container-transform)
2.  [Shared axis](#shared-axis)
3.  [Fade through](#fade-through)
4.  [Fade](#fade)



<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/157047014-2cf69797-090f-41a3-97e9-a1aeda55307a.gif" width="32%"/>

</p>

## Container transform How to use In Fragments

```kotlin

//Start Fragments onclick// 
val extras = FragmentNavigatorExtras(view to item.pos.toString())
val action = ArtistListFragmentDirections.navToCharacterDetailFragment(item)
findNavController().navigate(action, extras)

//start fragment 
// inside on onViewCreated  
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    hold() // this is function is really important for the "ContainerTransform" it will hold the currant fragment view

}


//destination fragment		

// inside on onViewCreated  
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val metaphor = MetaphorFragment.Builder(fragment)
        .setExitDuration(300)
        .setView(view)
        .setTransitionName(args.data.pos.toString())
        .setExitAnimation(MetaphorAnimation.ContainerTransform)
        .setMotion(MaterialArcMotion())
        .build()
    metaphor.animate()
}

```

<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/157047720-d6dcb0ab-3fe4-4078-84f3-f3be70cbb0f4.gif" width="32%"/>

</p>

## Container transform How to use in views

```kotlin
//call this method with startView and add end view set Animation you want to perform


viewBinding.fabDetail.setOnClickListener {
    val meta = MetaphorView.Builder(viewBinding.fabDetail)
        .setDuration(300)
        .setEndView(viewBinding.controls)
        .setMetaphorAnimation(MetaphorAnimation.ContainerTransform)
        .setMotion(MaterialArcMotion())
        .build()
    meta.animate()
}



```




<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/157048740-76908bb0-0937-4a33-9759-894d389a46b1.gif" width="32%"/>

</p>

## Shared axis How to use In Fragments


```kotlin

//start fragment 

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // FadeThrough inside fragment

    val metaphor = MetaphorFragment.Builder(fragment)
        .setEnterDuration(300)
        .setEnterAnimation(MetaphorAnimation.SharedAxisXForward)
        .build()
    metaphor.animate()
}

//destination fragment		

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // FadeThrough inside fragment

    val metaphor = MetaphorFragment.Builder(fragment)
        .setEnterDuration(300)
        .setEnterAnimation(MetaphorAnimation.SharedAxisXForward)
        .build()
    metaphor.animate()
}


```

<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/157049004-82bd3875-f0a6-4853-98f4-ad2d166d1259.gif" width="32%"/>

</p>

## Shared axis How to use in views

```kotlin
//call this method with startView and add end view set Animation you want to perform

viewBinding.fabDetail.setOnClickListener {
    val meta = MetaphorView.Builder(viewBinding.fabDetail)
        .setDuration(300)
        .setEndView(viewBinding.controls)
        .setMetaphorAnimation(MetaphorAnimation.SharedAxisXForward)
        .build()
    meta.animate()
}



```


<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/157048740-76908bb0-0937-4a33-9759-894d389a46b1.gif" width="32%"/>

</p>

## Fade through How to use In Fragments


```kotlin

//start fragment 

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // FadeThrough inside fragment

    val metaphor = MetaphorFragment.Builder(fragment)
        .setEnterDuration(300)
        .setEnterAnimation(MetaphorAnimation.FadeThrough)
        .build()
    metaphor.animate()
}

//destination fragment		

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // FadeThrough inside fragment

    val metaphor = MetaphorFragment.Builder(fragment)
        .setEnterDuration(300)
        .setEnterAnimation(MetaphorAnimation.FadeThrough)
        .build()
    metaphor.animate()
}


```

<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/157051396-9eaa6437-5c86-4fd8-abba-00b0ebafac55.gif" width="32%"/>

</p>

## Fade through How to use in views


```kotlin

//call this method with startView and add end view set Animation you want to perform


viewBinding.fabDetail.setOnClickListener {
    val meta = MetaphorView.Builder(viewBinding.fabDetail)
        .setDuration(300)
        .setEndView(viewBinding.controls)
        .setMetaphorAnimation(MetaphorAnimation.FadeThrough)
        .build()
    meta.animate()
}


```




<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/157051144-645ebfed-a388-4c5c-a43d-d7c2f647ffbd.gif" width="32%"/>

</p>

## Fade  How to use In Fragments


```kotlin

//start fragment 
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // FadeThrough inside fragment

    val metaphor = MetaphorFragment.Builder(fragment)
        .setEnterDuration(300)
        .setEnterAnimation(MetaphorAnimation.MaterialFade)
        .build()
    metaphor.animate()
}

//destination fragment		

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // FadeThrough inside fragment

    val metaphor = MetaphorFragment.Builder(fragment)
        .setEnterDuration(300)
        .setEnterAnimation(MetaphorAnimation.Fade)
        .build()
    metaphor.animate()
}


```

<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/157052869-9e124cef-0b3e-416b-a577-9d515e76d428.gif" width="32%"/>

</p>

## Fade  How to use in views


```kotlin

//call this method with startView and add end view set Animation you want to perform

viewBinding.fabDetail.setOnClickListener {
    val meta = MetaphorView.Builder(viewBinding.fabDetail)
        .setDuration(300)
        .setEndView(viewBinding.controls)
        .setMetaphorAnimation(MetaphorAnimation.Fade)

        .build()
    meta.animate()
}



```



## Supported Animations

```kotlin
MetaphorAnimation.None
MetaphorAnimation.ContainerTransform
MetaphorAnimation.FadeThrough
MetaphorAnimation.Fade
MetaphorAnimation.SharedAxisXForward
MetaphorAnimation.SharedAxisYForward
MetaphorAnimation.SharedAxisZForward
MetaphorAnimation.SharedAxisXBackward
MetaphorAnimation.SharedAxisYBackward
MetaphorAnimation.SharedAxisZBackward
MetaphorAnimation.ElevationScaleGrow
MetaphorAnimation.ElevationScale

```
















### Create Metaphor Fragment with Kotlin DSL
We can also create an instance of the MetaphorFragment with the Kotlin DSL.



```kotlin
  val meta = metaphorFragment(this) {
    setEnterAnimation(MetaphorAnimation.Fade)
    setView(view)
    build()
}
meta.animate()

```

### Create Metaphor View with Kotlin DSL
We can also create an instance of the MetaphorView with the Kotlin DSL.



```kotlin
val meta = metaphorView(it) {
    setDuration(300)
    setEndView(viewBinding.controls)
    setMetaphorAnimation(MetaphorAnimation.Fade)
    setMotion(MaterialArcMotion())
    build()
}

meta.animate()

```




<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/157848865-d85ff703-0cac-4930-a02d-69b277df7ca4.png" width="80%"/>

</p>





images credit:https://unsplash.com/


## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/AndroidPoet/Metaphor/stargazers)__ for this repository. :star: <br>
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
