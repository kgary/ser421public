#!/usr/bin/perl
print ("Content-type: text/html\n\n");
while (($key, $val) = each %ENV) {
	print ("$key = $val<br>\n");
}
