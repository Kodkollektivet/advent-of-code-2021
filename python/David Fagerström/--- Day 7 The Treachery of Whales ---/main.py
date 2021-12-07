import statistics

path = ".\python\David Fagerström\--- Day 7 The Treachery of Whales ---\input.txt"
lst = open(path,'r').read().strip().split(',')
pos = 0

for i in range(0,len(lst)):
    lst[i] = int(lst[i])

def part1(lst):
    fuel = 0
    pos = statistics.median_low(lst)
    for i in lst:
        fuel += abs(pos - i)
    print(fuel)

def part2(lst,pos):
    fueltot = 0
    for i in lst:
        fueltot += sum(fuelcalc(abs(pos - i)))
    result = fueltot,pos
    return(result)

def fuelcalc(range_):
    lst = []
    counter = 0
    for i in range(range_):
        counter += 1
        lst.append(counter)
    return lst

bestresult = part2(lst,round(statistics.mean(lst)))
for i in range(1000):
    newpart2 = part2(lst,pos)
    if newpart2[0] < bestresult[0]:
        bestresult = newpart2
        print(newpart2)
    pos += 1