def main():
    path = ".\--- Day 1 Sonar Sweep ---\input.txt"
    file = open(path, "r")
    lst = []
    threemeasurement = increasecount = previousnumber = 0
    for line in file:
        strippedline = line.strip()
    
        if int(strippedline) > int(previousnumber):
            increasecount +=1
        lst.append(int(strippedline))

        if len(lst) > 3:
            sumnewlst = sum(lst[len(lst)-3:len(lst)])
            sumoldlst = sum(lst[len(lst)-4:len(lst)-1])

            if sumnewlst == sumoldlst:
                pass

            elif sumnewlst > sumoldlst:
                print(lst[len(lst)-3:len(lst)], lst[len(lst)-4:len(lst)-1])
                print(sumnewlst, "is larger then", sumoldlst)
                threemeasurement += 1

        previousnumber = strippedline

    print("The numbers incresed", increasecount -1,"times")
    print("Threemeasurement incresed", threemeasurement, "times")


main()