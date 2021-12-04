from copy import deepcopy
from typing import List, Tuple


def read_file(path: str) -> List:
    with open(path) as f:
        return [item.strip("\n") for item in f.readlines()]


def clean(data) -> Tuple:
    nums = data[0].split(",")
    data = [item for item in data if item != ""]
    data.pop(0)
    new_data = []
    table = []
    for item in data:
        table.append([int(item) for item in item.split(" ") if item != ""])
        if len(table) == 5:
            new_data.append(table)
            table = []
    return (nums, new_data)


def part_one(data):
    nums, new_data = clean(data)
    return get_winner(data=new_data, nums=nums, onetwo=1)


def part_two(data):
    nums, new_data = clean(data)
    return get_winner(data=new_data, nums=nums, onetwo=2)


def get_winner(data, nums, onetwo):
    drawn = deepcopy(data)
    for num in nums:
        drawn = draw_num(drawn, int(num))
        last_drawn = num
        winners = check_win(drawn)
        if winners:
            if onetwo == 1:
                winner = drawn[winners[0]]
                break
            else:
                if len(drawn) == 1:
                    winner = drawn[0]
                    break
                for num in reversed(sorted(winners)):
                    drawn.pop(num)

    sum_unmarked = sum([sum([num for num in row if num != "x"]) for row in winner])
    return sum_unmarked * int(last_drawn)


def draw_num(data: List, drawn: int) -> List:
    return [
        [["x" if num == drawn else num for num in row] for row in table]
        for table in data
    ]


def check_win(data: List) -> List:
    rows = [0 for _ in range(5)]
    winners = []
    for tidx, table in enumerate(data):
        rows = [0 for _ in range(5)]
        for row in table:
            xes = 0
            for idx, char in enumerate(row):
                if char == "x":
                    xes += 1
                    rows[idx] += 1
                if rows[idx] == 5:
                    winners.append(tidx)

            if xes == 5:
                winners.append(tidx)
    return winners


def main():
    path = "input.txt"
    data = read_file(path)
    p1 = part_one(data)
    p2 = part_two(data)
    print(p1)
    print(p2)


if __name__ == "__main__":
    main()
