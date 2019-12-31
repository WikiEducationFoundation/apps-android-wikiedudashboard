/**
* Object for holding dependencies version
 * numbers
***/
object Versions {
    // Sdk and tools
    val CompileSdkVersion = 28
    val MinSdkVersion = 19
    val TargetSdkVersion = 28
    val MaxSdkVersion = 28
    val VersionCode = 1
    val VersionName = "1.0"
    val SourceCompatibility = "1.8"
    val TargetCompatibility = "1.8"

    //Gradle
    val gradleVersion = "3.4.1"
    val safeArgs = "1.0.0-alpha04"

    //Dependencies
    val constraintLayoutVersion = "2.0.0-beta1"
    val coreTestingVersion = "2.0.0"
    val vectorDrawableVersion = "1.0.1"
    val cardViewVersion = "1.0.0"
    val butterknifeVersion = "10.1.0"
    val clansFabVersion = "1.6.4"
    val legacySupportVersion = cardViewVersion
    val browserVersion = cardViewVersion
    val glideVersion = "4.0.0"
    val leakcanaryVersion = "2.0-alpha-2"
    val rxjavaVersion = "2.3.0"
    val retrofitConverterVersion = "2.5.0"
    val okHttpLogginVersion = "3.14.2"
    val okhttpUrlConnectionVersion = "3.10.0"
    val transitionVersion = "1.0.1"
    val projectlombokVersion = "1.18.6"
    val appcompactVersion = "1.0.2"
    val annotationVersion = "1.1.0"
    val kotlinVersion = "1.3.31"
    val kotlinxCoroutineVersion = "1.2.1"
    val photoViewVersion = "2.3.0"
    val timberVersion = "4.7.1"
    val viewPager2Version = "1.0.0-beta04"
    val viewPagerdotsIndicator = "4.1.2"
    val circleIndicatorVersion = "2.1.4"
    val expressoContribVersion = "3.1.1"
    val expressoCoreVersion = expressoContribVersion
    val expressoIntents = expressoContribVersion
    val testExtJunit = annotationVersion
    val uiAutomatorVersion = "2.2.0"
    val workVersion = "2.1.0-alpha02"
    val truth = "0.42"
    val junitVersion = "4.12"
    val materialVersion = cardViewVersion
    val fragmentVersion = annotationVersion
    val navigationVersion = "2.1.0"
    val koinVersion = "2.1.0-alpha-1"
    val lifecycleVersion = "2.2.0-alpha05"
    val roomVersion = "2.1.0-rc01"
}

/**
 * Object for holding dependencies description
 *
 ***/
object Deps {

    //class paths
    val gradleVersion = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    val gradleKotlinVersion = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    val gradleNavigationVersion = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"
    val safeArgs = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"

    val constraintLayoutVersion = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    val vectorDrawableVersion = "androidx.vectordrawable:vectordrawable:${Versions.vectorDrawableVersion}"
    val cardViewVersion = "androidx.cardview:cardview:${Versions.cardViewVersion}"
    val butterknifeVersion = "com.jakewharton:butterknife:${Versions.butterknifeVersion}"
    val clansFabVersion = "com.github.clans:fab:${Versions.clansFabVersion}"
    val legacySupportVersion = "androidx.legacy:legacy-support-v4:${Versions.legacySupportVersion}"
    val browserVersion = "androidx.browser:browser:${Versions.browserVersion}"
    val glideVersion = "com.github.bumptech.glide:glide:${Versions.glideVersion}"

    val leakcanaryVersion = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanaryVersion}"
    val rxjavaVersion = "com.squareup.retrofit2:adapter-rxjava:${Versions.rxjavaVersion}"
    val retrofitConverterVersion = "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterVersion}"
    val okHttpLogginVersion = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogginVersion}"
    val okhttpUrlConnectionVersion = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttpUrlConnectionVersion}"
    val transitionVersion = "androidx.transition:transition:${Versions.transitionVersion}"
    val projectlombokVersion = "org.projectlombok:lombok:${Versions.projectlombokVersion}"
    val appcompactVersion = "androidx.appcompat:appcompat:${Versions.appcompactVersion}"
    val annotationVersion = "androidx.annotation:annotation:${Versions.annotationVersion}"
    val kotlinVersion = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    val kotlinxCoroutineVersion = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutineVersion}"

    // Image Viewer library
    val photoViewVersion = "com.github.chrisbanes:PhotoView:${Versions.photoViewVersion}"

    // Timber
    val timberVersion = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    // Testing dependencies
    val coreTestingVersion = "androidx.arch.core:core-testing:${Versions.coreTestingVersion}"
    val expressoContribVersion = "androidx.test.espresso:espresso-contrib:${Versions.expressoContribVersion}"
    val expressoCoreVersion = "androidx.test.espresso:espresso-core:${Versions.expressoCoreVersion}"
    val expressoIntents = "androidx.test.espresso:espresso-intents:${Versions.expressoIntents}"
    val testExtJunit = "androidx.test.ext:junit:${Versions.testExtJunit}"
    val uiAutomatorVersion = "androidx.test.uiautomator:uiautomator:${Versions.uiAutomatorVersion}"
    val workVersion = "androidx.work:work-testing:${Versions.workVersion}"
    val truth = "com.google.truth:truth:${Versions.truth}"
    val junitVersion = "junit:junit:${Versions.junitVersion}"

    // Motionlayout onboarding
    val viewPagerdotsIndicator = "com.tbuonomo.andrui:viewpagerdotsindicator:${Versions.viewPagerdotsIndicator}"
    val viewPager2Version = "androidx.viewpager2:viewpager2:${Versions.viewPager2Version}"

    //Material design
    val materialVersion = "com.google.android.material:material:${Versions.materialVersion}"
    val fragmentVersion = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
    val fragmentNavigationVersion = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    val navigationVersion = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    // circular dots
    val circleIndicatorVersion = "me.relex:circleindicator:${Versions.circleIndicatorVersion}"

    // Koin for Kotlin
    val koinCoreVersion = "org.koin:koin-core:${Versions.koinVersion}"
    // Koin AndroidX Scope features
    val koinScopeVersion = "org.koin:koin-androidx-scope:${Versions.koinVersion}"

    // Room Dependencies
    val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    val room =  "androidx.room:room-ktx:${Versions.roomVersion}"


    // Lifecycle components
    val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
}