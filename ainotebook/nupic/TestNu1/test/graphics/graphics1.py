###########################################################
# turtle1.py
# Berlin Brown, simple TK example python
# berlin dot brown at gmail.com
###########################################################

import Tkinter as tk
from time import sleep

if __name__ == '__main__':
    print "Running Turtle Test"    
    master = tk.Tk()
    w = tk.Canvas(master, width=600, height=500, background="white")
    w.pack()
    
    # x1, y1, x2, y2
    for i in range(0, 10):
        w.create_rectangle(10 + (12 * i), 10, 14 + (12 * i), 14, fill="black")
        sleep(1)
        w.update()
        
    w.create_rectangle(10, 20, 14, 24, fill="red")
        
    tk.mainloop()    
    print "Done"
    