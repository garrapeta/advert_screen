### Sergio Torres - Exercise submission 

Thanks for taking the time to look at my code.


# Overview

In the interest of time, I have focused more in the backend and architecture than in creating a very detailed UI.

- The app is built using with a clean architecture: data, domain and presentation layers.
- As time was limited, I'm using the same models across the three layers. Normally, I would have created different models and mappers at each layer.
- The data layer is based in a Repository. This delegates into a DataSource and adds some behaviour such as data caching. In a real world app, the Repository would have more than one DataSource (ie: NetworkDataSource, 
SqlLiteDataSource) and would coordinate the synchronization between them.
- The domain layer connects the data layer to the presentation layer using Interactors, which implement Use Cases.
- The presentation layer is based in MVP. The presenter has no dependencies to Android classes.
- The data layer is ultimately connected to the Presenters with RXJava.
- DI with Dagger2.


## Remarkable points

- When the user marks one advert as favourite, the change in the icon in the ActionBar is triggered by an event that the Presenter receives from the Repository. 
The Presenter is observing the Ad, and when this is updated the Presenter is notified and updates the UI. 
The UI is synchronized to the Repo, and it is only updated to reflect changes in the resources ultimately originated in the data layer.
- Although I'm NOT using Loaders, the app does not unnecessarily re-fetch data upon a screen rotation. This is done by caching the loaded Ad in Repository.
- The data layer and the Presenter done with TDD and have some JUnit4 tests

## What I would have liked to do

- Espresso tests.
- Two flavours of the app: one "fake" flavour using the fake DataSource, and another using real DataSources (fetching from the web).
- Integration of the Google Maps view.
- UI transition with shared elements between one picture and a full-screen picture.


Thank you




# Gumtree Android app coding challenge (advert-screen)

## Before you start ...

Please do not fork our repository to create your project. Clone and then work in your own repository. Once you are done, send us a link to your repository to gumtreeuk.apps@gmail.com. Please allow yourself at least 1 hour of uninterrupted time for this task, but feel free to spend as much time on the task as you like and make the solution and the code as perfect as you like. Remember, though, "done is better than perfect" :).

## The task

Your task is to implement an Advert details screen for the Gumtree app. You can check an example at the link/image below. Additionally, feel free to have a look at some other Advert details screen examples by downloading the Gumtree application in google play (https://play.google.com/store/apps/details?id=com.gumtree.android)

![Advert Details Screen Example] (https://lh3.googleusercontent.com/PWA1l37j3Lj34M0qhyvt0XuCJtcOw56itrikl722cYJkSaAx-m-8UrzzGbr5rDESCbk=h900-rw)

## The app

Features:

- Mock the data endpoints in a way that you feel more comfortable with (e.g. use Content Providers or simple Java objects) so the screen can get the data from them.
- Support Adverts having more than one picture
- Support the feature of sharing an Advert
- Handle rotation
- Make sure screen is optimized for different android screen sizes

Keep in mind:

- Use of new android design patterns and/or libraries
- Gumtree app is built on: MVP, RXJava, Dagger2, Gradle, Espresso, Glide, etc.
- Easy to read and testable code

