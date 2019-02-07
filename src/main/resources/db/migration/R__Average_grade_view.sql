DROP VIEW IF EXISTS student_average_grade;

CREATE VIEW student_average_grade AS SELECT student.students_id AS group_id,
	student.id AS student_id,
	student.name AS student_name,
	COALESCE(AVG(grade.score), 0) AS average
	FROM student LEFT JOIN grade ON student.id = grade.grades_id
	GROUP BY student.students_id, student.id;