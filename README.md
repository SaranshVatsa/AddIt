# AddIt
Android app to detect songs through microphone &amp; add it to the user's Spotify playlist.


# Current status:

**The Spotify end of the work is done.** The User can provide authentication to the app to read and write to playlists.
The user selects the playlist, and for now a hardcoded song name string (Eminem- Lose Yourself) is looked up on Spotify and gets added to the playlist selected by the user.

It is at this point where the app needs to go into listening mode (as a background service), the audio detected through the microphone should be sent to an API end point which will return the title of the track and the artist name, which will then be looked up on Spotify in place of that hard coded string we have for now.

Fairly straightforward work left now, but the problem is: **Shazam and SoundHound do not have any public APIs, and no other API available online seems to be working at this point.**

If anyone is willing to go to that extent, a song classifier model can be built from scratch for a dataset of at least over a million songs (preferably 10 million +), the neural network trained and hosted at any virtual instance online. Then API end points can be created and the app can be linked to that.

Feel free to contribute to this. UI can be looked into if and when the functionality is complete.
