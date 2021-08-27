
## Installing on MacOS host ##

The steps assume that nodejs installation is absent on the local machine. 
If it is present locally, the steps could cause conflicts when using node/npm.
    - A possible workaround could be to rename usb node binaries/executables: 'node' to 'node-usb' and 'npm' to 'npm-usb' and use the node-usb/npm-usb (Not recommended)

As a general note, please rename your USB drive so that it does not include any spaces.
Also, please refer the page https://nodejs.org/en/download/ in case the download url is dead.

Please note: The Path setting step can be excluded if path was already set to point to node/npm on usb before. 

**Steps:**
1. cd /path/to/usb
2. curl https://nodejs.org/dist/v14.17.5/node-v14.17.5-darwin-x64.tar.gz --output nodejs.tar.gz
3. tar -xvzf node-v14.17.5-darwin-x64.tar.gz
4. export PATH=$PATH:/path/to/usb/node-v14.17.5-darwin-x64/bin
5. Restart terminal.
6. node -v
7. npm -v

## Installing on Windows host ##

**Steps:**
1. Open the USB drive.
2. Open a command prompt in the drive.
3. Download https://nodejs.org/dist/v14.17.5/node-v14.17.5-win-x64.zip and unzip the zipfile.
4. set PATH=%PATH%;<UsbDriveLetter>:\node-v14.17.5-win-x64
5. Restart command prompt.
6. node -v
7. npm -v