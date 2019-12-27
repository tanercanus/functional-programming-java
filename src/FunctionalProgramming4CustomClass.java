import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionalProgramming4CustomClass {

    public static void main(String[] args) {
        List<Course> courses = List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 95, 18000),
                new Course("API", "Microservices", 97, 22000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("FullStack", "FullStack", 96, 31200),
                new Course("AWS", "Cloud", 92, 33000),
                new Course("Azure", "Cloud", 99, 21000),
                new Course("Docker", "Cloud", 99, 33000),
                new Course("Kubernates", "Cloud", 97, 33000)
        );

        //allMatch, noneMatch, anyMatch
        System.out.println("-----");
        Predicate<Course> reviewScoreGreaterThan90Predicate = course -> course.getReviewScore() > 90;
        Predicate<Course> reviewScoreGreaterThan95Predicate = course -> course.getReviewScore() > 95;
        Predicate<Course> reviewScoreLessThan90Predicate = course -> course.getReviewScore() < 90;
        System.out.println(courses.stream().allMatch(reviewScoreGreaterThan90Predicate));
        System.out.println(courses.stream().allMatch(reviewScoreGreaterThan95Predicate));
        System.out.println(courses.stream().noneMatch(reviewScoreLessThan90Predicate));
        System.out.println(courses.stream().anyMatch(reviewScoreGreaterThan95Predicate));

        System.out.println("-----");
        Comparator<Course> compaingByNoOfStudentIncreasing = Comparator.comparing(Course::getNoOfStudents);
        Comparator<Course> compaingByNoOfStudentDecreasing = Comparator.comparing(Course::getNoOfStudents).reversed();
        System.out.println(courses.stream()
                .sorted(compaingByNoOfStudentIncreasing)
                .collect(Collectors.toList()));//increasing order
        //[Spring Boot:18000:95, Spring:20000:98, Azure:21000:99, API:22000:97, Microservices:25000:96, FullStack:31200:96, AWS:33000:92, Docker:33000:99, Kubernates:33000:97]
        System.out.println(courses.stream()
                .sorted(compaingByNoOfStudentDecreasing)
                .collect(Collectors.toList()));//decreasing order
        //[AWS:33000:92, Docker:33000:99, Kubernates:33000:97, FullStack:31200:96, Microservices:25000:96, API:22000:97, Azure:21000:99, Spring:20000:98, Spring Boot:18000:95]

        //Use primitive comparing, etc comparingInt, thenComparingInt
        Comparator<Course> compaingByNoOfStudentAndNoOfStudents =
                Comparator.comparingInt(Course::getNoOfStudents).thenComparingInt(Course::getNoOfStudents).reversed();
        System.out.println(courses.stream()
                .sorted(compaingByNoOfStudentAndNoOfStudents)
                .collect(Collectors.toList()));
        //[AWS:33000:92, Docker:33000:99, Kubernates:33000:97, FullStack:31200:96, Microservices:25000:96, API:22000:97, Azure:21000:99, Spring:20000:98, Spring Boot:18000:95]

        System.out.println("-----");
        System.out.println(courses.stream()
                .sorted(compaingByNoOfStudentAndNoOfStudents)
                .limit(5)
                .collect(Collectors.toList()));
        //[AWS:33000:92, Docker:33000:99, Kubernates:33000:97, FullStack:31200:96, Microservices:25000:96]

        System.out.println("-----");
        System.out.println(courses.stream()
                .sorted(compaingByNoOfStudentAndNoOfStudents)
                .skip(3)
                .collect(Collectors.toList()));
        //[FullStack:31200:96, Microservices:25000:96, API:22000:97, Azure:21000:99, Spring:20000:98, Spring Boot:18000:95]

        System.out.println("-----");
        System.out.println(courses.stream()
                .sorted(compaingByNoOfStudentAndNoOfStudents)
                .skip(3)
                .limit(5)
                .collect(Collectors.toList()));
        //[FullStack:31200:96, Microservices:25000:96, API:22000:97, Azure:21000:99, Spring:20000:98]

        System.out.println("-----");
        System.out.println(courses);
        //[Spring:20000:98, Spring Boot:18000:95, API:22000:97, Microservices:25000:96, FullStack:31200:96, AWS:33000:92, Azure:21000:99, Docker:33000:99, Kubernates:33000:97]

        System.out.println("-----");
        System.out.println(courses.stream()
                .takeWhile(course -> course.getReviewScore() >= 95)//when find this result, it returns back with current elements
                .collect(Collectors.toList()));
        //[Spring:20000:98, Spring Boot:18000:95, API:22000:97, Microservices:25000:96, FullStack:31200:96]

        System.out.println("-----");
        System.out.println(courses.stream()
                .dropWhile(course -> course.getReviewScore() >= 95)//opposite of upper function
                .collect(Collectors.toList()));
        //[AWS:33000:92, Azure:21000:99, Docker:33000:99, Kubernates:33000:97]

        System.out.println("-----");
        System.out.println(courses.stream()
                .max(compaingByNoOfStudentAndNoOfStudents));
        //Optional[Spring Boot:18000:95]

        System.out.println("-----");
        System.out.println(courses.stream()
                .min(compaingByNoOfStudentAndNoOfStudents)
                .orElse(new Course("Kubernetes", "Cloud", 91, 20000)));
        //Optional[AWS:33000:92]
        // if we add the orElse, it doesnt return Optional. it returns Kubernetes:20000:91

        System.out.println("-----");
        System.out.println(courses.stream()
                .filter(reviewScoreLessThan90Predicate)
                .min(compaingByNoOfStudentAndNoOfStudents)
                .orElse(new Course("Kubernetes", "Cloud", 91, 20000))// If not found return this
        );
        //Optional.empty
        //changed to Kubernetes:20000:91() by addinf orElse function

        System.out.println("-----");
        System.out.println(courses.stream()
                .filter(reviewScoreLessThan90Predicate)
                .findFirst()
        );
        //Optional.empty

        System.out.println("-----");
        System.out.println(courses.stream()
                .filter(reviewScoreGreaterThan90Predicate)
                .findFirst()
        );
        //Optional[Spring:20000:98]

        System.out.println("-----");
        System.out.println(courses.stream()
                .filter(reviewScoreGreaterThan90Predicate)
                .findAny()
        );
        //Optional[Spring:20000:98], it can return different result. it is non deterministic.

        System.out.println("-----");
        System.out.println(courses.stream()
                .filter(reviewScoreGreaterThan95Predicate)
                .mapToInt(Course::getNoOfStudents)
                .sum()
        );
        //185200

        System.out.println("-----");
        System.out.println(courses.stream()
                .filter(reviewScoreGreaterThan95Predicate)
                .mapToInt(Course::getNoOfStudents)
                .average()
        );
        //OptionalDouble[26457.14285714286]

        System.out.println("-----");
        System.out.println(courses.stream()
                .filter(reviewScoreGreaterThan95Predicate)
                .mapToInt(Course::getNoOfStudents)
                .count()
        );
        //7

        System.out.println("-----");
        System.out.println(courses.stream()
                .filter(reviewScoreGreaterThan95Predicate)
                .mapToInt(Course::getNoOfStudents)
                .max()
        );
        //OptionalInt[33000]

        System.out.println("-----");
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory)));
        //{
        //  Cloud=[AWS:33000:92, Azure:21000:99, Docker:33000:99, Kubernates:33000:97],
        //  FullStack=[FullStack:31200:96],
        //  Microservices=[API:22000:97, Microservices:25000:96],
        //  Framework=[Spring:20000:98, Spring Boot:18000:95]
        //  }

        System.out.println("-----");
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory, Collectors.counting())));
        //{Cloud=4, FullStack=1, Microservices=2, Framework=2}

        System.out.println("-----");
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(
                        Course::getCategory,
                        Collectors.maxBy(Comparator.comparing(Course::getReviewScore)))));
        //{Cloud=Optional[Azure:21000:99], FullStack=Optional[FullStack:31200:96], Microservices=Optional[API:22000:97], Framework=Optional[Spring:20000:98]}

        System.out.println("-----");
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(
                        Course::getCategory,
                        Collectors.mapping(Course::getName, Collectors.toList()))));
        //{Cloud=[AWS, Azure, Docker, Kubernates], FullStack=[FullStack], Microservices=[API, Microservices], Framework=[Spring, Spring Boot]}

        //All intermadiate operation is lazy, only terminal operaion called the intermadiates are called
        System.out.println("-----");
        List<String> courses2 = List.of("Spring", "Spring Boot", "API", "Microservices",
                "AWS", "PCF", "Azure", "Docker", "Kubernetes");
        courses2.stream()
                .peek(System.out::println)
                .filter(course -> course.length()>11)
                .map(String::toUpperCase)
                .peek(System.out::println)
                .findFirst();
        //Spring
        //Spring Boot
        //API
        //Microservices
        //MICROSERVICES

        System.out.println("-----");
        courses2.stream()
                .peek(System.out::println)
                .filter(course -> course.length()>11)
                .map(String::toUpperCase)
                .peek(System.out::println);
        //Nothing write to console. Because terminate function doesnt call
        //Lazy

        System.out.println("-----");
        List<String> modifyableCourses = new ArrayList<>(courses2);
        modifyableCourses.replaceAll(str -> str.toUpperCase());
        System.out.println(modifyableCourses);

        System.out.println("-----");
        modifyableCourses.removeIf(course -> course.length()<6);
        System.out.println(modifyableCourses);

    }
}

class Course {
    private String name;
    private String category;
    private int reviewScore;
    private int noOfStudents;

    public Course(String name, String category, int reviewScore, int noOfStudents) {
        this.name = name;
        this.category = category;
        this.reviewScore = reviewScore;
        this.noOfStudents = noOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public String toString() {
        return name + ":" + noOfStudents + ":" + reviewScore;
    }
}
