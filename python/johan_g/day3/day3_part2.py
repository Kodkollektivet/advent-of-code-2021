#advent of code 2021 day3  part2  Johan g
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


def strToInt(stringNumber):
	value = 0
	posCount = 0
	for s in stringNumber:
		posCount += 1
		value += int(s)*2**(len(stringNumber)-posCount)
	return value	

class valueType(enum.Enum):
   Oxygen = "Oxygen"
   co2 = "CO2"


def findValue(data, type):
		numbers = dataLst.copy()
		dataSize = len(dataLst[0])
		for i in range(dataSize):
			sortedNums = []
			zeros = 0
			ones = 0
			for data in numbers:
				if int(data[i]) == 1:
					ones += 1
				else:
					zeros +=1	

			mostCommon = 0	
			if type == valueType.Oxygen:	
				if ones >= zeros:
					mostCommon = 1
				else:
					mostCommon = 0
			else:
				if ones >= zeros:
					mostCommon = 0
				else:
					mostCommon = 1

			for data in numbers:
				if int(data[i]) == mostCommon:
					sortedNums.append(data)					
			numbers = sortedNums.copy()
			if len(numbers) == 1:
				
				return strToInt(numbers[0])

		
		return strToInt(numbers[0])


lsr = findValue(dataLst, valueType.co2) * findValue(dataLst, valueType.Oxygen)

print(f"Oxygen value: {findValue(dataLst, valueType.Oxygen)}")
print(f"CO2 value: {findValue(dataLst, valueType.co2)}")
print(f"lsr: {lsr}")

