#!/usr/bin/perl
my $rand = int(rand(100));
print "Set-Cookie: age=" . $rand . "\n";
print "Content-type: text/html\n\n";
system("java -cp ../classes edu.asupoly.ser422.PhoneCGI");
