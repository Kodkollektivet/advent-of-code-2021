def main():
    path = "./python/David Fagerström/--- Day 3 Binary Diagnostic ---/input.txt"
    file = open(path, "r")
    linecount = 0
    lst = [0,0,0,0,0,0,0,0,0,0,0,0]
    finalnum = ''
    asciistring = ""
    for line in file:
        line = line.strip()
        print (line)
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
    print(*lst) 
    
    for i in lst:
        finalnum += str(i)
    print(finalnum)
    inverted = ""
    for i in finalnum:
        print (i)
        if i == '1':
            inverted += "0"
        if i == '0':
            inverted += "1" 
    finalnum = binaryToDecimal(int(finalnum))
    inverted = binaryToDecimal(int(inverted))
    print(finalnum, inverted)
    print(finalnum * inverted)

    

def binaryToDecimal(binary):
     
    binary1 = binary
    decimal, i, n = 0, 0, 0
    while(binary != 0):
        dec = binary % 10
        decimal = decimal + dec * pow(2, i)
        binary = binary//10
        i += 1
    return decimal
    

main()