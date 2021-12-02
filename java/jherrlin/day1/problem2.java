import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class problem2 {
	enum Direction {
		first,
		increased,
		decreased
	}

	record Measurement(Integer depth, Direction direction) {
	}

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
		} catch (Exception e) {
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

	static Function<Stream<Integer>, Stream<Integer>> applyWindowNumbers = (numbers) -> {
		var                windowSize    = 3;
		List<Integer>      numbersList   = numbers.toList();
		ArrayList<Integer> windowNumbers = new ArrayList<>();
		for (int i = 0; i < numbersList.size(); i++) {
			if (i + windowSize <= numbersList.size()) {
				windowNumbers.add(numbersList.get(i) + numbersList.get(i + 1) + numbersList.get(i + 2));
			}
		}
		return windowNumbers.stream();
	};

	static Function<Stream<Measurement>, Stream<Measurement>> increasedMeasurements = (measurements) ->
		measurements.filter(m -> m.direction() == Direction.increased);

	public static void main(String[] args) {
		var result =
			readFile
				.andThen(splitLines)
				.andThen(toInt)
				.andThen(applyWindowNumbers)
				.andThen(toMeasurement)
				.andThen(increasedMeasurements)
				.apply("./input.txt")
				.count();
		System.out.println(result);
	}
}
