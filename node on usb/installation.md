
## Installation Guide ##

**General Guidelines/Notes:**

1. Please make sure that absolute path to node setup on usb does not include any spaces.

2. Please refer the page https://nodejs.org/en/download/ in case the download url the following steps use, is dead.

3. The path setting step can be excluded if path was already set to point to node/npm on usb before. 

4. The steps should ideally work for any node version. 

    This installation refers to node-v14.17.5-darwin-x64 (MacOS) or node-v14.17.5-win-x64 (Windows 10)

6. The steps assume that nodejs installation is absent on the local machine.

   If it is present locally, the steps could cause conflicts when using node/npm.

    - A possible workaround could be to rename usb node binaries/executables: 'node' to 'node-usb' and 'npm' to 'npm-usb' and use the node-usb/npm-usb (Not recommended)

## Installing on MacOS host ##

**Steps:**
1. cd /path/to/usb
2. curl https://nodejs.org/dist/v14.17.5/node-v14.17.5-darwin-x64.tar.gz --output nodejs.tar.gz
3. tar -xvzf nodejs.tar.gz
4. export PATH=/path/to/usb/node-v14.17.5-darwin-x64/bin:$PATH
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
