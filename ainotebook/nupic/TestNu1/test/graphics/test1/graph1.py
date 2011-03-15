'''
Render the nupic bitworm data.

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
    
    trainfile = open('./training_data.txt', 'r')    
    bitworm_data = [] 
    tmp_time_group = []
    linedata = trainfile.readlines()
        
    for line in linedata:
        arr_line_data = line.strip().split()
        chk_arr_zeros = compress(arr_line_data)
        if len(chk_arr_zeros) == 1:
            # Reset time group
            # Zero group found
            tmp_time_group = []            
            print "[reading lines] : found reset line"
        else:
            # Continue with default operations            
            tmp_time_group.append(copy.deepcopy(arr_line_data))            
            if len(tmp_time_group) == 20:
                print "[INFO] at twenty, append to bitworm data"                
                bitworm_data.append(copy.deepcopy(tmp_time_group))
                print "[INFO] at twenty, bitworm size:", len(bitworm_data) 
    trainfile.close()                                            
    # End of for, through lines
    
    startx_offset = 40
    starty_offset = 40
    print len(bitworm_data)
    for grp, bitworm_group in enumerate(bitworm_data):    
        for j, single_bitworm_line in enumerate(bitworm_group): 
            for i, bitworm_bit in enumerate(single_bitworm_line):                
                x1 = (i * 12) + 0 + startx_offset
                y1 = (j * 12) + 0 + starty_offset
                x2 = (i * 12) + 8 + startx_offset
                y2 = (j * 12) + 8 + starty_offset                            
                if (bitworm_bit == '1'):                    
                    canvas.create_rectangle(x1, y1, x2, y2, fill="black")
                else:                    
                    canvas.create_rectangle(x1, y1, x2, y2, fill="grey")
                                                
        # Update canvas after group rendered
        canvas.create_text(120, 400, text=('Bitworm Group %s' % grp))
        canvas.update()        
        sleep(0.7)
        canvas.delete(tk.ALL)
        
    canvas.mainloop()
    print "Done"

#############################
# End of File
#############################