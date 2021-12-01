#advent of code 2021 day1  par2  Johan g
import os
path = "C:\\Users\jocke\OneDrive\Dokument\Knacka_kod\Phyton\Datateknik\\advent_of_code\\advent-of-code-2021\python\johan_g\day1\depths.txt"

try:
    depths = []
    with open(path, "r") as file:
        for line in file:
            depths.append(int(line))
except IOError as io:
    print(f"Error: {io}")


slidingDepths = []
for depth in depths:
  print(depths.index(depth))
  """  if depths.index(depth) <= (len(depths) - 3):
    depthPlusOne = depths[depths.index(depth) + 1]
    depthPlusTwo = depths[depths.index(depth) + 2]
    slidignDepth = depth + depthPlusOne + depthPlusTwo
    slidingDepths.append(slidignDepth) """


""" increases = 0
prevDepth = slidingDepths[0]

for slidingDepth in slidingDepths:
  if slidingDepth > prevDepth:
    increases += 1
  prevDepth = slidingDepth

print("Number of increases: ", increases) """