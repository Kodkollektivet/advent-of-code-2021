import Data.IntMap (IntMap)
import qualified Data.IntMap as IntMap
import Data.List (maximumBy, transpose)
import Data.Function (on)


lineToList :: [Char] -> [Int]
lineToList = map (read . (:""))

stringToMatrix :: String -> [[Int]]
stringToMatrix s = map lineToList $ lines s

frequencies :: [Int] -> IntMap Int
frequencies xs = IntMap.fromListWith (+) [(c, 1) | c <- xs]

largesPairInMap :: IntMap Int -> (Int, Int)
largesPairInMap m = maximumBy (compare `on` snd) $ IntMap.toList m

binaryPositionValues :: Int -> [Int]
binaryPositionValues 0 = []
binaryPositionValues n = foldl (\acc _ -> let h = head acc in h*2:acc ) [1] [1..(pred n)]

listWithBinariesToDecimal :: [Int] -> Int
listWithBinariesToDecimal [] = 0
listWithBinariesToDecimal xs =
  let binList = binaryPositionValues $ length xs
      sumThem = foldl (\acc (x, y) -> acc + x * y) 0
   in sumThem $ zip (reverse binList) (reverse xs)

binaryListInverse :: [Int] -> [Int]
binaryListInverse = map (\x -> if x == 0 then 1 else 0)

problem1 = do
  fileContent <- readFile "input.txt"
  -- fileContent <- readFile "test-input.txt"
  let matrix      = stringToMatrix fileContent
      columnWidth = (pred . length . head) matrix
      gamma       = map fst [largesPairInMap $ frequencies $ map (\xs -> xs !! x) matrix | x <- [0..columnWidth]]
      epsilon     = binaryListInverse gamma
      sum         = listWithBinariesToDecimal gamma * listWithBinariesToDecimal epsilon
  print gamma

column :: [[Int]] -> Int -> [Int]
column matrix id = map (\row -> row !! id) matrix

lookupResult :: Maybe Int -> Int
lookupResult (Just x) = x
lookupResult _ = error "Map does not contain that key!"

type Idx = Int
type Matrix = [[Int]]
type Binary = [Int]
type Pred = (Int -> Int -> Int)

calculate :: Matrix -> Pred -> Binary -> Idx -> Binary
calculate [lastRow] p b idx = reverse (drop idx b) ++ lastRow
calculate   m p b idx =
  let zeros      = lookupResult $ IntMap.lookup 0 $ frequencies $ column m idx
      ones       = lookupResult $ IntMap.lookup 1 $ frequencies $ column m idx
      mostCommon = p zeros ones
      newMatrix  = filter (\row -> mostCommon == (row !! idx)) m
      newIdx     = succ idx
      binary     = mostCommon:b
  in calculate newMatrix p binary newIdx

problem2 = do
  fileContent <- readFile "input.txt"
  -- fileContent <- readFile "test-input.txt"
  let matrix        = stringToMatrix fileContent
      oxygen        = calculate matrix (\z o -> if z <= o then 1 else 0) [] 0
      co2           = calculate matrix (\z o -> if z <= o then 0 else 1) [] 0
      oxygenInDec   = listWithBinariesToDecimal oxygen
      co2InDec      = listWithBinariesToDecimal co2
  print (oxygenInDec * co2InDec)
