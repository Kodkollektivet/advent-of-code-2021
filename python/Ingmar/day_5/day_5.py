from typing import List, Tuple
from collections import namedtuple
from enum import Enum


Point = namedtuple("Point", ("x", "y"))
Vector = namedtuple("Vector", ("p1", "p2"))


class Relation(Enum):
    Equal = 0
    Greater = 1
    Smaller = 2


def read_file(path: str) -> List:
    with open(path) as f:
        return [item.strip("\n") for item in f.readlines()]


def clean(data: List[str]) -> List[Tuple]:
    return [
        Vector(
            Point(
                int(item.replace(" ", "").split("->")[0].split(",")[0]),
                int(item.replace(" ", "").split("->")[0].split(",")[1]),
            ),
            Point(
                int(item.replace(" ", "").split("->")[1].split(",")[0]),
                int(item.replace(" ", "").split("->")[1].split(",")[1]),
            ),
        )
        for item in data
    ]


def check_relation(x1, y1, x2, y2):
    grid = [[0 for _ in range(1000)] for _ in range(1000)]
    x, y = 0, 0
    match x1:
        case x1 if x1 == x2: x = Relation.Equal
        case x1 if x1 > x2: x = Relation.Greater
        case x1 if x1 < x2: x = Relation.Smaller
    match y1:
        case y1 if y1 == y2: y = Relation.Equal
        case y1 if y1 > y2: y = Relation.Greater
        case y1 if y1 < y2: y = Relation.Smaller
    
    return Point(x, y)


def vents(data: List[tuple]) -> int:
    grid = [[0 for _ in range(1000)] for _ in range(1000)]

    for vec in data:
        p1x, p1y, p2x, p2y = (vec.p1.x, vec.p1.y, vec.p2.x, vec.p2.y)
        relation = check_relation(p1x, p1y, p2x, p2y)
        vec_range = [[], []]
        match relation.x:
            case Relation.Equal:
                lower = p1y if p1y < p2y else p2y
                upper = p1y if lower is p2y else p2y
                vec_range[0] = [p1x for _ in range(lower, upper + 1)]
            case Relation.Greater:
                vec_range[0] = [i for i in range(p2x, p1x + 1)]
            case Relation.Smaller:
                vec_range[0] = [i for i in range(p2x, p1x - 1, -1)]
        match relation.y:
            case Relation.Equal:
                lower = p1x if p1x < p2x else p2x
                upper = p1x if lower is p2x else p2x
                vec_range[1] = [p1y for _ in range(lower, upper + 1)]
            case Relation.Greater:
                vec_range[1] = [i for i in range(p2y, p1y + 1)]
            case Relation.Smaller:
                vec_range[1] = [i for i in range(p2y, p1y - 1, -1)]
        
        if len(vec_range[0]) == len(vec_range[1]):
            for x, y in zip(vec_range[0],vec_range[1]):
                grid[y][x] += 1

    return sum([sum([1 for num in row if num >= 2]) for row in grid])


def main():
    path = "test.txt"
    data = read_file(path)
    data = clean(data)
    hydro_vents = vents(data)
    print(hydro_vents)


if __name__ == "__main__":
    main()
