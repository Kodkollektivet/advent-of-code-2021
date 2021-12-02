#This gives the solution for part 2. Uncomment row 12 and 15 for solution to part 1

def main():
    path = ".\python\David Fagerström\--- Day 2 Dive! ---/input.txt"
    file = open(path, "r")
    xPos = yPos = aim = 0
    for line in file:
        line = line.split()
        command = line[0]
        steps = int(line[1])
        if command == "up":
            #yPos -= steps 
            aim -= steps 
        elif command == "down":
            #yPos += steps
            aim += steps
        elif command == "forward":
            xPos += steps
            yPos += aim * steps

    print(xPos,yPos)
    print(xPos * yPos)
    
if __name__ == '__main__':
    main()