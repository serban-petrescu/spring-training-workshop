CREATE OR REPLACE VIEW student_average_grade AS SELECT student.students_id AS group_id,
	student.id AS student_id,
	AVG(grade.score) AS average
	FROM student LEFT JOIN grade ON student.id = grade.grades_id
	GROUP BY student.students_id, student.id;