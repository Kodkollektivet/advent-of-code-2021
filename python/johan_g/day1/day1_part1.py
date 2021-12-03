#advent of code 2021 day1  par1  Johan g
import os
path = "C:\\Users\jocke\OneDrive\Dokument\Knacka_kod\Phyton\Datateknik\\advent_of_code\\advent-of-code-2021\python\johan_g\day1\depths.txt"

try:
    depths = []
    with open(path, "r") as file:
        for line in file:
            depths.append(int(line))
except IOError as io:
    print(f"Error: {io}")

increases = 0
prevDepth = depths[0]

for depth in depths:
  if depth > prevDepth:
    increases += 1
  prevDepth = depth

print("Number of increases: ", increases)