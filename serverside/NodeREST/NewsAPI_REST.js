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
    if (urlObj.pathname == "/stories") {
        // to determine what behavior to invoke, we rely on HTTP verbs to tell us
        if (req.method == "GET") {
            // query string spec: ?title=<title>&author=<author>
            let newsStories = newsService.getStoriesForFilter(qstr);
            // being optimistic here, error-checking needs to be added
            res.writeHead(200, {
                'Content-Type': 'application/json'
            });
            res.end(JSON.stringify(newsStories));
        } else if (req.method == "POST") {
            var reqData = '';
            req.on('data', function (chunk) {
                reqData += chunk;
            });
            req.on('end', function () {
                var postParams = JSON.parse(reqData);
                var isTrueSet = (postParams.flag === 'true');
                console.log(postParams);
                let storyId = newsService.addStory(postParams.title, postParams.content, postParams.author, isTrueSet, postParams.date);
                res.writeHead(201, {
                    'Content-Type': 'application/json'
                });
                res.end(JSON.stringify({
                    'storyId': storyId
                }));
            });
        } else {
            // error case, 405 method not supported
            res.writeHead(405, {
                'Content-Type': 'application/json'
            });
            res.end(JSON.stringify("method not supported"));
        }
    } else {
        // error case, 404 not found
        res.writeHead(404, {
            'Content-Type': 'application/json'
        });
        res.end(JSON.stringify("not found"));
    }
}).listen(8089);
