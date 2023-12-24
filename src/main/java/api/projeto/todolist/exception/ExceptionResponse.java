package api.projeto.todolist.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse implements Serializable {

   private static final long serialVersionUID = 1L;

   private LocalDateTime timestamp;
   private String message;
   private String path;
   private int status;
   private String error;


}
