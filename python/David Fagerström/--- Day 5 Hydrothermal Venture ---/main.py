import re
import numpy as np
from numpy.core.fromnumeric import diagonal

def main():
    path = ".\python\David Fagerström\--- Day 5 Hydrothermal Venture ---\input.txt"
    file = open(path,'r')
    radar = np.zeros([1000,1000])
    for line in file:
        cords = []
        line = line.split()
        x1, y1 = line[0].split(',')
        x2, y2 = line[2].split(',')
        x1,x2,y1,y2 = int(x1),int(x2),int(y1),int(y2)        
        
        if x1 == x2:
            if y1 < y2:  
                yrange = range(y1, y2+1)
            else:
                yrange = range(y2, y1+1)
            
            for y in yrange:
                radar[y][x1] += 1

        elif y1 == y2:
            if x1 < x2:  
                xrange = range(x1, x2+1)
            else:
                xrange = range(x2, x1+1)
            
            for x in xrange:
                radar[y1][x] += 1
        else:
            if x1 < x2 and y1 < y2:
                xrange = range(x1, x2+1)
                yrange = range(y1, y2+1)

            elif x1 < x2 and y1 > y2:
                xrange = range(x1, x2+1)
                yrange = range(y1, y2-1, -1)
            
            elif x1 > x2 and y1 < y2:
                xrange = range(x1, x2-1, -1)
                yrange = range(y1, y2+1)

            elif x1 > x2 and y1 > y2: 
                xrange = list(range(x2, x1+1))
                yrange = range(y2, y1+1)
            
            else:
                print("😡")
        
            for diagonal, y in enumerate(yrange):
                radar[xrange[diagonal]][y] += 1
                print([y])
                print([xrange[diagonal]])
                print(xrange)

    result = np.count_nonzero(radar > 1)
    print(result)
if __name__ == '__main__':
    main()