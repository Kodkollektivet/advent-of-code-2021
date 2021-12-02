
fn part_one(data: &Vec<&str>) -> i32 {
    // define horizontal position and depth
    let mut hori: i32 = 0;
    let mut depth: i32 = 0;
    
    for item in data {
        let new_item = item.split_whitespace().collect::<Vec<&str>>();
        let dir: &str = new_item[0];
        let change: i32 = new_item[1].parse().unwrap();
        match dir {
            "forward" => hori += change,
            "up" => depth -= change,
            "down" => depth += change,
            _ => ()
        };
    }
    hori * depth
}

fn part_two(data: &Vec<&str>) -> i32 {
    let mut hori: i32 = 0;
    let mut depth: i32 = 0;
    let mut aim: i32 = 0;

    for item in data {
        let new_item = item.split_whitespace().collect::<Vec<&str>>();
        let dir: &str = new_item[0];
        let change: i32 = new_item[1].parse().unwrap();
        match dir {
            "forward" => { 
                hori += change;
                depth += aim * change;
            }
            "up" => aim -= change,
            "down" => aim += change,
            _ => ()
        };
    }

    hori * depth
}

fn main() {
    let data: Vec<&str> = include_str!("input.txt")
        .lines()
        .collect::<Vec<&str>>();
    let p1: i32 = part_one(&data);
    let p2: i32 = part_two(&data);

    println!("{:?}", (p1, p2));
}
