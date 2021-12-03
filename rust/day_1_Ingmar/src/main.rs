

use anyhow;

fn measure(data: Vec<i64>) -> i64 {
    // Part One
    let mut count: i64 = 1;

    for idx in 1..data.len() {
        if data[idx] > data[idx - 1] {
            count += 1;
        } 
    }

    count
}


fn three_measure(data: Vec<i64>) -> i64 {
    // Part 2
    let mut count: i64 = 1;
    let mut temp: i64 = 0;

    for idx in 0..data.len() {
        if data[idx] == data[data.len() - 3] {
            break;
        }
        let curr: i64 = [data[idx], data[idx + 1], data[idx + 2]].iter().sum();
        println!("{} : {}", temp, curr);
        if curr > temp {
            count += 1;
        }
        temp = curr;
    }

    count
}

fn main() -> anyhow::Result<()> {
    let data: Vec<i64> = include_str!("input.txt")
        .split("\n")
        .map(str::parse::<i64>)
        .map(Result::unwrap)
        .collect();

    let mes = three_measure(data);
    println!("{}", mes);

    Ok(())
}
