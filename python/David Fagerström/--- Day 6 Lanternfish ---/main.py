from typing import Mapping

def main(days):
    path = ".\python\David Fagerström\--- Day 6 Lanternfish ---\input.txt"
    file = open(path,'r').read().split(',')
    lst = []
    fishlst = []
    for num in file:
        lst.append(int(num))
    for fish in range(9):
        fishlst.append(lst.count(fish))
        
    for i in range(days):
        nxtfish = fishlst.pop(0)
        fishlst[6] += nxtfish
        fishlst.append(nxtfish)
    return sum(fishlst)

if __name__ == '__main__':
    print(main(80))
    print(main(256))