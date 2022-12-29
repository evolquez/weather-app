# Weather App

## Current conditions 

This simple weather Android app displays a list of current weather conditions for each state capital.

The app display a list of 20 cities with:
- City name
- City country
- Current temp in Celsius
- Today's high/low temp in Celsius
- Precipitation
- A timestamp of when the data was updated

Weather information is consumed from [OpenWeather API] and updated when the app is opened. If offline, the app show cached data retrieved from local persistence.

## 5 Day Forecast

When selecting a city from the current conditions list the app navigate to a new screen that displays a 5 day forecast for that specific city. If offline, the data is loaded from local persistence.

## Technical Requirements
- Kotlin
- Target API 33
- Min API 23

## Implementations
- Room database.
- Retrofit.
- Jetpack (Androidx libraries Lifecycle)
  - LiveData
  - ViewModel
- Picasso for image loading
- Coroutines
- Dependency Injection with Dagger2
- Unit testing

## Screenshots
<img src="https://user-images.githubusercontent.com/8124733/209906215-458f0f18-8c6f-4cca-8edd-c14f1af5e8c5.png" width="350"/>

<img src="https://user-images.githubusercontent.com/8124733/209906223-163bc0b3-50f4-435e-917d-2d0960aaa49a.png" width="350"/>

https://user-images.githubusercontent.com/8124733/209906877-5ccac667-d9c3-4c74-b594-0fae9cb2d0d6.mov


[OpenWeather Api]: <https://openweathermap.org/api>
