from typing import Dict, List


def read_file(path: str) -> List[str]:
    with open(path) as f:
        return [item.strip() for item in f.readlines()]


def generate(data: List[str], rnge: int) -> int:
    initial = init_gen(data=data)
    for _ in range(rnge):
        temp = {num: 0 for num in range(9)}
        for key, val in initial.items():
            match key:
                case 0:
                    temp[6] += val
                    temp[8] += val
                case key if key > 0:
                    temp[key - 1] += val
        initial = temp
    return sum([item for item in initial.values()])

def init_gen(data: List[str]) -> Dict[int, int]:
    timers = list(map(int, data[0].split(",")))
    timers_count = {num: 0 for num in range(9)}
    for time in timers:
        timers_count[time] += 1

    return timers_count

def main():
    path = "input.txt"
    data = read_file(path)
    p1 = generate(data, 80)
    p2 = generate(data, 256)
    print(p1)
    print(p2)
    


if __name__ == "__main__":
    main()
