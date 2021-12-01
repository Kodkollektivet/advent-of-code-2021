#advent of code 2021 day1  part2  Johan g
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
i = 0
while i < len(depths) - 2:
  depthPlusOne = depths[i + 1]
  depthPlusTwo = depths[i + 2]
  slidignDepth = depths[i] + depthPlusOne + depthPlusTwo
  slidingDepths.append(slidignDepth)
  i += 1  


increases = 0
prevDepth = slidingDepths[0]

for slidingDepth in slidingDepths:
  if slidingDepth > prevDepth:
    increases += 1
  prevDepth = slidingDepth

print("Number of increases: ", increases)