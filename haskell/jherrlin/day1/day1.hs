increasingMeasurements :: [Int] -> [Int]
increasingMeasurements [] = []
increasingMeasurements [_] = []
increasingMeasurements (x : y : rest) =
  if x < y
    then y : increasingMeasurements (y : rest)
    else increasingMeasurements (y : rest)

stringToInt :: String -> Int
stringToInt = read

stringsToInts :: [String] -> [Int]
stringsToInts = map stringToInt

applyWindow :: [Int] -> [Int]
applyWindow [] = [0]
applyWindow [_] = [0]
applyWindow [_, _] = [0]
applyWindow (x : y : z : rest) =
  x + y + z : applyWindow (y : z : rest)

problem1 fileName = do
  result <- length . increasingMeasurements . stringsToInts . lines <$> readFile fileName
  print result

problem2 fileName = do
  result <- length . increasingMeasurements . applyWindow . stringsToInts . lines <$> readFile fileName
  print result
