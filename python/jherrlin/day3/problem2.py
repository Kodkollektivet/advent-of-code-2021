import collections

input = """00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010"""

test_matrix = [[int(y) for y in list(x)] for x in input.splitlines()]

with open('input.txt', 'r') as f:
    matrix = [[int(y) for y in list(x.strip())] for x in f.readlines()]

def find(matrix, oxygen_or_co2, idx=0):
    if len(matrix) == 1:
        return int("".join([str(x) for x in matrix[0]]), 2)

    frequencies = collections.Counter([x[idx] for x in matrix])
    most_common = oxygen_or_co2(frequencies.get(0), frequencies.get(1))
    matrix = list(filter(lambda x: x[idx] == most_common, matrix))
    idx += 1
    return find(matrix, oxygen_or_co2, idx)

# test input
a = find(test_matrix, lambda zeroes, ones: 1 if zeroes <= ones else 0)
b = find(test_matrix, lambda zeroes, ones: 0 if zeroes <= ones else 1)
print(a * b)

# real input
c = find(matrix, lambda zeroes, ones: 1 if zeroes <= ones else 0)
d = find(matrix, lambda zeroes, ones: 0 if zeroes <= ones else 1)
print(c * d)
