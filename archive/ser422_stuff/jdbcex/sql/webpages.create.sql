
Create table WebPage(URL Varchar(100) not null, 
Hypertext VARCHAR(4500) not null, primary key(URL));

Create table HyperLink(FromURL VARCHAR(100) not null,
ToURL VARCHAR(100) not null, Label VARCHAR(30) not null,
primary key(FromURL, ToURL), foreign key(FromURL)
references WebPage(URL), foreign key(ToURL) references
WebPage (URL)); 

Create table ImageInfo(URL VARCHAR(100) not null, ImageData VARCHAR(3000) 
not null, primary key(URL));

Create table ImageLink(FromURL VARCHAR(100) not null, 
ToURL VARCHAR(100) not null, AltText VARCHAR(30), 
primary key(FromURL, ToURL), 
foreign key(FromURL) references WebPage(URL), 
foreign key(ToURL) references ImageInfo(URL));

 