file = 'input'


def read(in_file):
    dic = {}
    with open(in_file, 'r') as lines:
        for line in lines:
            comp_line = line.strip()
            comp_lst = comp_line.split()
            if comp_lst[0] not in dic and comp_lst[0] != 'up':
                dic[comp_lst[0]] = int(comp_lst[1])
            elif comp_lst[0] == 'up':
                dic['down'] -= int(comp_lst[1])
            else:
                dic[comp_lst[0]] += int(comp_lst[1])
    # print(dic)
    horizontal = dic['forward']
    depth = dic['down']
    return horizontal * depth


def main(file):
    result = read(file)
    print("The final result of the multiplied horizontal and depth\
numbers is: ", result)


if __name__ == '__main__':
    main(file)
