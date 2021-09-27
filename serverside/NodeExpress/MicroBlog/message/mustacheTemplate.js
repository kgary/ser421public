function mustache(text, data) {
  var result = text;

  for (var prop in data) {
    if (data.hasOwnProperty(prop)) {
      var regExp = new RegExp('{{' + prop + '}}', 'g');

      result = result.replace(regExp, data[prop]);
    }
  }

  return result;
}

module.exports = mustache;
