#!/usr/bin/python

import cgi
import cgitb; cgitb.enable()  # for troubleshooting
import os.path
import sys
import time
import re
import urlparse

def main():
    print "Content-type: text/html"
    print
    print "<html><head><body><div>"

    form = cgi.FieldStorage()
    fname = ""
    lname = ""
    phone = ""
    action = ""
    listStr = ""

    directory = "numbers"

    if not os.path.exists(directory):
        os.makedirs(directory)

    #List is not formatted normally so we cant use get value
    for value in form.list :
        if (value.name == "fname") :
           fname = value.value
        if (value.name == "lname") :
           lname = value.value
        if (value.name == "phone") :
           phone = value.value
        if (value.name == "action") :
           action = value.value
           if (value.value[:4] == "list") :
              action = value.value[:4] #Chop of the first 4 letters for later
              listStr = (value.value[5:])[:-1] #Turns "list[args]" into "args"

    if action == "add" :
       os.chdir(directory)
       #print "Add requested:"
       if (len(fname) != 0 and len(lname) != 0 and len(phone) == 10 and len(re.findall(r"[0-9]{10}", phone)) == 1) :
          #print "<br/>All inputs ok: " + fname + ", " + lname + ", " + phone
          filepath = phone + ".txt"

          if not os.path.exists(filepath) :
              #print "<br/>Entry added to file: " + filepath
              try :
                  f = open(filepath, "w+")
                  f.write(fname + "\n" + lname + "\n" + phone)
                  f.close()
              except Exception, e:
                  print "<br/>Entry in use, please try again."

              print "<br/>Added User Successfully"
          else :
              print "<br/>Duplicate Entry"
       else :
            print "<br/>Invalid Input"

    elif action == "remove" :
       os.chdir(directory)
       #print "Remove requested"
       if (len(phone) != 0) :
          #print "<br/>All inputs ok: " + phone
          filepath = phone + ".txt"

          if os.path.exists(filepath) :
              try :
                  os.remove(filepath)
                  print "<br/>Removed entry for: " + phone
              except Exception, e:
                  print "<br/>Entry in use, please try again."
          else :
              print "<br/>Entry not found."
       else :
            print "<br/>Invalid Input"
    elif action == "list" :
        try :
          listArgs = urlparse.parse_qs(listStr)
          print "<ul>"

          for infile in os.listdir(directory):
              match = True
              f = open(directory + "/" + infile, "r")
              fname = f.readline().strip()
              lname = f.readline().strip()
              phone = f.readline().strip()

              if 'fname' in listArgs :
                 #The parse qs function returns values as arrays
                 #Take the first element of the array
                 match &= listArgs['fname'][0] == fname
              if 'lname' in listArgs :
                 match &= listArgs['lname'][0] == lname
              if 'phone' in listArgs :
                 match &= listArgs['phone'][0] == phone

              if match :
                 print "<li>" + fname + ", " + lname + ", " + phone + "</li>"
              f.close()

          print "</ul>"
        except ValueError:
          print "<br/>Problem reading list parameters."

    else :
       print "<br/>Invalid action requested"

    print "</div></body></html>"

    pass

if __name__ == '__main__':
    main()
