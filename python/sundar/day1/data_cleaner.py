with open('./input.txt', 'r') as f: 
    data = f.readlines()
data = [l.strip('\n\r') for l in data]
measurements = []
for d in data:
    measurements.append(int(d))