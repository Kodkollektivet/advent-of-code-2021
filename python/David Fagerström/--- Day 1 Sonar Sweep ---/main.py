def main():
    path = ".\python\David Fagerström\--- Day 1 Sonar Sweep ---\input.txt"
    file = open(path, "r")
    lst = []
    threemeasurement = increasecount = 0
    for line in file:

        if len(lst) and int(line) > int(lst[len(lst)-1]):
            increasecount +=1
        lst.append(int(line))
        
        if len(lst) > 3 and sum(lst[len(lst)-3:len(lst)]) > sum(lst[len(lst)-4:len(lst)-1]):
                threemeasurement += 1

    print("The numbers incresed", increasecount,"times")
    print("Threemeasurement incresed", threemeasurement, "times")


if __name__ == '__main__':
    main()