**Name: Kayla**
**Computing ID: PRT5GG**

##Overview 
This app shows NCAA basketball scores using the ESPN-based API. Users can select a date and toggle between men's and women's games to view scores for that day. The app retrieves live data from the API and displays relevant information about each game. It also supports offline viewing by storing previously retrieved scores in a local Room database.

##Features
Each game shows the away and home team name as well as the status. Additional information is also shown depending on the status. For upcoming games, the user can see the start time. For live games, the user can see the current score, current period, and time remaining. For final games, the user can see the final score and the winner of the game. 

#Refresh
Users can manually refresh the game data using a refresh button.

##Loading Indicator
A circular loading indicator appears while the app retrieves data from the API

##Offline mode and persistance
The app stores retrieved game data in a local SQLite Room database. If the device is offline, the user can still see previously downloaded scores. When new scores are retrieved from the API, the database is updated to reflect the most recent game information.
