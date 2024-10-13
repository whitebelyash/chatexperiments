# ChatExperiments
Some funny things in minecraft chat. For spigot.  \

## What does it do?
-  shows all png stored in data folder and named in the sequence 0.png, 1.png... n.png as an 24x18 art in a chat.
-  runs various command on host with /runcmd and redirects stdout to minecraft chat. stdin passthrough isn't supported. Kinda a backdoor, don't use outside localhost.
-  
## how to play videos
1. convert the video to a png sequence (e.g. with ffmpeg: ffmpeg -i $videofile -r 10 %d.png)
2. place all pngs in the plugin datafolder (plugins/ChatExperiments/)
3. run /testplayer command

To stop player run /stopplayer command.
