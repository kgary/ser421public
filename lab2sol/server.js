/*
SER421 Lab 2 Fall 2016 Solution
Copyright 2016 Kevin A. Gary
*/
const fs = require('fs');
const http = require('http');
const url = require('url');
const qstring = require('querystring');

// This template root is for our homegrown templates
const templateRoot = 'news/';
var mimeTypes=new Object;

http.createServer(function (req, res) {
    // Step 1: Process headers. In this case, find the cookie
    var usernameDict = { username: "Jane Doe", role: "Guest" };
    var cookieString = req.headers['cookie'];
    //console.log("Cookie: " + cookieString);
    if (cookieString != undefined) {
        cookieString = cookieString.replace("; ","&");
        usernameDict = qstring.parse(cookieString);
    }
    // Extract the HTTP method
    var method = req.method;
    
    // Step 2: Process params, include the URL
    var path = url.parse(req.url).pathname.toString().trim();
    //console.log(path);
    
    // Step 3:
    // We have the method and the relative path, time for the route logic
    // First are the default, login, and logout 
    if (path == '/') {
        res.writeHead(302, {Location: '/landing.html'}); // if get it is the landing page
        res.end();
    } else if (path == '/landing.html') {
        if (method != 'GET') {
            clientError(res, 405, "Only GET allowed to the landing page");
        } else {
            renderHome(usernameDict, res);
        }
    } else if (path == '/login/') {
        console.log("MATCHED login URL");
        if (method == 'GET') {
            renderGeneric(res, 'login.html');
        } else if (method == 'POST') {
            login(req, res);
        }
    } else if (path == '/logout/') {
        console.log("MATCHED logout URL");
        logout(res);
    } else if (path.indexOf('.story') !== -1) { // if a story file
        console.log("MATCHED story file");
        renderStory(usernameDict,path,res);
    } else if (path.indexOf('/media') !== -1) { // if a media file
        console.log("MATCHED media file");
        renderMedia(res, path);
    }
    else if (path=='/addStoryPage/') { // endpoint for adding
        console.log("MATCHED addStoryPage");
        if (usernameDict.role != 'Reporter') {
            clientError(res, 403, "You are not authorized to use this page");
        } else {
            renderGeneric(res, 'addStoryPage.html');
        }
    } 
    else if (path=='/editPage'){ //endpoint to load edit story page with existing values
      console.log("MATCHED editPage");
          if (usernameDict.role != 'Reporter') {
              clientError(res, 403, "You are not authorized to use this page");
          } else {
              editStory(res,req.url,'editStoryPage.html');
          }
    }
    else if (path=='/editMetadata') { // POST to metadata from create story form
        console.log("MATCHED editMetadata");
        if (method == 'POST') {
            if (usernameDict.role != 'Reporter') {
                clientError(res, 403, "You are not authorized to use this page");
            } else {
                createStory(req,res,usernameDict,true); //true means file edit
            }
        } else {
            clientError(res, 405, "GET method not allowed to endpoint /metadata");
        }
    }
    else if(path=='/metadata') { // POST to metadata from create story form
        console.log("MATCHED metadata");
        if (method == 'POST') {
            if (usernameDict.role != 'Reporter') {
                clientError(res, 403, "You are not authorized to use this page");
            } else {
                createStory(req,res,usernameDict,false);
            }
        } else {
            clientError(res, 405, "GET method not allowed to endpoint /metadata");
        }
    }
    else if(path=='/html') {  // POST to html for new story
        console.log("MATCHED html create");
        if (method == 'POST') {
            if (usernameDict.role != 'Reporter') {
                clientError(res, 403, "You are not authorized to use this page");
            } else {
                createHTML(usernameDict,req,res);
            }
        } else {
            clientError(res, 405, "GET method not allowed to endpoint /html");
        }
    }
    else if(path.indexOf('delete') > -1) {  // for deleting a story
        console.log("MATCHED delete");
        if (usernameDict.role != 'Reporter') {
            clientError(res, 403, "You are not authorized to use this page");
        } else {
            deleteFile(usernameDict,req.url, res);
        }
    } else {
        clientError(res, 404, "NOT FOUND " + path);
    }
}).listen(3000, function() {
    // init MIME types structure
    initMimeTypes();
});

function clientError(res, code, msg) {
    res.writeHead(code);
    res.end("<html><head><title>CLIENT ERROR</title></head><body>HTTP "+ code + "<br/>" + msg + "</body></html>");
}

