#advent of code 2021 day3  part1  Johan g
import os
import enum
path = "C:\\Users\jocke\OneDrive\Dokument\Knacka_kod\Phyton\Datateknik\\advent_of_code\\advent-of-code-2021\python\johan_g\day3\data.txt"

try:
    dataLst = []
    with open(path, "r") as file:
        for line in file:
            dataLst.append(line.strip())
except IOError as io:
    print(f"Error: {io}")


class valueType(enum.Enum):
   Epsilon = "Epsilon"
   Gamma = "Gamma"
   

def strToInt(stringNumber):
	value = 0
	posCount = 0
	for s in stringNumber:
		posCount += 1
		value += int(s)*2**(len(stringNumber)-posCount)
	return value

def calcValue(data, type):
		gamma = ""
		dataSize = len(dataLst[0])
		for i in range(dataSize):
			zeros = 0
			ones = 0
			for data in dataLst:
				if int(data[i]) == 1:
					ones += 1
				else:
					zeros +=1
			if type == valueType.Gamma:		
				if ones > zeros:
					gamma += "1"
				else:
					gamma += "0"	
			else:
				if ones < zeros:
					gamma += "1"
				else:
					gamma += "0"
	
		return strToInt(gamma)

powerConsumption = calcValue(dataLst, valueType.Gamma) * calcValue(dataLst, valueType.Epsilon)

print(f"Gamma value: {calcValue(dataLst, valueType.Gamma)}")
print(f"Epsilon value: {calcValue(dataLst, valueType.Epsilon)}")
print(f"{powerConsumption}")

