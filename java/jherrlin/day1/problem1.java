import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Stream;

enum Direction {
	first,
	increased,
	decreased
}

record Measurement(Integer depth, Direction direction) {}

public class problem1 {
	static Function<String, String> readFile = (fileName) -> {
		try {
			File           file          = new File(fileName);
			BufferedReader br            = new BufferedReader(new FileReader(file));
			StringBuilder  stringBuilder = new StringBuilder();
			String         line;
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}
			return stringBuilder.toString();
		}
		catch (Exception e) {
			System.err.println(e);
			return "";
		}
	};

	static Function<String, Stream<String>> splitLines = (s)
		-> Stream.of(s.split("\\r?\\n"));

	static Function<Stream<String>, Stream<Integer>> toInt = (s1)
		-> s1.map(s2 -> Integer.parseInt(s2));

	static Function<Stream<Integer>, Stream<Measurement>> toMeasurement = (numbers) ->
		numbers.collect(
			() -> new ArrayList<Measurement>(),
			(coll, e) -> {
				if (coll.isEmpty()) {
					coll.add(new Measurement(e, Direction.first));
				} else if (coll.get(coll.size() - 1).depth() < e) {
					coll.add(new Measurement(e, Direction.increased));
				} else {
					coll.add(new Measurement(e, Direction.decreased));
				}
			},
			(c1, c2) -> c1.addAll(c2))
			.stream();

	static Function<Stream<Measurement>, Stream<Measurement>> increasedMeasurements = (measurements) ->
		measurements.filter(m -> m.direction() == Direction.increased);

	public static void main(String[] args) {
		var result = readFile.andThen(splitLines)
			.andThen(toInt)
			.andThen(toMeasurement)
			.andThen(increasedMeasurements)
			.apply("./input.txt")
			.count();
		System.out.println(result);
	}
}
