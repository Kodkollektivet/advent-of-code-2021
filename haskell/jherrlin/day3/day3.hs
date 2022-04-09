import Data.IntMap (IntMap)
import qualified Data.IntMap as IntMap
import Data.List (maximumBy)
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
  content <- readFile "input.txt"
  let matrix      = stringToMatrix content
      columnWidth = (pred . length . head) matrix
      gamma       = map fst [largesPairInMap $ frequencies $ map (\xs -> xs !! x) matrix | x <- [0..columnWidth]]
      epsilon     = binaryListInverse gamma
      sum         = listWithBinariesToDecimal gamma * listWithBinariesToDecimal epsilon
  print gamma
