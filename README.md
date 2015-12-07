# android-location-polling

Current demo uses `AlarmManager` to start polling service with associated task each 2 minutes. If you want to update your backend more frequently, consider using `Handler`'s `postDelayed` method instead to increase battery life.
<p>To send location data to server I use Retrofit 2.0 library and simple `DTO` object. For dependency injection I use Dagger 2.0.
