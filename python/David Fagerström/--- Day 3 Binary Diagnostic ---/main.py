def main():
    path = "./python/David Fagerström/--- Day 3 Binary Diagnostic ---/input.txt"
    file = open(path, "r")
    linecount = 0
    lst = [0,0,0,0,0,0,0,0,0,0,0,0]
    finalnum = ''
    asciistring = ""

    for line in file:
        line = line.strip()
        linecount += 1
        count = 0
        for number in line:
            lst[count] += int(number)
            count += 1
    for i in range(0, len(lst)):
        if lst[i] > linecount/2:
            lst[i] = 1
        else:
            lst[i] = 0
    
    for i in lst:
        finalnum += str(i)
    inverted = ""
    for i in finalnum:
        if i == '1':
            inverted += "0"
        if i == '0':
            inverted += "1" 
    finalnum = binaryToDecimal(int(finalnum))
    inverted = binaryToDecimal(int(inverted))
    print(finalnum * inverted, "Solution part 1")


def lifesupport():
    path = "./python/David Fagerström/--- Day 3 Binary Diagnostic ---/input.txt"
    file = open(path, "r")
    lst = []
    invertlst = []
    oldlst = []
    Oxlst = []
    Co2lst = []
    zerolst = []
    onelst = []
    for line in file:
        line = line.strip()
        lst.append(line)
        oldlst = lst
        
    for i in range(0,12):
        for j in range(0,len(lst)):
            if (lst[j])[i] == '0':
                zerolst.append(lst[j])
            elif ((lst[j])[i]) == '1':
                onelst.append(lst[j])

        if len(lst) == 1:
            Oxlst = lst

        if len(onelst) >= len(zerolst):
            lst = zerolst
            
        else:
            lst = onelst

        zerolst = []
        onelst = []

    lst = oldlst

    for i in range(0,12):
        for j in range(0,len(lst)):
            if (lst[j])[i] == '0':
                zerolst.append(lst[j])
            elif ((lst[j])[i]) == '1':
                onelst.append(lst[j])
        
        if len(lst) == 1:
            Co2lst = lst

        if len(onelst) >= len(zerolst):
            lst = onelst
            
        else:
            lst = zerolst

        zerolst = []
        onelst = []

    Co2 = Co2lst[0]
    ox = Oxlst[0]
    ox = binaryToDecimal(int(ox))
    Co2 = binaryToDecimal(int(Co2))  
    print(ox * Co2, "Solution part 2")
            
def binaryToDecimal(binary):
     
    decimal, i, n = 0, 0, 0
    while(binary != 0):
        dec = binary % 10
        decimal = decimal + dec * pow(2, i)
        binary = binary//10
        i += 1
    return decimal

if __name__ == '__main__':
    main()
    lifesupport()
