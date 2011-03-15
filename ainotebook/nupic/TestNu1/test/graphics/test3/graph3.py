'''
Render the nupic bitworm data.
Render categories.

Tested on Python 2.5.2  
Created on Dec 14, 2010
@author: Berlin Brown
'''
import os
import copy

import Tkinter as tk
from time import sleep

def compress(a=[]):
    newarr = sorted(set(a))
    return newarr

if __name__ == '__main__':
    ''' Render bitworm data'''
    
    print "Running file load, graph"    
    master = tk.Tk()
    canvas = tk.Canvas(master, width=700, height=500, background="white")
    canvas.pack()
    
    bitworm_size = 20
    category_data = []
    try:
        trainfile = open('./training_categories.txt', 'r')
        bit_per_line = trainfile.readlines()
        bitworm = []
        reset_grp = 0
        for i, bit in enumerate(bit_per_line):
            bitworm.append(bit.strip())
            if (reset_grp == 20):
                # Append group
                chk_zero = compress(bitworm)
                if (len(chk_zero) != 1):
                    category_data.append(bitworm)
                bitworm = []
                reset_grp = 0
                
            reset_grp = reset_grp + 1        
        trainfile.close()
                
        # Render
        startx_offset = 40
        starty_offset = 40
        for j, bitworm_data in enumerate(category_data):
            for i, bitworm_bit in enumerate(bitworm_data):                
                x1 = (i * 12) + 0 + startx_offset
                y1 = (j * 12) + 0 + starty_offset
                x2 = (i * 12) + 8 + startx_offset
                y2 = (j * 12) + 8 + starty_offset                            
                if (bitworm_bit == '1'):
                    canvas.create_rectangle(x1, y1, x2, y2, fill="black")
                else:                    
                    canvas.create_rectangle(x1, y1, x2, y2, fill="grey")        
    except Exception, e:
        print "Error at open training category file", e
    
    # End wait for user to close window    
    canvas.mainloop()
    print "Done"

#############################
# End of File
#############################