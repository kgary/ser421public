#!/usr/bin/perl
#- CgiQuery.pl
#- Copyright (c) 1996 by Dr. Herong Yang, http://www.herongyang.com/
# Adapted from http://www.herongyang.com/Perl/CGI-QUERY_STRING.html
   print "Content-Type: text/html\n\n";
   print "<html><body>\n";
   print "<b>Query String Data:</b><br/>\n";
   $query = $ENV{'QUERY_STRING'};
   @list = split( /\&/, $query);
   foreach (@list) {
      ($var, $val) = split(/=/);
      $val =~ s/\'//g;
      $val =~ s/\+/ /g;
      $val =~ s/%(\w\w)/sprintf("%c", hex($1))/ge;
      print($var, ' = ', $val, "<br/>\n");
   }
   print "</html></body>\n";
