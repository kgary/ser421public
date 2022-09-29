define({ "api": [
  {
    "type": "get",
    "url": "/authors",
    "title": "Get list of Authors",
    "name": "getAuthors",
    "group": "Authors",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "\tHTTP/1.1 200 OK\n\t[\n  {\"authorId\":1111,\"firstName\":\"Ariel\",\"lastName\":\"Denham\"},\n  {\"authorId\":1212,\"firstName\":\"John\",\"lastName\":\"Worsley\"}\n ]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/edu/asupoly/ser422/restexample/api/AuthorResource.java",
    "groupTitle": "Authors",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "400",
            "optional": false,
            "field": "BadRequest",
            "description": "<p>Bad Request Encountered</p>"
          }
        ],
        "Error 5xx": [
          {
            "group": "Error 5xx",
            "type": "500",
            "optional": false,
            "field": "InternalServerError",
            "description": "<p>Something went wrong at server, Please contact the administrator!</p>"
          }
        ]
      }
    }
  }
] });
