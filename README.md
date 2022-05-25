# DoggyApp
## Demo on Emulator
![demo](https://user-images.githubusercontent.com/10107412/148344449-7aa468a9-7ad4-4f98-bc83-a792e81fde56.gif)
## Demo on Android Phone
https://user-images.githubusercontent.com/10107412/148344628-0e779bb4-88cc-4c4d-bd2c-d2dfb0a20750.mp4
## MainActivity Screenshot
![ss](https://user-images.githubusercontent.com/10107412/148348312-b53aad3f-38a7-43d4-a14a-9211ed4ef6cb.jpg)
## Gallery Screenshot
![ss2](https://user-images.githubusercontent.com/10107412/148348754-c996cd22-1faa-434d-bde7-a5080356bf7d.jpg)
## Favorites Screenshot
![ss3](https://user-images.githubusercontent.com/10107412/148349922-b39903be-47c7-4647-a339-5c91abb1b971.jpg)
## Current Functionality
### Dependencies 
- `retrofit` -> used to make api calls to the dog api
- `glide` -> used to display pictures from urls received from dog api
- `room` -> used to access local SQLite Database
- `coroutines` -> used to make IO calls to database and api without locking the Main thread
- `dagger - hilt` -> used to do dependency injection providing a Singleton instance of local database and retrofit
### Screens
- Main/Home Screen - Displays lists of all breeds and sub breeds
- Gallery Screen - Shows pictures of currently selected breed
- Favorites Screen - Shows all of your saved favorites with option to remove from favorites
- Email Dialog - Opens after clicking on the floating action button. It allows you to share your favorites via email
### Other Features
- Favorites persist between reloads of the app
- Favorite Dog Images can be viewed even when offline
### Error Handling
- If by chance one of the api call fails, a toast message will be displayed with the error message
## TODO
- [ ] add testing
- [ ] download all images of breed with progress bar using background woker
- [ ] update app to use MVVM pattern
