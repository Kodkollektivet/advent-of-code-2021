file = 'input'


def read(in_file):
    lst = []
    with open(in_file, 'r') as lines:
        for line in lines:
            comp_line = line.strip()
            comp_lst = comp_line.split()
            lst.append(comp_lst)
        res_part1 = part1(lst)
    return res_part1


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




def main(file):
    result = read(file)
    print("The final result of the multiplied horizontal and depth\
numbers is: ", result)


if __name__ == '__main__':
    main(file)
