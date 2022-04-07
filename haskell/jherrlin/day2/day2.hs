data Direction = Up Int | Down Int | Forward Int deriving (Show)

readLine :: [String] -> (String, Int)
readLine [a, b] = (a, read b)

toDirection :: (String, Int) -> Direction
toDirection ("forward", x) = Forward x
toDirection ("down", x)    = Down x
toDirection ("up", x)      = Up (-x)

horizontalPred :: Direction -> Bool
horizontalPred (Forward _) = True
horizontalPred _ = False

verticalPred :: Direction -> Bool
verticalPred (Up _) = True
verticalPred (Down _) = True
verticalPred _ = False

directionToInt :: Direction -> Int
directionToInt (Up x) = x
directionToInt (Down x) = x
directionToInt (Forward x) = x

problem1 = do
  contents <- readFile "input.txt"
  let directions  = map (toDirection . readLine . words) $ lines contents
      horizontals = sum $ map directionToInt $ filter horizontalPred directions
      verticals   = sum $ map directionToInt $ filter verticalPred directions
      result      = horizontals * verticals
  print result


acc :: (Int, Int, Int) -> (String, Int) -> (Int, Int, Int)
acc (horizontal, depth, aim) ("down", x)    = (horizontal,     depth,             aim + x)
acc (horizontal, depth, aim) ("up", x)      = (horizontal,     depth,             aim - x)
acc (horizontal, depth, aim) ("forward", x) = (horizontal + x, depth + (aim * x), aim)

problem2 = do
  contents <- readFile "input.txt"
  let directions             = map (readLine . words) $ lines contents
      (horizontal, depth, _) = foldl acc (0, 0, 0) directions
      result                 = horizontal * depth
  print result
