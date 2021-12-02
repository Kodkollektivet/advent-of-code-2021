file = 'input'


def read(in_file):
    lst = []
    with open(in_file, 'r') as lines:
        for line in lines:
            comp_line = line.strip()
            comp_lst = comp_line.split()
            lst.append(comp_lst)
        res_part1 = part1(lst)
        res_part2 = part2(lst)
    return res_part1, res_part2


def part1(lst):
    dic = {}
    for i in lst:
        if i[0] not in dic and i[0] != 'up':
            dic[i[0]] = int(i[1])
        elif i[0] == 'up':
            dic['down'] -= int(i[1])
        else:
            dic[i[0]] += int(i[1])
    horizontal = dic['forward']
    depth = dic['down']
    return horizontal * depth


def part2(lst):
    aim, depth, forward = 0, 0, 0
    for i in lst:
        if i[0] == 'down':
            aim += int(i[1])
        elif i[0] == 'up':
            aim -= int(i[1])
        else:
            forward += int(i[1])
            depth += (int(i[1]) * aim)
    return depth * forward


def main(file):
    result1, result2 = read(file)
    print("The final result of the multiplied horizontal and depth \
numbers is: ", result1)
    print("the final result after accounting for how the sub really \
works is not: ", result2)


if __name__ == '__main__':
    main(file)