function editStory(res, requrl, templateName){
  var renderedTemplate;
  //get file name
  var header = url.parse(requrl, true);
  var fileToBeEdited = header.query.name;  //*name*.story
  var filenameToBeEdited=fileToBeEdited.substring(0,fileToBeEdited.indexOf("."));  //name (without .story)
  //console.log("filenameToBeEdited::"+filenameToBeEdited);

  //read existing data
  fs.readFile(fileToBeEdited, function(err, contents) {
    var fileContent = JSON.parse(contents);
    //get radio button value
    var isPublic=fileContent.Public;
    //read edit file page
    fs.readFile(templateRoot+templateName,function(err,data){
        renderedTemplate = data.toString();

        //load existing data
        renderedTemplate = renderedTemplate.replace("{{title}}",fileContent.Title);
        renderedTemplate = renderedTemplate.replace("{{author}}",fileContent.Author);
        if(isPublic) {
            renderedTemplate = renderedTemplate.replace("{{yesCheck}}","checked");
            renderedTemplate = renderedTemplate.replace("{{noCheck}}","");
        } else {
            renderedTemplate = renderedTemplate.replace("{{yesCheck}}","");
            renderedTemplate = renderedTemplate.replace("{{noCheck}}","checked");
        }
        renderedTemplate = renderedTemplate.replace("{{fragments}}",fileContent.Fragments);
        renderedTemplate = renderedTemplate.replace("{{filename}}",filenameToBeEdited);
        res.end(renderedTemplate);
    });
  });
}

function deleteFile(userDict,requrl,res){
  //get file name to delete
  var header = url.parse(requrl, true);
  var fileToBeDeleted = header.query.name;
  //find its related html -_- also "are you sure u want to delete?"
  fs.unlink(fileToBeDeleted, function(err){
            if (err) throw err;
            renderHome(userDict, res);
       });
}

function initMimeTypes() {
    var strs = [];
    
    // from Node documentation on readline
    // https://nodejs.org/api/readline.html
    const readline = require('readline');

    const rl = readline.createInterface({
        input: fs.createReadStream('mime.types')
    });

    rl.on('line', (line) => {
        line = line.trim();
        if (!line.startsWith("#")) {
            // not commented out. Split on whitespace
            // thanks http://stackoverflow.com/questions/14912502
            strs = line.match(/\S+/g) || [];
            // if it is 1 no extensions are present
            // if > 1 then 1 or more extensions present
            // add a mime type entry for each one 
            for (i = 1; i < strs.length; i++) {
                mimeTypes[strs[i]] = strs[0];
            }
        }
    });
}

function createStory(req,res,userDict,edit) {
  var jsonData = "";
  req.on('data', function (chunk) {
    jsonData += chunk.toString();
  });
  req.on('end', function () {
    var postData = qstring.parse(jsonData);
    var filename=postData['filename']+".story";
    console.log(filename);
    var aa=postData['Fragments'].split(",")
    var array=[];
    for( b in aa){
      array.push(aa[b].trim())
    }
    var fileContent ={Title: postData['Title'],
                      Author: postData['Author'],
                      Public: postData['Public'],
                      Fragments: array
                    };
    var fileContent = JSON.stringify(fileContent);
    //write filecontent to the .story file
    fs.writeFile(filename, fileContent, function(err) {
         console.error(err)
   })
  });
  if(edit){
    renderHome(userDict, res);
   } else {
    renderGeneric(res, 'addStoryPage.html');
  }
}

function createHTML(userDict,req,res) {
  var jsonData = "";
  req.on('data', function (chunk) {
    jsonData += chunk.toString();
  });
  req.on('end', function () {
    var postData = qstring.parse(jsonData);
    var filename= templateRoot+postData['filename']+".html";
    var fileContent =postData['text'];
    fs.writeFile(filename, fileContent, function(err) {
         console.error(err)
   })
  });

  renderHome(userDict, res);
}

function buildHtmlListEntries(htmlString, files, index, userDict, templateToString, res) {
    //console.log("INDEX is " + index + "\t HTML buffer is \n" + htmlString);
    if (index >= files.length) {    
        var renderedTemplate = templateToString.replace("{{includeHTML}}", htmlString);
        renderedTemplate = renderedTemplateWithUsername(renderedTemplate, userDict.username, userDict.role);
        res.end(renderedTemplate);
    } else {
        //console.log("Processing " + files[index]);
        if(files[index].indexOf(".story") > -1 ) {
          fs.readFile(files[index], function(err, fc) {
            var fc1 = JSON.parse(fc.toString());
            // Business logic: if the role is Guest then see all public
            // if role is Subscriber sees all
            // if role is Reporter see all public and all nonpublic you authored
            if (userDict.role === "Reporter" && userDict.username === fc1.Author) {
                htmlString += "<a href='/"+files[index]+"'>"+fc1.Title+"</a> "
                htmlString += "&nbsp;&nbsp;<a href='/editPage?name="+files[index]+"'>Edit</a>"
                htmlString += "&nbsp;&nbsp;<a href='/delete?name="+files[index]+"'>Delete</a><br>";
            } else if (fc1.Public == ("yes") || fc1.Public == ("Yes")) {
                htmlString += "<a href='/"+files[index]+"'>"+fc1.Title+"</a><br>";
            } else if (userDict.role === "Subscriber") {
                // We are a subscriber so we see all
                htmlString += "<a href='/"+files[index]+"'>"+fc1.Title+"</a><br>";
            }
            return buildHtmlListEntries(htmlString, files, index+1, userDict, templateToString, res);
          });
        } else {
            return buildHtmlListEntries(htmlString, files, index+1, userDict, templateToString, res);
        }
    }
}

