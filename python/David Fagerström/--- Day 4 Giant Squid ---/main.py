import collections
from os import path
import re

def main():

    boards = numbers = list()
    path = ".\python\David Fagerström\--- Day 4 Giant Squid ---\input.txt"
    file = open(path,'r')
    column = 5
    for row in [re.split(' +|,', line.strip()) for line in file]:
        if (len(row) > column):
             numbers = [num for num in row]
        elif (len(row) == column):
            boards[len(boards) -1].extend([i for i in row])
        else: 
            boards.append(list())

    numWins = 0
    for num in numbers:
        for board in (b for b in boards):
            try:
                board[board.index(num)] = ""
                for i in range(0,column):
                    if "".join(board[i::column]) == "" or "".join(board[i*column:][:column]) == "":
                        if numWins == 0 or numWins == len(boards) - 1:
                            print ("BINGO LOTTO! " + str(int(num) * sum(int(i) if i.isnumeric() else 0 for i in board)))
                        numWins += 1
                        board.clear()
                        break
            except:
                pass
            
if __name__ == '__main__':
    main()