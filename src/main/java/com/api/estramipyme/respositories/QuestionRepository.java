package com.api.estramipyme.respositories;

import com.api.estramipyme.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = """
        SELECT 
            q.id AS id,
            q.sesion AS section,
            q.question_text AS question,
            COALESCE(STRING_AGG(o.option_text, ' | ' ORDER BY o.option_value), '') AS options
        FROM 
            question q
        LEFT JOIN 
            public.option o ON q.id = o.question_id
        GROUP BY 
            q.id, q.sesion, q.question_text
    """, nativeQuery = true)
    List<Object[]> fetchQuestionsWithOptions();
}
