

from typing import List


def read_file(path: str) -> List[List[str]]:
    with open(path) as f:
        return [item.strip("\n").split() for item in f.readlines()]


def part_one(data: List[List[str]]) -> int:
    # Part One
    hori: int = 0
    depth: int = 0

    for item in data:
        change = int(item[1])
        match item[0]:
            case "forward": hori += change
            case "up": depth -= change
            case "down": depth += change
    return hori * depth


def part_two(data: List[List[str]]) -> int:
    # Part Two
    hori: int = 0
    depth: int = 0
    aim: int = 0

    for item in data:
        change = int(item[1])
        match item[0]:
            case "forward":
                hori += change
                depth += aim * change
            case "up": aim -= change
            case "down": aim += change
    return hori * depth


def main():
    path = "input.txt"
    data = read_file(path)
    print(part_one(data))
    print(part_two(data))


if __name__ == '__main__':
    main()
