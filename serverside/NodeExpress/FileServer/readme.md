# File Server using Express framework

This example is of simple file server which allows uploading image files and lists currently available file on server.
Please note that this is a very minimal implementation and has a (lot of) scope for improvements.

# NPM Packages used:
    -> express
    -> express-fileupload
    -> fs

To create an HTML form which allows uploading a file:
    1. HTML File Input:  https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file
    2. HTML <form> enctype attribute:  https://www.w3schools.com/tags/att_form_enctype.asp
    3. Since we are using an HTML File Input, we need to change enctype to multipart/form-data: https://stackoverflow.com/questions/4526273/what-does-enctype-multipart-form-data-mean


# For this example

How file uploading works:
    1. The application does a POST to the server along with the file that is selected in the file input.
    2. Respective endpoint to handle a POST has the file. It moves the file to a specific directory.
    3. If no file was selected during form submission, the endpoint returns a 400 (bad request).

How file downloading works:
    1. The endpoint to download a file is /download/:filename - where :filename is a path parameter and should be a name of file present on the server.
    2. If the file is present, the response sends a copy of the file as an attachment (using res.download method)

How files are listed:
    1. Server reads the list of files in the directory where file uploading is done (in the example: ./files/)
    2. It creates an HTML unordered list of these files, along with an anchor element with a link to download the file.
    3. The anchor element url (a href = ...) is nothing but a link/endpoint to download the file along with the filename.
        e.g. <a href="/download/watchtower.jpg">watchtower.png</a>