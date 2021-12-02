
from typing import List


def read_file(path: str) -> List[int]:
    with open(path) as f:
        return [int(item.strip('\n')) for item in f.readlines()]


""" Part 1 """


def measure(data: List[int]) -> int:
    count: int = 0
    for idx, item in enumerate(data):
        if item > data[idx - 1]:
            count += 1
    return count


""" Part 2 """


def three_measure(data: List[int]) -> int:
    count: int = 0
    temp: int = 0
    for idx, item in enumerate(data):
        try:
            curr = sum([item, data[idx + 1], data[idx + 2]])
            if sum([item, data[idx + 1], data[idx + 2]]) > temp:
                count += 1
            temp = curr
        except IndexError:
            continue
    return count


def main():
    path = "input.txt"
    measurements = read_file(path)
    print(three_measure(measurements))


if __name__ == '__main__':
    main()
