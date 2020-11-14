Activity 1

There are 4 files in Activity 1 inside folder newsservice:
1. NewsService.js - Main Api object which has 5 functions.
  i. addStory - adds story to the persistence store. While adding it generates a unique id for a news story.
     Params:
     title    - string
     content  - string
     author   - string
     isPublic - boolean
     date - Any valid Javascript Date object format:
        ISO Date	"2015-03-25" (The International Standard)
        Short Date	"03/25/2015"
        Long Date	"Mar 25 2015" or "25 Mar 2015"
       

    returns id - int (to uniquely identify the news service in the persistence store)
    
  ii. updateTitle - updates new Title for news story identified using id.
     params:
        id - int (News story's id)
        newTitle - string

    iii. updateContent - updates content for news story
     params:
        id - int (News story's id)
        newContent - string

  iv. deleteStory - deletes news story identified using id
     params:
        id - int (News story's id)

  v. getStoriesForFilter - To filter news stories and get a Map {id: NewsStory{title, content, author, isPublic, date} }
       params:
       filter {
           id : int - to filter by news story id.
           title : string,
           author : string,
           dateRange : {
               startDate: "valid javascript date obj format"
               endDate: "valid javascript date obj format"
           }
       }
       Eg:
        var filter = {
            'title' : 'test title',
            'dateRange': {
                startDate: '09/22/2020',
                endDate: '09/23/2020'
            },
            'author': 'demoAuthor'
        }

        returns a map with all {news story id - NewsStory object}

Examples:

Object Creation:
var NewsService = require('./NewsService');
var newsService = new NewsService();

1. addStory:
var id = newsService.addStory('title', 'content', 'author', true, '09/30/2020');

2. updateTitle:
newsService.updateTitle(2, 'New test title 5'); // say id = 2

3. updateContent:
newsService.updateContent(2, 'New test content for story 5'); // say id = 2

4. deleteStory:
newsService.deleteStory(2); // say id = 2

5. getStoriesForFilter:
var filter = {
    'title' : 'test title',
    'dateRange': {
        startDate: '09/22/2020',
        endDate: '09/23/2020'
    },
    'author': 'demoAuthor'
}
var stories = newsService.getStoriesForFilter(filter);

2. PersistenceStore.js - Object responsible for storing and fetching data from the Persistence Store.
Data is stored in a file - persistencestore.json in JSON format. [Extra credit 1]
Data is writen to the file in a synchronous way.
When a PersistenceStore object is created, it performs certain checks and initializations.
To avoid overhead caused while creation of object, i have made it a singleton and hence there exists only
one instance of PersistenceStore which can be obtained by invoking getPersistenceStoreInstance function.

NewsService uses this single instance of PersistenceStore object internally to store and fetch data.

3. NewsStory object - which has the attributes: title, content, author, isPublic and date
getStoriesForFilter api function returns a map of { id to NewsStory object }

4. persistencestore.json
All news stories are stored in persistencestore.json in json format.

Activity 1 Test cases:
To run execute: node NewsService_test.js
