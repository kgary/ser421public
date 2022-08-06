var http = require('http');
var url = require('url');
var NewsService = require('./newsModel/NewsService');
var newsService = new NewsService();

http.createServer(function (req, res) {
    let resBody = '';
    let resMsg = '';
    // This way of parsing a query string is deprecated but I still find it much easier
    // than trying to use the new WhatWG URL object and trying to parse the searchParams
    let urlObj = url.parse(req.url, true, false);
    let qstr = urlObj.query;

    // Let's check the URL first
    if (urlObj.pathname == "/getStories") {
        // for simplicity we will assume this wraps "getStoriesForFilter"
        // as this is the only read-only behavior on NewsService
        // Have to construct the filter based on the req params (qstr)
        // To get the format I looked in NewsService_test.js:
        /* 
           var filter = {
             'title' : title,
             'author': author,
             'dateRange': {
                 startDate: startDate,
                 endDate: endDate
             }
           }
	    */
        // query string spec: ?title=<title>&author=<author>
        let newsStories = newsService.getStoriesForFilter(qstr);
        // being optimistic here, error-checking needs to be added
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.end(JSON.stringify(newsStories));
    } else if (urlObj.pathname == "/addStory") {
        var isTrueSet = (qstr.flag === 'true');
        let storyId = newsService.addStory(qstr.title, qstr.content, qstr.author, isTrueSet, qstr.date);
        res.writeHead(200, {
            'Content-Type': 'application/json'
        });
        res.end(JSON.stringify({
            'storyId': storyId
        }));
    } else {
        // error case, 404 not found
        res.writeHead(404, {
            'Content-Type': 'application/json'
        });
        res.end(JSON.stringify("not found"));
    }
}).listen(8088);
