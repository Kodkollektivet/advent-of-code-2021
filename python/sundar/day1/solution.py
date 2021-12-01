import collections
with open('./input.txt', 'r') as f: 
    data = f.readlines()
data = [l.strip('\n\r') for l in data]
measurements = []
for d in data:
    measurements.append(int(d))

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