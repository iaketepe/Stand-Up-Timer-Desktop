# Notes

## Thread Handling

For the most part, my project structure was good. However, since I was dealing with threads, I needed to actually check how the threads were being handled, checking resourcse like the number of threads and the memory being used. To do this, I used task manager and an application called Java VisualVM.

By using these two, I saw that my thread count was higher than I thought. it seemed like when my application ran it started at around 14-16 live threads being used with around 11 daemon threads. However, when I pressed the start button, it seemed like there was a jump in threads by 2-3. However, after accounting for my threads, there was still a thread unaccounted for. This is when I realized that my SoundPlayer class actually uses threads to play sound effects. So the reason I was seeing a higher 'jump' was because of it. 

The next issue was that thread count had increased after the timer stopped they did not drop. This is when I realized that there was a gap in how I was handling my threads. To combat this I updated my startTimer and stopTimer methods to be 'synchronized'. While the thread handling improved, it was still not at a right 'level'. Thats when I realized that my SoundPlayer's threads didn't end on their own, so I needed to add an event listener to close those threads as well. My handling becamse much better.

<img width="309" height="170" alt="image" src="https://github.com/user-attachments/assets/b5b7f58c-fcd2-40a5-bd07-887797aa0730" />

One last thing, I noticed that the amount of heap space by my timer program started at 40 MB and then jumped to ~75 MB, repeating over again. However, after doing some research, it seems like this is just the JVM's natural 'flow'. It will keep 'garbage' for a certain amount of time before 'cleaning' it. Therefore, it's behaviour is no cause for concern on my project


<img width="300" height="156" alt="image" src="https://github.com/user-attachments/assets/e38ef978-8cee-490c-9af8-cb07a5d1c3ce" />
