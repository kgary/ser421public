#!/usr/bin/php
<?php
print "Content-type: text/html\n\n";
$first = filter_input(INPUT_GET, "first", FILTER_SANITIZE_STRING);
$second = filter_input(INPUT_GET, "second", FILTER_SANITIZE_STRING);
$is_ok = true;

// note: this script uses the function is_numeric is ideally shopuld tun under PHP 4 or greater
//       however it has a 'fix' so that it works to some extent with PHP 3 also
//function is_number($x)
//{
//return is_numeric($x);
//}

//if ( is_number($first) && is_number($second) ) {
//     $is_ok = true;
//     $answer = $first + $second;
//  } else {
//     $is_ok = false;
//  }
?>
<?php
  if ( $is_ok ) :
?>
<html>
<head>
<title>I can add up!</title>
</head>
<body bgcolor="#ffffff">
<h2>I can add up!</h2>
<table>
<tr><td></td><td align=right><?php echo $first; ?></td></tr>
<tr><td>+</td><td align=right><?php echo $second; ?></td></tr>
<tr><td></td><td align=right>------</td></tr>
<tr><td></td><td align=right><?php echo $answer; ?></td></tr>
</table>
</body>
</html>
<?php
   else :  // the following is for when one or other of $first or $second is not a number
?>
<html>
<head>
<title>bad numbers to add up</title>
</head>
<body bgcolor="#ffffff">
<h2>bad numbers to add up</h2>
<p>Sorry, I can't add up &quot;<?php echo $first; ?>&quot; and &quot;<?php echo $second; ?>&quot; as I can only add up numbers
</p> 
</body>
</html>
<?php
  endif;
?>
