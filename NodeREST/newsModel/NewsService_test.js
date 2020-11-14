'use strict'

var NewsService = require('./NewsService');

tests();

function tests() {

    var newsService = new NewsService();

    testAddNewsStory(newsService);
    var id = testAddNewsStory(newsService);  // R1
    testUpdateTitle(newsService, id); // R2
    testUpdateContent(newsService, id); //R3
    testDeleteStory(newsService, id); // R4
    testUpdateTitleForWrongNewsStory(newsService, 1000); // R2 wrong input

    addDataForFilterTesting(newsService);

    testGetStoriesWithTitleFilter(newsService, 'Test title'); // R5 a.
    testGetStoriesWithAuthorFilter(newsService, 'Author1'); // R5 b.
    testGetStoriesWithDateRangeFilter(newsService, '08/23/2020', '08/24/2020'); // R5 c.
    testGetStoriesWithTitleAndAuthorFilter(newsService, 'Test Filter title 2', 'Author1'); // R5 a and c
    testGetStoriesWithTitleAndAuthorAndDateRangeFilter(newsService, 'Test Filter title', 'Author1', '08/23/2020',  '08/23/2020'); // R5 a,b and c.

    console.log('--------------------------');
    console.log("All Test Cases For News Service API SUCCESSFUL");
    console.log('--------------------------');

}

function testAddNewsStory(newsService) {
    var title = 'Test title 1';
    var content = 'Content for title 1';
    var author = 'Author1';
    var isPublic = false;
    var date = new Date().getTime();

    printTestName("1 - Add News Story");

    var id = newsService.addStory(title, content, author, isPublic, date);
    console.log("added for id " + id);
    assertNumber(id);
    assert(newsService.getStoriesForFilter({'id':id})[id].title, 'Test title 1');

    console.log('--------------------------');
    console.log("Add news story test - SUCCESSFUL");
    console.log('--------------------------');
    return id;
}

function testUpdateTitle(newsService, id) {
    printTestName('2 - Update Title for news story with id: ' + id);
    try {
        newsService.updateTitle(id, 'New test title 2');
        assert(newsService.getStoriesForFilter({'id':id})[id]['title'], 'New test title 2');
    } catch(err) {
        console.error(err);
        console.log("Failed: Shouldn't throw exception");
        process.exit();
    }
    console.log('--------------------------');
    console.log("Update news story title test - SUCCESSFUL");
    console.log('--------------------------');
}

function testUpdateContent(newsService, id) {
    printTestName('3 - Update content for news story with id: ' + id);

    try {
        newsService.updateContent(id, 'New test content for story 3');
        assert(newsService.getStoriesForFilter({'id':id})[id]['content'], 'New test content for story 3');
        console.log('--------------------------');
        console.log("Update news story content test  - SUCCESSFUL");
        console.log('--------------------------');
    } catch(err) {
        console.error(err);
        console.log("Failed: Shouldn't throw exception");
        process.exit();
    }
}

function testDeleteStory(newsService, id) {
    printTestName('4 - Delete news story with id: ' + id);

    newsService.deleteStory(id);
    assert(newsService.getStoriesForFilter({'id':id})[id], undefined);
    console.log('--------------------------');
    console.log("Delete story test  - SUCCESSFUL");
    console.log('--------------------------');
}

function testUpdateTitleForWrongNewsStory(newsService, id) {
    printTestName('5 - Update title for wrong NewStory using non existing NewsStory ID: ' + id);
    try {
        newsService.updateTitle(id, 'New test title 2');
        console.log("Failed: update of non existing story should throw exception");
        process.exit();
    } catch(err) {
        assert(err.message, 'NewsStoryNotFound');
        console.log('--------------------------');
        console.log("Update title for wrong non existing news story test - SUCCESSFUL");
        console.log('--------------------------');
    }
}

function addDataForFilterTesting(newsService) {
    var title = 'Test Filter title 1';
    var content = 'Content for title 1';
    var author = 'Author1';
    var isPublic = false;
    var date = '08/23/2020';
    var id = newsService.addStory(title, content, author, isPublic, date);

    title = 'Test Filter title 2';
    content = 'Content for title 1';
    author = 'Author1';
    isPublic = true;
    date = '08/24/2020'
    id = newsService.addStory(title, content, author, isPublic, date);

    title = 'Test Filter title 3';
    content = 'Content for title 1';
    author = 'Author1';
    isPublic = true;
    date = '08/23/2020';
    id = newsService.addStory(title, content, author, isPublic, date);

}

