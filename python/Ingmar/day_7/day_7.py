from typing import List, Tuple


def read_file(path: str) -> List[int]:
    with open(path) as f:
        return [list(map(int, item.strip().split(","))) for item in f.readlines()][0]


def get_up_low(data: List[int]) -> Tuple[int, int]:
    upper, lower = 0, data[0]
    for item in data:
        match item:
            case item if item > upper: upper = item
            case item if item < lower: lower = item
    return (upper, lower)


def align_one(data: List[int]) -> int:
    upper, lower = get_up_low(data)
    steps = []
    for hori in range(lower, upper + 1):
        steps.append(
            sum([abs(item - hori) for item in data])
            )
    return min(steps)


def align_two(data: List[int]) -> int:
    upper, lower = get_up_low(data)
    steps = []
    for hori in range(lower, upper + 1):
        steps.append(
            sum([sum([i for i in range(abs(hori - item) + 1)])
                for item in data]))
    return min(steps)


def main():
    path = "input.txt"
    data = read_file(path)
    p1 = align_two(data)
    print(p1)


if __name__ == "__main__":
    main()
