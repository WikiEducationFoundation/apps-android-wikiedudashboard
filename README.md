# Wiki Education Dashboard Android app

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/314da6eb3ef14d9e897a828464996791)](https://app.codacy.com/app/ujjwalagrawal17/apps-android-wikiedudashboard?utm_source=github.com&utm_medium=referral&utm_content=ujjwalagrawal17/apps-android-wikiedudashboard&utm_campaign=Badge_Grade_Dashboard)
[![Build status](https://api.travis-ci.org/WikiEducationFoundation/apps-android-wikiedudashboard.svg)](https://travis-ci.org/WikiEducationFoundation/apps-android-wikiedudashboard) 
[![Gitter](https://badges.gitter.im/Wiki-Education-Foundation/wikiedu-dashboard-android.svg)](https://gitter.im/Wiki-Education-Foundation/wikiedu-dashboard-android?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
<a href="https://play.google.com/store/apps/details?id=org.wikiedufoundation.wikiedudashboard.release"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" height=60px /></a>

The main feature of this app is to support Wikipedia education assignments, edit-a-thons, and other editing projects. It provides data and course management features for groups of editors — instructors, students, and others — who are working on Wikipedia, Wikidata, and other Wikimedia wikis. Users log in with their Wikipedia accounts (through OAuth) and allow the Dashboard to make edits on their behalf. The Dashboard automates many of the standard elements of organizing and participating in a Wikipedia classroom assignment, edit-a-thon, or other wiki contribution campaign. 

This project is a part of [WikiEdu Dashboard]( https://github.com/WikiEducationFoundation/WikiEduDashboard)

<h2>Screenshots </h2>

<table>
<tr>
<td>

![login page](https://github.com/WikiEducationFoundation/apps-android-wikiedudashboard/blob/master/screenshots/6.jpg)

</td>
<td>


![login page](https://github.com/WikiEducationFoundation/apps-android-wikiedudashboard/blob/master/screenshots/4.jpg)

</td>
<td>

![login page](https://github.com/WikiEducationFoundation/apps-android-wikiedudashboard/blob/master/screenshots/1.jpg)

</td>
</tr>
<tr>
<td>

![login page](https://github.com/WikiEducationFoundation/apps-android-wikiedudashboard/blob/master/screenshots/3.jpg)

</td>
<td>


![login page](https://github.com/WikiEducationFoundation/apps-android-wikiedudashboard/blob/master/screenshots/2.jpg)

</td>
<td>

![login page](https://github.com/WikiEducationFoundation/apps-android-wikiedudashboard/blob/master/screenshots/5.jpg)

</td>
</tr>


</table>

## Development

### Android App Development Set up

Please find info about the set up of the Android app in your development environment [here](docs/Android_App_Setup.md).

### Libraries used and their documentation

- Retrofit [Docs](http://square.github.io/retrofit/2.x/retrofit/)
- ButterKnife [Docs](http://jakewharton.github.io/butterknife/javadoc/)
- Espresso [Docs](https://google.github.io/android-testing-support-library/docs/espresso/)
- Lombok [Docs](https://projectlombok.org/)
- Glide [Docs](https://github.com/bumptech/glide)

### Project Conventions

There are certain conventions we follow in the project, we recommend that you become familiar with these so that the development process is uniform for everyone:

#### MVP

The project follows Model-View-Presenter design pattern and requires schematic interfaces for each component to be written first in interfaces and then implemented.   
All the interactions are done using interfaces only. This means any model, view or presenter will only be referenced by its interface. We do so it is easy to mock and test them and there is no discrepancy in the callable methods of the concrete class and the interface.  
We realize that MVP is opinionated and there is no strict boundary between the responsibility of each component, but we recommend following this style:
- `View` is passive and dumb, there is no logic to be exercised in View, only the ability to show data provided by the presenter through interface is present in the View. 
- `Presenter` is responsible for most of the business logic, manipulation of data and organising it for the view to present. All logic for the app is present here. The responsibility of presenter includes the fetching of data from external source, observing changes and providing the view with the latest data. It also needs to handle all View interactions that require any logic, such as UI triggers causing complex interactions. Notable exception for this is launching of an Activity on click of a button. There is no logic required in the action and is completely dependent on Android APIs. 
- `Model` has the responsibility to hold the data, load it intelligently from appropriate source, be it disk or network, monitor the changes and notify presenter about those, be self sufficient; meaning to update data accordingly as needed without any external trigger (saving the data in disk after updating from network and providing the saved data from next time on), but be configurable (presenter may be able to ask for fresh data from network). The presenter should not worry about the data loading and syncing conditions (like network connectivity, failed update, scheduling jobs, etc) as it is the job of model itself.

<h2> License </h2>

This software is open source, licensed under the [MIT License][1].


[1]: https://opensource.org/licenses/MIT 
