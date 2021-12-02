# DONE: Implement sliding window, offset = 1
import data_cleaner as d
import collections
data = d.measurements
sums = []
for i in range(len(data)):
    sums.append(sum(data[i:i+3]))
output = []
for i in range(len(sums)):
    if i == 0:
        output.append('N/A no previous measurement')
    else:
        if sums[i] > sums[i-1]:
            output.append('increased')
        elif sums[i] == sums[i-1]:
            output.append('unchanged')
        else:
            output.append('decreased')
print(collections.Counter(output))