#!/usr/bin/perl
my $rand = int(rand(100));
print "Set-Cookie: age=" . $rand . "\n";
print "Content-type: text/html\n\n";
while (($key, $val) = each %ENV) {
	print "$key = $val<br>\n";
}
