# User Directory App
## This is an offline-first Android application that displays a list of 10 users fetched from the JSONPlaceholder API. The app uses Room Database to store all of the data locally. It then displays the data from the local cache, meaning that the app will still work even without an internet connection. Users can also search for a specific user's name or email, filtering results directly from the Room database.

### What the app looks like with NO internet connection:
<img width="350" height="771" alt="Screenshot 2025-11-14 at 7 36 00 PM" src="https://github.com/user-attachments/assets/4ecc8b38-0c9c-41e0-8b29-c863949ed4d6" />

### When there is internet connection:
<img width="346" height="766" alt="Screenshot 2025-11-14 at 7 37 15 PM" src="https://github.com/user-attachments/assets/8b2d5236-44d1-42da-9b22-ffa6eaea2342" />

### To implement the core functionality, I am using Retrofit in order to fetch user data from the JSON Placeholder API and then store it locally within the Room database. Using StateFlow, the UI always reads from Room to enable offline access, while local search is also enabled via a custom SQL query in the DAO. 
