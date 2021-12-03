import collections
import data_cleaner as dt
measurements = dt.measurements

output = []
for i in range(len(measurements)):
    if i == 0:
        output.append('N/A no previous measurement')
    else:
        if measurements[i] > measurements[i-1]:
            output.append('increased')
        elif measurements[i] == measurements[i-1]:
            output.append('unchanged')
        else:
            output.append('decreased')
print(collections.Counter(output))