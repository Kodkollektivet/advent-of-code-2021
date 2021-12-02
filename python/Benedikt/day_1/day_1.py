import os

file_path = os.getcwd() + '/input'
fil_path = 'input'
test_list = [199, 200, 208, 210, 200, 207, 240, 269, 260, 263]


def build_list(file):
    numbers = []
    with open(file, 'r') as lines:
        for line in lines:
            numbers.append(int(line.strip()))
    return numbers


def o_count(lst):
    counter = 0
    for i in range(len(lst)):
        # if lst[i] >= lst[i - 1]:
        if i == 0:  # starting case:
            # print(lst[i], i, 'no previous meassurement available.')
            print(end='')
        elif lst[i] > lst[i - 1]:
        # if lst[i] > lst[i - 1]:
            counter += 1
            # print(lst[i], '\t (increase)', counter)
            print(end='')
        elif lst[i] == lst[i - 1]:
            # print(lst[i], '\t (no change at all)')
            print(end='')
        else:
            # print(lst[i], '\t (decrease)')
            print(end='')
        # print(i - 1, lst[i], lst[i - 1], counter, i)
    # print(len(lst))
    return counter


def mes3(lst):
    counter = 0
    mes3_lst = []
    for i in range(len(lst)):
        try:
            sume = lst[i] + lst[i + 1] + lst[i + 2]
            mes3_lst.append(sume)
        except: IndexError
    return part1_count(mes3_lst)


def part1_count(lst):
    counter = 0
    for i in range(len(lst)):
        if lst[i] > lst[i - 1]:
            counter += 1
    return counter


def main(file, lst):
    num_lst = build_list(file)
    counter = part1_count(num_lst)
    part2 = mes3(num_lst)
    # counter = count(lst)
    print("There are {0} occasions where the meassurement \
has increased".format(counter))
    print("There are {0} occasions where 3 meassurements \
have increased".format(part2))


if __name__ == '__main__':
    main(fil_path, test_list)