/*
  This is really nasty in that you want to read the files first and then go into each one individually
*/
function getStories(userDict, templateToString, res) {
    var htmlSegment = "<h3>Stories</h3>\n";
    fs.readdir(__dirname, function(err, files) {
        buildHtmlListEntries(htmlSegment, files, 0, userDict, templateToString, res);
    });
}

function renderHome(userDict, res) {
  fs.readFile(templateRoot+'index.html',function(err,data){
    res.setHeader("Content-Type", "text/html");
    res.writeHead(200);
    var templateToString = data.toString();
    if(userDict.role==="Reporter"){
      templateToString += "<a href='/addStoryPage/'>Create New Story</a><br>";
    }
    getStories(userDict, templateToString, res);
  });
}

function renderStory(userDict, path, res) {
  var renderedTemplate = '';
  fs.readFile(templateRoot + 'header.html', function(err, data) {
      renderedTemplate += data.toString();
      var templateToString = data.toString();
      var renderedTemplate = templateToString;
      var uname=userDict.username;
      var role=userDict.role;

      var renderedTemplate = renderedTemplateWithUsername(templateToString, uname,role);
      getStoryFile(path, res, renderedTemplate);
  });
}

function getStoryFile(path, res, renderedTemplate) {
  path = path.split('/');
  path = path[path.length - 1];
  fs.readFile(path,function(err,data){
      var storyTemplate = data.toString();
      var storyTemplateDict = JSON.parse(storyTemplate.toString());
      getStory(path, res, renderedTemplate, storyTemplateDict['Fragments'],0);
    });
}

function getStory(path, res, renderedTemplate, fragments, count) {
  if (count >= fragments.length) {
      renderFooter(res, renderedTemplate);
  } else {
    fs.readFile(templateRoot + fragments[count], function(err, data) {
       renderedTemplate += data.toString();
       getStory(path, res, renderedTemplate, fragments, count+1);
    });
  }
}

function renderFooter(res, renderedTemplate) {
  fs.readFile(templateRoot + 'footer.html', function(err, data) {
      renderedTemplate += data.toString();
      res.end(renderedTemplate);
  });
}

function renderGeneric(res, templateName) {
  fs.readFile(templateRoot+templateName,function(err,data){
    res.setHeader("Content-Type", "text/html");
    res.writeHead(200);
    var renderedTemplate = data.toString();
    res.end(renderedTemplate);
  });
}

function login(req,res) {
  var bodyData = "";
  req.on('data', function (chunk) {
    bodyData += chunk.toString();
  });
  req.on('end', function () {
    var postData = qstring.parse(bodyData);
    var role=postData['role'];
    var username = postData['username'];
    if (username == postData['password']) {
        res.writeHead(302,{
            'Location': '/landing.html',
            'Set-Cookie': ["username="+username+"; path=/;", "role="+role+"; path=/;"]
        });
        res.end();
    } else {
        res.writeHead(302,{'Location': '/login/'});
        res.end();
    }
  });
}

function logout(res) {
   res.writeHead(302,{
    'Location': '/landing.html',
    'Set-Cookie': ["username=; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT;", 
                   "role=; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT"]
   });
   res.end();
}

function renderedTemplateWithUsername(renderedTemplate, username, role) {
  renderedTemplate = renderedTemplate.replace("{{welcome}}","Welcome "+username);
  renderedTemplate = renderedTemplate.replace("{{role}}",role);
  if (role==='Guest') {
    renderedTemplate = renderedTemplate.replace("{{loginURL}}","/login/");
    renderedTemplate = renderedTemplate.replace("{{login}}","login");
  } else {
    renderedTemplate = renderedTemplate.replace("{{loginURL}}","/logout/");
    renderedTemplate = renderedTemplate.replace("{{login}}","logout");
  }
  return renderedTemplate;
}

function renderMedia(res, mediaPath) {
  mediaPath = mediaPath.slice(1);
  mimeType = 'application/octet-stream';  // default if we do not find one
  fs.readFile(mediaPath, function(err, data) {
    if (err) {
        console.log(err);
        clientError(res, 400, "Error processing media file");
    } else {
        ext = mediaPath.split('.');
        if (ext.length > 1) { // we found a file extension
            if (mimeTypes[ext[1]] != undefined) mimeType = mimeTypes[ext[1]];
        }
        console.log('Writing media file ' + mediaPath + ' with MIME type ' + mimeType);
        res.writeHead(200, { 'Content-Type': mimeType });
        res.end(data);
    }
  });
}