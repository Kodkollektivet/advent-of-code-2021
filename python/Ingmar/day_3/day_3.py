
from typing import List


def read_file(path):
    with open(path) as f:
        return [item.strip('\n') for item in f.readlines()]


def part_one(data: List[str]) -> int:
    gamma: str = ""
    epsilon: str = ""
    item_length: int = len(data[0])
    bits: List[List[str]] = [[] for item in range(item_length)]

    for idx in range(item_length):
        for item in data:
            bits[idx].append(item[idx])

    gamma_averages = get_average(bits)

    epsilon_averages = ["1" if item == "0" else "0" for item in gamma_averages]
    gamma = int("".join(gamma_averages), 2)
    epsilon = int("".join(epsilon_averages), 2)

    return gamma * epsilon


def get_average(averages: List) -> str:
    out: List = []
    for item in averages:
        average: str = ""
        zeroes: int = 0
        ones: int = 0
        for bit in item:
            match bit:
                case "0": zeroes += 1
                case "1": ones += 1
        average = "0" if zeroes > ones else "1"
        out.append(average)
    return out


def part_two(data) -> int:
    ox_rating = gr(data, "1")
    co2_rating = gr(data, "0")
    ox: int = int("".join(ox_rating[0]), 2)
    co2: int = int("".join(co2_rating[0]), 2)
    return ox * co2


def gr(data: List, id: str) -> List[str]:
    item_length: int = len(data[0])
    curr = [item for item in data if item[0] == id]

    for i in range(1, item_length):
        if len(curr) == 1:
            return curr
        zeroes, ones = 0, 0
        for item in curr:
            match item[i]:
                case "0": zeroes += 1
                case "1": ones += 1
        match id:
            case "1": curr_id = "1" if ones >= zeroes else "0"
            case "0": curr_id = "0" if zeroes <= ones else "1"

        curr = [item for item in curr if item[i] == curr_id]
    return curr


def main():
    path = "input.txt"
    data = read_file(path)
    p1 = part_one(data)
    p2 = part_two(data)
    print(p1)
    print(p2)


if __name__ == '__main__':
    main()
