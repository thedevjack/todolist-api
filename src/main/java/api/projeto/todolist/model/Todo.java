package api.projeto.todolist.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "tb_todo", schema = "webtodo")
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true, length = 50)
    private String tarefa;

}
