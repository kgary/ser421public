This directory contains the starter code and partial solution of creating a REST API for a domain implementation of a News service. 
This problem was given in the Fall 2020 SER421 class as Lab 5. It built off an earlier lab, Lab 2, so both lab specifications are shared here for context. 
Your Canvas shell also has links to walkthrough videos I recorded taking you through this code.

In a nutshell:
1. Review the code in the newsModel directory first. It implements model code for a NewsService, without respect to HTTP. That is, it is not in HTTP-based API form yet, it is simply based on the News domain and News stories with a simple file-based JSON persistence store.
2. Review the non-REST API code. This is a brute force approach to creating a web API, one that focuses on the "Verbs", meaning "add" "update", "getStories" as a simple wrapper around the newsModel/NewsService.js
3. Review the REST API code, which is a partial solution. Notice how it flips the approach around to start with the noun "NewsStory" and from there uses RESTful principles to implement endpoints with appropriate HTTP verbs and response codes. 

There is also a [YouTube video (older)](https://youtu.be/Y8RhNTdlMOU) where I walk through starting making the API RESTful (this was started with a non-REST ad hoc API).