function testGetStoriesWithTitleFilter(newsService, title) {
    var filter = {
        'title' : title,
    }
    printTestName('6 - Fetching News Stories having title : ' + title + ' as a substring');
    var stories = newsService.getStoriesForFilter(filter);
    if(stories.length <= 0) {
        console.log("Db should have atleast one story with this title");
        process.exit();
    }
    for(var i in stories) {
        assertContains(stories[i].title, title);
    }
    console.log(stories);
    console.log('--------------------------');
    console.log("Get News Stories for given title filter - SUCCESSFUL");
    console.log('--------------------------');
}

function testGetStoriesWithAuthorFilter(newsService, author) {
    var filter = {
        'author': author
    }
    printTestName('7 - Fetching News Stories published by author : ' + author);
    var stories = newsService.getStoriesForFilter(filter);
    var flag = 0;
    for(var i in stories) {
        
        assert(stories[i].author, author);
        flag = 1;
    }
    assert(flag, 1);
    console.log(stories);
    console.log('--------------------------');
    console.log("Get News Stories for given author filter - SUCCESSFUL");
    console.log('--------------------------');
}


function testGetStoriesWithDateRangeFilter(newsService, startRange, endRange) {

    printTestName('8 - Fetching news published within date range: ' + startRange + ' - ' + endRange);
    var filter = {
        'dateRange': {
            startDate: startRange,
            endDate: endRange
        }
    }
    let startRangeAsTimestamp = new Date(startRange).getTime();
    let endRangeAsStimeStamp = new Date(endRange).getTime();
    try {
        var stories = newsService.getStoriesForFilter(filter);
        var flag = 0;
        for(var i in stories) {
            if(new Date(stories[i].date).getTime() < startRangeAsTimestamp || new Date(stories[i].date).getTime() > endRangeAsStimeStamp) {
                console.log("Wrong story fetched: Outside date range");
                process.exit();
            }
         
            flag = 1;
        }
        assert(flag, 1);
        console.log(stories);
        console.log('--------------------------');
        console.log("Get News Stories for given dateRange filter - SUCCESSFUL");
        console.log('--------------------------');
    } catch(err) {
        console.log(err);
        console.log("Should not throw exception");
        process.exit();
    }
}

function testGetStoriesWithTitleAndAuthorFilter(newsService, title, author) {
    var filter = {
        'title' : title,
        'author': author
    }
    printTestName('9 - Fetching News Stories with title: ' + title + ' , published by author :' + author);
    var stories = newsService.getStoriesForFilter(filter);
    console.log(stories);
    var flag = 0;
    for(var i in stories) {
        assert(stories[i].author, author);
        assertContains(stories[i].title, title);
        flag = 1;
    }
    assert(flag, 1);
    console.log('--------------------------');
    console.log("Get News Stories for given author and title filter - SUCCESSFUL");
    console.log('--------------------------');
}

function testGetStoriesWithTitleAndAuthorAndDateRangeFilter(newsService, title, author, startDate, endDate) {
    var filter = {
        'title' : title,
        'author': author,
        'dateRange': {
            startDate: startDate,
            endDate: endDate
        }
    }
    printTestName('9 - Fetching News Stories with title: ' + title + ' , published by author :' + author + ' in date range: ' + startDate + " to " + endDate);
    var stories = newsService.getStoriesForFilter(filter);
    var flag = 0;
    console.log(stories);
    for(var i in stories) {
        assert(stories[i].author, author);
        assertContains(stories[i].title, title);
        if(new Date(stories[i].date).getTime() < startDate || new Date(stories[i].date).getTime() > endDate) {
            console.log("Wrong story fetched: Outside date range");
            process.exit();
        }
        flag = 1;
    }
    assert(flag, 1);
    console.log('--------------------------');
    console.log("Get News Stories for given author and title and dateRange filter - SUCCESSFUL");
    console.log('--------------------------');
}

function assert(val1, val2) {
    if(val1 != val2) {
        console.log("Val1: " + val1 + " not equals Val2: " + val2);
        process.exit();
    }
}

function assertNumber(val) {
    if(isNaN(val)) {
        console.log("Val: " + val + " should be a number - news story id");
        process.exit();
    }
}

function assertContains(str1, str2) {
    if(!str1.includes(str2)){
        console.log("Str1: " + str1 + " does not contain : " + str2);
        process.exit();
    }
}

function printTestName(name) {
    console.log("[Test: " + name + "]");
}