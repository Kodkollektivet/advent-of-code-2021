import Data.List.Extra (splitOn)

type X = Int
type Y = Int
type Marked = Bool
type Position = (X, Y)
type Value = Int
type Item = (Position, Value, Marked)
type Board = [Item]


-- | Split string on char
--
-- >>> wordsWhen (==',') "a,b,c,d"
-- ["a","b","c","d"]
wordsWhen :: (Char -> Bool) -> String -> [String]
wordsWhen p s =
  case dropWhile p s of
    "" -> []
    s' -> w : wordsWhen p s''
      where
        (w, s'') = break p s'

-- | Partition
--
-- >>> partition 2 [1,2,3,4,5,6]
-- [[1,2],[3,4],[5,6]]
partition :: Int -> [a] -> [[a]]
partition _ [] = []
partition n xs = take n xs : partition n (drop n xs)

-- | Turn ints into Items
--
-- >>> valuesToItems [0..4]
-- [((0,0),0,False),((0,1),1,False),((0,2),2,False),((0,3),3,False),((0,4),4,False)]
valuesToItems :: [Value] -> Board
valuesToItems xs = zip3 (cycle [(x,y) | x <- [0,1,2,3,4], y <- [0,1,2,3,4]]) xs (repeat False)

-- | Mark item on board
--
-- >>> markItem ((0,0), 1, False)
-- ((0,0),1,True)
markItem :: Item -> Item
markItem (p,v,_) = (p,v,True)

-- | Get a row from the board
--
-- >>> row 1 $ valuesToItems [0..24]
-- [((1,0),5,False),((1,1),6,False),((1,2),7,False),((1,3),8,False),((1,4),9,False)]
row :: Int -> Board -> Board
row i = filter (\((x,_),_,_) -> x == i)

-- | Get a column from the board
--
-- >>> column 1 $ valuesToItems [0..24]
-- [((0,1),1,False),((1,1),6,False),((2,1),11,False),((3,1),16,False),((4,1),21,False)]
column :: Int -> Board -> Board
column i = filter (\((_,y),_,_) -> y == i)

-- | Does a board row has bingo?
--
-- >>> bingoRow [((0,0),0,True),((0,1),1,True),((0,2),2,False),((0,3),3,False),((0,4),4,False)]
-- False
-- >>> bingoRow [((0,0),0,True),((0,1),1,True),((0,2),2,True),((0,3),3,True),((0,4),4,True)]
-- True
bingoRow :: Board -> Bool
bingoRow = all (\(_,_,z) -> z == True)

-- | Check if board has bingo
--
-- >>> boardHasBingo $ valuesToItems [0..24]
-- False
-- >>> boardHasBingo $ map markItem $ valuesToItems [0..24]
-- True
boardHasBingo :: Board -> Bool
boardHasBingo board
  | columns   = True
  | rows      = True
  | otherwise = False
  where columns = any (==True) $ map (\x -> bingoRow $ column x board) [0..4]
        rows    = any (==True) $ map (\x -> bingoRow $ row    x board) [0..4]

-- | Mark items on board they are the same as value
--
-- >>> nextBoardGeneration 1 $ valuesToItems [0..2]
-- [((0,0),0,False),((0,1),1,True),((0,2),2,False)]
nextBoardGeneration :: Value -> Board -> Board
nextBoardGeneration x = map (\item@(_,v,_) -> if v == x then markItem item else item)

whenIWins :: Bool -> [Value] -> [Board] -> Int
whenIWins False (x:xs) boards =
  let nextBoardGenerations = map (nextBoardGeneration x) boards
      anyBingo             = any ((==True) . boardHasBingo) nextBoardGenerations
  in whenIWins anyBingo (if anyBingo then [x] else xs) nextBoardGenerations

whenIWins True  (x:_) boards =
  let bingoBoard      = filter boardHasBingo boards
      unmarkedNumbers = filter (\(_,_,z) -> z == False) $ head $ bingoBoard
      unmarkedSum     = sum $ map (\(_,y,_) -> y) unmarkedNumbers
  in unmarkedSum * x

problem1 = do
  -- fileContent <- readFile "input.txt"
  fileContent <- readFile "test-input.txt"
  let nonBlankLines = filter (not . null) $lines fileContent
      bingoNumbers  = map (\x -> read x :: Int) $ wordsWhen (==',') $ head nonBlankLines
      boardStrings  = tail nonBlankLines
      items         = concatMap (map (\x -> read x :: Int) . words) boardStrings
      boards        = partition 25 $ zip3 (cycle [(x,y) | x <- [0,1,2,3,4], y <- [0,1,2,3,4]]) items (repeat False)
      result        = whenIWins False bingoNumbers boards
  print result

whenSquidWins :: Bool -> [Value] -> [Board] -> Int
whenSquidWins False (x:xs) boards =
  let nextBoardGenerations = map (nextBoardGeneration x) boards
      numberWithoutMark    = length $ filter ((==False) . boardHasBingo) nextBoardGenerations
      noOneLeft            = (0 == numberWithoutMark)
      lastBoard            = map (nextBoardGeneration x) $ filter ((==False) . boardHasBingo) boards
  in whenSquidWins noOneLeft (if noOneLeft then [x] else xs) (if noOneLeft then lastBoard else nextBoardGenerations)

whenSquidWins True (x:xs) boards =
  let unmarkedNumbers = filter (\(_,_,z) -> z == False) $ head boards
      unmarkedSum     = sum $ map (\(_,y,_) -> y) unmarkedNumbers
  in unmarkedSum * x

problem2 = do
  -- fileContent <- readFile "input.txt"
  fileContent <- readFile "test-input.txt"
  let nonBlankLines = filter (not . null) $lines fileContent
      bingoNumbers  = map (\x -> read x :: Int) $ wordsWhen (==',') $ head nonBlankLines
      boardStrings  = tail nonBlankLines
      items         = concatMap (map (\x -> read x :: Int) . words) boardStrings
      boards        = partition 25 $ zip3 (cycle [(x,y) | x <- [0,1,2,3,4], y <- [0,1,2,3,4]]) items (repeat False)
      result        = whenSquidWins False bingoNumbers boards
  print result
