// Example from Brad Dayley
// https://github.com/bwdbooks/nodejs-mongodb-angularjs-web-development
var express = require('express'),
    pug = require('pug'),
    ejs = require('ejs');
var app = express();

app.set('views', './views');
app.set('view engine', 'pug');
app.engine('pug', pug.__express);
app.engine('html', ejs.renderFile);
app.listen(8088);

app.get('/pug', function (req, res) {
  res.render('user_pug');
});

// https://stackoverflow.com/questions/5560248/programmatically-lighten-or-darken-a-hex-color-or-rgb-and-blend-colors
function shadeColor(color, percent) {

    var R = parseInt(color.substring(1,3),16);
    var G = parseInt(color.substring(3,5),16);
    var B = parseInt(color.substring(5,7),16);

    R = parseInt(R * (100 + percent) / 100);
    G = parseInt(G * (100 + percent) / 100);
    B = parseInt(B * (100 + percent) / 100);

    R = (R<255)?R:255;  
    G = (G<255)?G:255;  
    B = (B<255)?B:255;  

    var RR = ((R.toString(16).length==1)?"0"+R.toString(16):R.toString(16));
    var GG = ((G.toString(16).length==1)?"0"+G.toString(16):G.toString(16));
    var BB = ((B.toString(16).length==1)?"0"+B.toString(16):B.toString(16));

    return "#"+RR+GG+BB;
}

// https://stackoverflow.com/questions/35969656/how-can-i-generate-the-opposite-color-according-to-current-color
function invertHex(hex) {
    hex = hex.substring(1);
    return (Number(`0x1${hex}`) ^ 0xFFFFFF).toString(16).substr(1).toUpperCase();
}

// https://stackoverflow.com/questions/5092808/how-do-i-randomly-generate-html-hex-color-codes-using-javascript
function generateRandomHexColor() {
    return '#'+(Math.random() * 0xFFFFFF << 0).toString(16).padStart(6, '0');
}

app.get('/ejs', function (req, res) {
    var rgb = [];
    var altFontColor = [];
    for(var i = 0; i < 10; ++i) {
        var color = generateRandomHexColor();
        altFontColor.push(invertHex(color));
        shades = []
        for(var j = 100; j >= -50; j-=10) {
            shades.push(shadeColor(color, j));
        }
        rgb.push(shades);
    }

    // Assemble background color and inverted color as fontcolor for the ejs.
    res.render('index.html', {colors:{rgb, altFontColor},});
});
