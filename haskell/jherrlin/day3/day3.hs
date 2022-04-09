import Data.IntMap (IntMap)
import qualified Data.IntMap as IntMap
import Data.List (sortBy)
import Data.Function (on)


lineToList :: [Char] -> [Int]
lineToList = map (read . (:""))

toIntMap :: [Int] -> IntMap Int
toIntMap xs = IntMap.fromListWith (+) [(c, 1) | c <- xs]

intMapLookup :: IntMap.Key -> IntMap Int -> Maybe Int
intMapLookup = IntMap.lookup

largesPairInMap :: IntMap Int -> (Int, Int)
largesPairInMap m = last $ sortBy (compare `on` snd) $ IntMap.toList m

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
  let grid        = map lineToList $ lines content
      columnWidth = (pred . length . head) grid
      gamma       = map fst [largesPairInMap $ toIntMap $ map (\xs -> xs !! x) grid | x <- [0..columnWidth]]
      epsilon     = binaryListInverse gamma
      sum         = listWithBinariesToDecimal gamma * listWithBinariesToDecimal epsilon
  print gamma
